package com.jailsonspeedway.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    public void  startQuiz(View view){

        int quizCategory = 0;

        switch (view.getId()){

            case R.id.asia:
                quizCategory = 1;
                break;

            case R.id.america:
                quizCategory = 2;
                break;

            case R.id.europe:
                quizCategory = 3;
                break;

        }
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("QUIZ_CATEGORY", quizCategory);
        startActivity(intent);

    }
}
