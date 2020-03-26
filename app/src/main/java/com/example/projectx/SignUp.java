package com.example.projectx;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class SignUp extends AppCompatActivity implements View.OnClickListener{
    EditText email,password;
    private FirebaseAuth mAuth;
    ProgressBar p1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        email=findViewById(R.id.editText3);
        password=findViewById(R.id.editText4);
        p1=findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        findViewById(R.id.signup).setOnClickListener(this);

    }

    private void registerUser(){
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

        mAuth.createUserWithEmailAndPassword(useremail,pwd).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                p1.setVisibility(View.GONE);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"User registered successfully",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUp.this,MainActivity.class));
                }
                else{
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        Toast.makeText(getApplicationContext(),"User is already registered",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.signup:
                registerUser();
                break;
        }
    }
}

