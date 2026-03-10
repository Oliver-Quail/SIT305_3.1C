package com.example.myapplication;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class QuestionPage extends AppCompatActivity {

    String[] questions = {"What year did World War Two Start?", "Which one of these men was not a prime minister during World War 2?", "Which one of these was a tank in World War Two?", "Which one of these countries that participated in World War Two no longer exists?", "Which territories did Australia own in World War Two?"};
    String[] questionDetails = {"This is referring to the war in Europe", "Did you know there was also an election in 1941?", "Be careful of the year", "Clue, this country was in eastern Europe", "Clue, it's not WA lol"};

    String[] answersOne = {"1945", "John Curtin", "T-80", "Soviet Union", "Malaysia"};
    String[] answersTwo = {"1939", "Robert Menzies", "T-43", "United Kingdom", "New Caledonia"};
    String[] answersThree = {"1937", "Harold Holt", "Leoped 2", "Croatia", "Solomon Islands"};
    Integer[] correctAnswer = {1,2,1,0,2};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_question_page);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        final boolean[] answeredQuestion = {false};
        final Integer[] answer = {-1};
        final Integer[] currentQuestion = {0};
        final Integer[] score = {0};

        String name;

        if(getIntent() != null) {
            name = getIntent().getStringExtra("Name");
        } else {
            name = "";
        }

        Button answerOneButton = (Button) findViewById(R.id.question_button_one);
        Button answerTwoButton = (Button) findViewById(R.id.question_button_two);
        Button answerThreeButton = (Button) findViewById(R.id.question_button_three);
        Button nextQuestionButton = (Button) findViewById(R.id.next_question_button);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress_bar);

        TextView questionText = (TextView) findViewById(R.id.question_text);
        TextView questionDetailsText = (TextView) findViewById(R.id.question_text_details);
        TextView progressText = (TextView) findViewById(R.id.progress_text);

        questionText.setText(questions[0]);
        questionDetailsText.setText(questionDetails[0]);

        answerOneButton.setText(answersOne[0]);
        answerTwoButton.setText(answersTwo[0]);
        answerThreeButton.setText(answersThree[0]);

        answerOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answeredQuestion[0]) {
                    answer[0] = 0;
                }
            }
        });
        answerTwoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answeredQuestion[0]) {
                    answer[0] = 1;
                }
            }
        });
        answerThreeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answeredQuestion[0]) {
                    answer[0] = 2;
                }
            }
        });

        nextQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.setProgress((currentQuestion[0] + 1) * 20);
                progressText.setText(currentQuestion[0] + 1 + "/5");

                if(answeredQuestion[0]) {
                    currentQuestion[0] = currentQuestion[0] + 1;
                    answeredQuestion[0] = false;

                    if(currentQuestion[0] == 5) {
                        nextQuestionButton.setText("Next");
                        Intent answerIntent = new Intent(QuestionPage.this, AnswerPage.class);
                        answerIntent.putExtra("Name", name);
                        answerIntent.putExtra("Score", score[0]);
                        startActivity(answerIntent);
                        finish();
                        return;
                    }
                    questionText.setText(questions[currentQuestion[0]]);
                    questionDetailsText.setText(questionDetails[currentQuestion[0]]);
                    answerOneButton.setText(answersOne[currentQuestion[0]]);
                    answerTwoButton.setText(answersTwo[currentQuestion[0]]);
                    answerThreeButton.setText(answersThree[currentQuestion[0]]);
                    nextQuestionButton.setText("Submit");
                    answerOneButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFc6c6c6")));
                    answerTwoButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFc6c6c6")));
                    answerThreeButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFc6c6c6")));

                    return;
                }

                if(currentQuestion[0] == 5) {
                    Intent answerIntent = new Intent(QuestionPage.this, AnswerPage.class);
                    answerIntent.putExtra("Name", name);
                    answerIntent.putExtra("Score", score[0]);
                    answerIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(answerIntent);
                    return;
                }

                if(answer[0] == -1) {
                    Toast.makeText(QuestionPage.this, "No Answer Selected", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(answer[0] == correctAnswer[currentQuestion[0]]) {
                    score[0] = score[0] + 1;
                }

                Integer correctButton = correctAnswer[currentQuestion[0]];

                switch (answer[0]) {
                    case 0:
                        answerOneButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFB6B3")));
                        break;
                    case 1:
                        answerTwoButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFB6B3")));
                        break;
                    case 2:
                        answerThreeButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFFFB6B3")));
                        break;
                }

                switch (correctAnswer[currentQuestion[0]]) {
                    case 0:
                        answerOneButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFBDE7BD")));
                        break;
                    case 1:
                        answerTwoButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFBDE7BD")));
                        break;
                    case 2:
                        answerThreeButton.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FFBDE7BD")));
                        break;
                }

                nextQuestionButton.setText("Next");

                answeredQuestion[0] = true;

            }
        });





    }
}