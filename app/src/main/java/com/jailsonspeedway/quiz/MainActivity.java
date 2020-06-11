package com.jailsonspeedway.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 5;

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] =  {

            {"Brasil", "Brasília","São Paulo","Minas Gerais","Mato Grosso do Sul"},
            {"India", "New Delhi","Beijing","Bangkok","Seoul"},
            {"México", "México City","Ottawa","Berlin","Santiago"},
            {"Afeganistão", "Cabul","Ottawa","Tirana","Riade"},
            {" Austrália", "Camberra","Bacu","Manama","Sófia"}

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = (TextView) findViewById(R.id.countLabel);
        questionLabel = (TextView) findViewById(R.id.questionLabel);
        answerBtn1 = (Button) findViewById(R.id.answerBtn1);
        answerBtn2 = (Button) findViewById(R.id.answerBtn2);
        answerBtn3 = (Button) findViewById(R.id.answerBtn3);
        answerBtn4 = (Button) findViewById(R.id.answerBtn4);

        // Receive quizCategory from StartActivity
        int quizCategory = getIntent().getIntExtra("QUIZ_CATEGORY", 0);

        Log.v("CATEGORY_TAG", quizCategory + "");

        //Create quizArray from quizData

        for(int i = 0; i <quizData.length; i++){
            //Prepare array

            ArrayList<String> tmpArray = new ArrayList<>();
            tmpArray.add(quizData[i][0]); //Country (País)
            tmpArray.add(quizData[i][1]); // Resposta Certa
            tmpArray.add(quizData[i][2]); // Alternativa 1
            tmpArray.add(quizData[i][3]); // Alternativa 2
            tmpArray.add(quizData[i][4]); // Alternativa 3

            //Add tmpArray to quizArray
            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz(){

        //update quizCountLabel
        countLabel.setText("Q" + quizCount);

        //Generate random number berween 0 and 14 (quizArray size -1)
        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        //Pick one quiz set.
        ArrayList<String> quiz = quizArray.get(randomNum);

        //Set Question anad right answer
        //Array Format
        questionLabel.setText(quiz.get(0));
        rightAnswer = quiz.get(1);

        //Remove "country País" from quiz and shuffle choices
        quiz.remove(0);
        Collections.shuffle(quiz);

        //Set Choices

        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        //Remove this quiz from quizArray
        quizArray.remove(randomNum);

    }

    public void checkAnswer(View view){

        //Get pushed button

        Button answerBtn = (Button) findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        String alertTitle;

        if(btnText.equals(rightAnswer)){
            alertTitle = "Correto";
            rightAnswerCount++;
        }else{

            alertTitle = "Errado!.";
        }

        //Create Dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(quizCount == QUIZ_COUNT){
                    //SHOW RESULTADO
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    startActivity(intent);

                }else{
                    quizCount++;
                    showNextQuiz();
                }
            }
        });

        builder.setCancelable(false);
        builder.show();

    }


}
