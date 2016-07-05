package com.firstapp.raviraj.firstapplication;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private String[] words;
    private Random rand;
    private String currWord;
    private LinearLayout wordLayout;
    private TextView[] charViews;
    private GridView letters;
    private TextView hangman;
    private ImageView imgSrc;
    private int[] imgParts;
    private String[] bodyParts;
    private int numParts=5;
    private int currPart;
    private int numChars;
    private int numCorr;
    LetterAdapter ltrAdapt;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        Resources res = getResources();
        words = res.getStringArray(R.array.words);
        rand = new Random();
        currWord = "";
        wordLayout = (LinearLayout) findViewById(R.id.word);

        letters = (GridView)findViewById(R.id.letters);
        hangman = (TextView)findViewById(R.id.hangman);
        imgSrc = (ImageView)findViewById(R.id.image);
        imgSrc.setImageResource(R.drawable.open);
        hangman.setText("5");
        imgSrc.setImageResource(R.drawable.open);
        playGame();
    }

    private void playGame() {
        bodyParts = new String[numParts];
        bodyParts[0] = "4";
        bodyParts[1] = "3";
        bodyParts[2] = "2";
        bodyParts[3] = "1";
        bodyParts[4] = "0";
        imgParts = new int[numParts];
        imgParts[0] = R.drawable.lose1;
        imgParts[1] = R.drawable.lose2;
        imgParts[2] = R.drawable.lose3;
        imgParts[3] = R.drawable.lose4;
        imgParts[4] = R.drawable.lose5;

        String newWord = words[rand.nextInt(words.length)];
        while (newWord.equals(currWord)) newWord = words[rand.nextInt(words.length)];
        currWord = newWord;
        charViews = new TextView[currWord.length()];
        wordLayout.removeAllViews();
        for (int c = 0; c < currWord.length(); c++) {
            charViews[c] = new TextView(this);
            charViews[c].setText("" + currWord.charAt(c));
            charViews[c].setLayoutParams(new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.WHITE);
            charViews[c].setBackgroundResource(R.drawable.letter_bg);
            wordLayout.addView(charViews[c]);
        }

        ltrAdapt=new LetterAdapter(this);
        letters.setAdapter(ltrAdapt);

        currPart=0;
        numChars=currWord.length();
        numCorr=0;
    }

    public void letterPressed(View view) {
        String ltr=((TextView)view).getText().toString();
        char letterChar = ltr.charAt(0);
        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.letter_down);
        boolean correct = false;
        for(int k = 0; k < currWord.length(); k++) {
            if(currWord.charAt(k)==letterChar){
                correct = true;
                numCorr++;
                charViews[k].setTextColor(Color.BLACK);
            }
        }
        if (correct) {
            //correct guess
        } else if (currPart < numParts) {
            hangman.setText(bodyParts[currPart]);
            imgSrc.setImageResource(imgParts[currPart]);
            currPart++;
        }else{
            disableBtns();
            imgSrc.setImageResource(R.drawable.lose);
            AlertDialog.Builder loseBuild = new AlertDialog.Builder(this);
            loseBuild.setTitle("OOPS");
            loseBuild.setMessage("You lose!\n\nThe answer was:\n\n"+currWord);
            loseBuild.setPositiveButton("Play Again",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            GameActivity.this.playGame();
                            hangman.setText("5");
                            imgSrc.setImageResource(R.drawable.open);
                        }});

            loseBuild.setNegativeButton("Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            GameActivity.this.finish();
                        }});

            loseBuild.show();
        }
        if (numCorr == numChars) {
            disableBtns();
            imgSrc.setImageResource(R.drawable.win);
            AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
            winBuild.setTitle("YAY");
            winBuild.setMessage("You win!\n\nThe answer was:\n\n"+currWord);
            winBuild.setPositiveButton("Play Again",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            GameActivity.this.playGame();
                            hangman.setText("5");
                            imgSrc.setImageResource(R.drawable.open);
                        }});

            winBuild.setNegativeButton("Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            GameActivity.this.finish();
                        }});

            winBuild.show();
        }
    }
    public void disableBtns() {
        int numLetters = letters.getChildCount();
        for (int l = 0; l < numLetters; l++) {
            letters.getChildAt(l).setEnabled(false);
        }
    }

}