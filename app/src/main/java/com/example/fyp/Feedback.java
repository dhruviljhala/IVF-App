package com.example.fyp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Feedback extends AppCompatActivity {
     Button B_logout , B_Submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        B_logout = findViewById(R.id.button_logout);
        B_Submit = findViewById(R.id.button_submit);

        B_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Logged Out !",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Feedback.this, MainActivity.class));
                Feedback.super.finish();
            }
        });

        B_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitFeedback();
            }
        });
        B_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SubmitFeedback();
                Toast.makeText(getApplicationContext(),"Thank you for your feed back! ",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Feedback.this, Prediction.class));
                Feedback.super.finish();
            }
        });
    }

    public static  void SubmitFeedback(){

    }



}
