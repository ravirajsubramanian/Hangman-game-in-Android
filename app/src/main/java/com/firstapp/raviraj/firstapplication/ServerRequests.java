package com.firstapp.raviraj.firstapplication;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ProgressDialog;

import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;
import org.xml.sax.helpers.DefaultHandler;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class ServerRequests {
    ProgressDialog progressDialog;
    public static final int CONNECTION_IMEOUT = 1000 * 15;
    public static final String SERVER_ADDRESS = "https://127.0.0.1/new/";

    public ServerRequests(Context context){
        progressDialog = new ProgressDialog(context);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please Wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback userCallback){
         progressDialog.show();
        new StoreUserDataAsyncTask(user, userCallback).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback callback){
        progressDialog.show();
        new FetchUserDataAsyncTask(user, callback).execute();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, User>{
        User user;
        GetUserCallback userCallback;
        public StoreUserDataAsyncTask(User user, GetUserCallback callback){
            this.user = user;
            this.userCallback = userCallback;
        }

        protected User doInBackground(Void... params){
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("name", user.name));
            dataToSend.add(new BasicNamePair("email", user.email));
            dataToSend.add(new BasicNamePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_IMEOUT);
            HttpConnectionParams.setSoTimeout(HttpRequestParams, CONNECTION_IMEOUT)

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "Register.php");

            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                client.execute(post);
            } catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }
    public class FetchUserDataAsyncTask extends AsyncTask<Void, Void, User>{
        User user;
        GetUserCallback userCallback;
        public FetchUserDataAsyncTask(User user, GetUserCallback callback){
            this.user = user;
            this.userCallback = userCallback;
        }

        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNamePair("email", user.email));
            dataToSend.add(new BasicNamePair("password", user.password));

            HttpParams httpRequestParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpRequestParams, CONNECTION_IMEOUT);
            HttpConnectionParams.setSoTimeout(HttpRequestParams, CONNECTION_IMEOUT)

            HttpClient client = new DefaultHttpClient(httpRequestParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchUserData.php");
            User.returnedUser = null;
            try{
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
               HttpResponse httpResponse = client.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                String result = EntityUtils.toString(entity);
                JSONObject jObject = new JSONObject(result);

                if (Object.length == 0){
                    returnedUser = null;
                } else{
                    String name = jObject.getString("name");
                    String email = jObject.getString("email");

                    returnedUser = new User(name, user.email);
                }

            } catch (Exception e){
                e.printStackTrace();
            }
            return retunedUser;
        }

        protected void onPostExecute(User returnedUser) {
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }
