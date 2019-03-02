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
        String[] pieces = qa.split("[\\n]+");
        this.question = pieces[0];
        this.answer = pieces[1];

        ((TextView) findViewById(R.id.question)).setText(this.question);
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

            this.log = tempLog + "\n" + this.log;

            System.out.println(this.log);

            this.qNum++;

            if(qNum >= 10)
            {
                String gameOver = "GAME OVER!";
                ((TextView) findViewById(R.id.qNum)).setText(gameOver);
            }
            else
            {
                String qNumString = "Q# " + this.qNum;
                String scoreString = "Score " + this.score;
                ((TextView) findViewById(R.id.qNum)).setText(qNumString);
                ((TextView) findViewById(R.id.score)).setText(scoreString);
                ((TextView) findViewById(R.id.log)).setText(this.log);
                ask();
            }

        }
    }
}
