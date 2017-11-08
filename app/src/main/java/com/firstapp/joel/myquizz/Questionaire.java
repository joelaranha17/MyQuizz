package com.firstapp.joel.myquizz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by joel on 11/5/2017.
 */

public class Questionaire extends AppCompatActivity {

    Button java;
    Button android;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.questionaire);

        java = (Button)findViewById(R.id.qPageJava);
        android = (Button)findViewById(R.id.qPageAndroid);

    java.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Toast.makeText(Questionaire.this, "Loading Java Quiz", Toast.LENGTH_SHORT).show();
            Intent intj = new Intent(Questionaire.this, com.firstapp.joel.myquizz.java.QuizActivity.class);
            startActivity(intj);
        }
    });
    android.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            //load android file from db;
            Toast.makeText(Questionaire.this, "Loading Android Quiz", Toast.LENGTH_SHORT).show();
            Intent inta = new Intent(Questionaire.this, com.firstapp.joel.myquizz.android.QuizActivity.class);
            startActivity(inta);
        }
    });

    }

}
