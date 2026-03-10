package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText yourNameInput = (EditText) findViewById(R.id.your_name_input);

        Button startButton = (Button) findViewById(R.id.start_button);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nameInput = yourNameInput.getText().toString();
                Log.d("MainActivity", nameInput);
                if(!nameInput.isEmpty()) {
                    Intent nameIntent = new Intent(MainActivity.this, QuestionPage.class);
                    nameIntent.putExtra("Name", nameInput);
                    startActivity(nameIntent);
                }
                else {
                    Toast.makeText(MainActivity.this,  "Please insert your name", Toast.LENGTH_LONG).show();
                }
            }
        });


    }
}