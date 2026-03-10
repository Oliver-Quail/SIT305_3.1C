package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

public class AnswerPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_answer_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String name = "";
        int score = 0;
        if(getIntent() != null) {
            score = getIntent().getIntExtra("Score", 0);
            name = getIntent().getStringExtra("Name");
        }

        TextView congratulationsMessage = (TextView) findViewById(R.id.congratulations_message);
        TextView yourScore = (TextView) findViewById(R.id.your_score);

        congratulationsMessage.setText("Congratulations " + name + "!");
        yourScore.setText(score + "/5");

        Button finishButton = (Button) findViewById(R.id.finish_button);
        Button takeNewQuizButton = (Button) findViewById(R.id.take_new_quiz_button);

        finishButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finishAffinity();
                System.exit(0);
            }
        });

        takeNewQuizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}