package com.example.caps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class CapsActivity extends AppCompatActivity
{
    private Game game;
    private String question;
    private String answer;
    private int score;
    private int qNum;
    private String log;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.caps_layout);

        this.game = new Game();
        ask();
        this.score = 0;
        this.qNum = 1;
        this.log = "";
    }

    private void ask()
    {
        String qa = game.qa();
        String[] pieces = qa.split("[^A-Za-z]+");
        this.question = pieces[0];
        this.answer = pieces[1];
    }

    public void onDone(View v)
    {
        if (qNum >= 10)
        {
            this.finish();
        }
        else
        {
            String userAnswer = ((EditText) findViewById(R.id.answer)).getText().toString();
            if (userAnswer.equalsIgnoreCase(this.answer))
            {
                this.score ++;
            }
            String tempLog;
            tempLog = "Q# " + this.qNum + ": " + this.question + "\n";
            tempLog += "Your answer: " + userAnswer.toUpperCase() + "\n";
            tempLog += "Correct Answer: " + this.answer + "\n";

            this.log = tempLog + this.log;

            this.qNum++;

            if(qNum >= 10)
            {
                ((TextView) findViewById(R.id.qNum)).setText("GAME OVER!");
            }
            else
            {
                ((TextView) findViewById(R.id.qNum)).setText("Q# " + this.qNum);
                ((TextView) findViewById(R.id.score)).setText("Score " + this.score);
                ask();
            }

        }
    }
}
