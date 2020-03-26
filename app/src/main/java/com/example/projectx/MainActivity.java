package com.example.projectx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    Button signup,team;
    EditText email,password;
    FirebaseAuth mAuth;
    ProgressBar p1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup=findViewById(R.id.signup);
        team=(Button)findViewById(R.id.team);
        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1= new Intent(MainActivity.this,team.class);
                startActivity(intent1);
            }
        });
        signup.setOnClickListener(this);

        email=findViewById(R.id.editText);
        password=findViewById(R.id.editText3);
        p1=findViewById(R.id.progressbar);

        mAuth=FirebaseAuth.getInstance();
        findViewById(R.id.login).setOnClickListener(this);

    }

    private void userLogin(){
        String useremail = email.getText().toString().trim();
        String pwd = password.getText().toString().trim();

        if(useremail.isEmpty()){
            email.setError("Email is required");
            email.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(useremail).matches()){
            email.setError("Please enter a valid E-mail");
            email.requestFocus();
            return;
        }



        if(pwd.isEmpty()){
            password.setError("Password is required");
            password.requestFocus();
            return;
        }
        if(pwd.length()<6) {
            password.setError("Minimum length of password should be 6");
            password.requestFocus();
            return;
        }
        p1.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(useremail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                p1.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Login Successful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,Menu.class));
                }else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signup:
                startActivity(new Intent(this, SignUp.class));

                break;
            case R.id.login:
                userLogin();
                break;
        }
    }
}