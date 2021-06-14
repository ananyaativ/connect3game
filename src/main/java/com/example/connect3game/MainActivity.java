package com.example.connect3game;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //0:red 1:blue 2:empty
    int activeplayer=0;
    boolean gameactive=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};//{0,1,1,0,0,0,2,2,1}
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void tap(View view){
        ImageView counter=(ImageView) view;
        int tappedCounter=Integer.parseInt(counter.getTag().toString());
        if(gameState[tappedCounter]==2&&gameactive)
         {
            gameState[tappedCounter] = activeplayer;
            counter.setTranslationY(-1500);
            if (activeplayer == 0) {
                counter.setImageResource(R.drawable.red);
                activeplayer = 1;
            } else {
                counter.setImageResource(R.drawable.blue);
                activeplayer = 0;
            }
            counter.animate().translationYBy(1500).rotationBy(3600).setDuration(300);
            for (int[] winninposition : winningPositions) {
                if (gameState[winninposition[0]] == gameState[winninposition[1]] && gameState[winninposition[1]] == gameState[winninposition[2]] && gameState[winninposition[0]] != 2) {
                    gameactive=false;
                    String winner="";
                    if (activeplayer==1) {
                        winner="Red";
                    } else {
                        winner="Blue";
                    }
                    Button button=(Button)findViewById(R.id.button);
                    TextView winnerTextView=(TextView)findViewById(R.id.textView2);
                    winnerTextView.setText(winner+" has won!");
                    winnerTextView.setVisibility(View.VISIBLE);
                    button.setVisibility(View.VISIBLE);
                }
            }
        }

        if(draw()){
            Button button=(Button)findViewById(R.id.button);
            TextView winnerTextView=(TextView)findViewById(R.id.textView2);
            winnerTextView.setText("Nobody has won :(");
            winnerTextView.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);


        }
    }

    public boolean full() {
        for(int i=0;i<gameState.length;i++) {
            if(gameState[i]==2)
            return false;
        }
        return true;
    }

    public boolean draw(){
        if(full()&&gameactive==true){
            return true;
        }
        return false;
    }

    public void playagain(View view){
        Button button=(Button)findViewById(R.id.button);
        TextView winnerTextView=(TextView)findViewById(R.id.textView2);
        winnerTextView.setVisibility(View.INVISIBLE);
        button.setVisibility(View.INVISIBLE);
        GridLayout gridLayout=(GridLayout)findViewById(R.id.grid);
        Log.i("Success","0");
        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageResource(0);
        }
        activeplayer=0;
        gameactive=true;
        for(int i=0;i<gameState.length;i++)
            gameState[i]=2;




    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}