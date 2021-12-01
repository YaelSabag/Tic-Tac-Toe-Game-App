package com.example.tictactoegame;

// Yael Sabag

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView appTitle;
    ImageButton[] btns = new ImageButton[9];
    Button playBtn;
    int whoIsPlay = 0; // 0-X play , 1- Y play
    int[] board = new int[9]; // 0- no mark, 1- X mark , 2 -0 mark

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        appTitle = findViewById(R.id.mainactivity_title);
        btns[0] = findViewById(R.id.mainactivity_btn1);
        btns[1] = findViewById(R.id.mainactivity_btn2);
        btns[2] = findViewById(R.id.mainactivity_btn3);
        btns[3] = findViewById(R.id.mainactivity_btn4);
        btns[4] = findViewById(R.id.mainactivity_btn5);
        btns[5] = findViewById(R.id.mainactivity_btn6);
        btns[6] = findViewById(R.id.mainactivity_btn7);
        btns[7] = findViewById(R.id.mainactivity_btn8);
        btns[8] = findViewById(R.id.mainactivity_btn9);
        playBtn = findViewById(R.id.mainactivity_play_btn);

        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onPlayClick();
            }
        });

        for (ImageButton btn : btns) {
            btn.setOnClickListener(this);
            btn.setEnabled(false);
        }

    }

    void onPlayClick() {
        // disable button
        playBtn.setVisibility(View.INVISIBLE);
        //set title to X play
        appTitle.setText("X Turn");
        for (ImageButton btn : btns) {
            btn.setEnabled(true);
            btn.setImageDrawable(null);
        }
        for(int i=0; i<9; i++){
            board[i]=0;
        }
    }

    @Override
    public void onClick(View view) {
        int tag = Integer.parseInt(view.getTag().toString());
        Log.d("TAG", "button click" + tag);
        if (whoIsPlay == 0) {//x play
            whoIsPlay=1; // move turn to Y player
            appTitle.setText("O Turn");
            btns[tag].setImageResource(R.drawable.x);
            btns[tag].setEnabled(false);
            board[tag]=1;
        }
        else {// Y play
            whoIsPlay=0; // move turn to X player
            appTitle.setText("X Turn");
            whoIsPlay=0;
            btns[tag].setImageResource(R.drawable.o);
            btns[tag].setEnabled(false);
            board[tag]=2;
          }
        checkIfGameOver ();
        }

        void checkIfGameOver () {
            boolean boardfull=true;
            // check if all bricks are marked
        for(int i=0; i<9;i++){
            if(board[i]==0) {
                boardfull = false;
                break;
            }
        }

        // check if wins
            if((board[0]==board[1] && board[1]==board[2] && board[0] ==1 )||
                    (board[3]==board[4] && board[4]==board[5] && board[3] ==1 )||
                    (board[6]==board[7] && board[7]==board[8] && board[6] ==1 )||
                    (board[0]==board[3] && board[3]==board[6] && board[0] ==1 )||
                    (board[1]==board[4] && board[4]==board[7] && board[1] ==1 )||
                    (board[2]==board[5] && board[5]==board[8] && board[2] ==1 )||
                    (board[0]==board[4] && board[4]==board[8] && board[0] ==1 )||
                    (board[2]==board[4] && board[4]==board[6] && board[2] ==1 ))
            {
                    Log.d("TAG", "X wins");
                gameEnd("X WINS");
            }
            if((board[0]==board[1] && board[1]==board[2] && board[0] ==2 )||
                    (board[3]==board[4] && board[4]==board[5] && board[3] ==2 )||
                    (board[6]==board[7] && board[7]==board[8] && board[6] ==2 )||
                    (board[0]==board[3] && board[3]==board[6] && board[0] ==2 )||
                    (board[1]==board[4] && board[4]==board[7] && board[1] ==2 )||
                    (board[2]==board[5] && board[5]==board[8] && board[2] ==2 )||
                    (board[0]==board[4] && board[4]==board[8] && board[0] ==2 )||
                    (board[2]==board[4] && board[4]==board[6] && board[2] ==2 ))
            {
                Log.d("TAG", "X wins");
                gameEnd("O WINS");
            }

            if(boardfull==true){
                gameEnd("DRAW");
            }
    }
    void gameEnd(String ttl){
        appTitle.setText(ttl);
        for (ImageButton btn : btns) {
            btn.setEnabled(false);
        }
        playBtn.setText("Play Again");
        playBtn.setVisibility(View.VISIBLE);
    }

}