package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;


public class MainActivity extends AppCompatActivity {
    private boolean gameIsFinished = false;
    private int player = 0;
    int [] score = {0,0};
    private final int winPos [][] =  {{0, 1, 2}, {3, 4, 5},
            {6, 7, 8},{0, 3, 6},
            {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    private int cnt = 0;
    private int[] positions = {3,3,3,
            3,3,3,
            3,3,3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        upDateScore();
    }


    public void onPressed (View view) {

        ImageView imageView = (ImageView) view;
        int tappedImage = Integer.parseInt(view.getTag().toString());
        // use gameIsFinished to stop responding a click
        if(positions[tappedImage] == 3 &&  !gameIsFinished){
            cnt++;

            if(cnt == 9)
                gameIsFinished = true;

            positions[tappedImage] = player;


           // Log.d("player1",String.valueOf(player));
            if(player == 0)
            {
                imageView.setImageResource(R.drawable.x);
                player++;
              //  Log.d("player2", String.valueOf(player));
            }
            else
            {
                imageView.setImageResource(R.drawable.o);
                player--;
            }



            checkGameStatus();
        }

    }
    public void checkGameStatus(){

        boolean flag = false;
        for (int [] winPostions: winPos) {
            if (positions[winPostions[0]] == positions[winPostions[1]] &&
                    positions[winPostions[1]] == positions[winPostions[2]] &&
                    positions[winPostions[0]] != 3) {
                flag = true;
                gameIsFinished = true;
                if (positions[winPostions[0]] == 0) {
                    score[0]++;
                    Toast.makeText(this, "player 1 won", Toast.LENGTH_SHORT).show();
                    delayCallerResetGame();

                } else if (positions[winPostions[0]] == 1) {
                    score[1]++;
                    Toast.makeText(this, "player 2 won", Toast.LENGTH_SHORT).show();
                    delayCallerResetGame();
                }
            }
        }

        if(cnt == 9 && flag == false){
            Toast.makeText(this, "Draw", Toast.LENGTH_SHORT).show();

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    delayCallerResetGame();
                }
            },2000);


        }


    }
    private void delayCallerResetGame(){

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                resetGame();
            }
        },2000);
        upDateScore();
    }
    public void upDateScore(){
        TextView showingScore = findViewById(R.id.scoreTV);
        showingScore.setText(score[0]+"\t: \t"+score[1]);
    }
    public void resetGame(){

        gameIsFinished = false;
        player = 0;
        cnt = 0;
        Arrays.fill(positions,3);
        ((ImageView) findViewById(R.id.field1)).setImageResource(0);
        ((ImageView) findViewById(R.id.field2)).setImageResource(0);
        ((ImageView) findViewById(R.id.field3)).setImageResource(0);
        ((ImageView) findViewById(R.id.field4)).setImageResource(0);
        ((ImageView) findViewById(R.id.field5)).setImageResource(0);
        ((ImageView) findViewById(R.id.field6)).setImageResource(0);
        ((ImageView) findViewById(R.id.field7)).setImageResource(0);
        ((ImageView) findViewById(R.id.field8)).setImageResource(0);
        ((ImageView) findViewById(R.id.field9)).setImageResource(0);

    }


}

