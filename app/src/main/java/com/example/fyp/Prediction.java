package com.example.fyp;


import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Prediction extends AppCompatActivity {
    Button button_Once_more,button_logout,button_feedback;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prediction);

        button_Once_more=(Button) findViewById(R.id.button_Once_more);
        button_logout = (Button) findViewById(R.id.button_logout);
        button_feedback = (Button) findViewById(R.id.button_feedback);

        button_Once_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Prediction.this, Input_parameters.class));
                Prediction.super.finish();
            }
        });
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Prediction.this, MainActivity.class));
                Prediction.super.finish();            }
        });
        button_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Prediction.this,Feedback.class));
                Prediction.super.finish();            }
        });

    }
}
