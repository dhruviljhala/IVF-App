package com.example.fyp;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
//import android.widget.ListView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import java.util.HashMap;

public class Input_parameters extends AppCompatActivity {

    public static final String TAG = "TAG";
    static final Upload upload_all = new Upload();
    Button button_next_page,button_upload;
    EditText ivf_count , embryo_count , birth_count , Age , preg_count;
    Integer age , pregCount,ivfCount,embryoCount,birthCount;
    String uid,uemail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_parameters);


        ivf_count = findViewById(R.id.editTextNumber_prev_ivf);
        Age = findViewById(R.id.editTextNumber_age);
        embryo_count = findViewById(R.id.editTextNumber_embryo_count);
        preg_count = findViewById(R.id.editTextNumber_preg_count);
        birth_count = findViewById(R.id.editTextNumber_birth_count);


        button_next_page=findViewById(R.id.next_page);
        button_next_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Input_parameters.this, Input_Image.class));
                Input_parameters.super.finish();
            }
        });

        button_upload = findViewById(R.id.button_upload);
        button_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Age.getText().equals("")){
                    Age.setError("Email is required");
                    Age.requestFocus();
                    return;
                }
                if(ivf_count.getText().equals("")){
                    ivf_count.setError("Email is required");
                    ivf_count.requestFocus();
                    return;
                }
                if(preg_count.getText().equals("")){
                    preg_count.setError("Email is required");
                    preg_count.requestFocus();
                    return;
                }
                if(embryo_count.getText().equals("")){
                    embryo_count.setError("Email is required");
                    embryo_count.requestFocus();
                    return;
                }
                if(birth_count.getText().equals("")){
                    birth_count.setError("Email is required");
                    birth_count.requestFocus();
                    return;
                }
                age= Integer.parseInt(Age.getText().toString());
                embryoCount= Integer.parseInt(embryo_count.getText().toString());
                pregCount= Integer.parseInt(preg_count.getText().toString());
                birthCount= Integer.parseInt(birth_count.getText().toString());
                ivfCount= Integer.parseInt(ivf_count.getText().toString());
                uploadParameters();
                Toast.makeText(getApplicationContext(),"Upload Successful",Toast.LENGTH_SHORT).show();
            }
        });

    }

    public  void uploadParameters(){
        if(FirebaseAuth.getInstance().getCurrentUser().getUid() != null) {
            HashMap<String, String> User = new HashMap<>();
            uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            uemail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
            User.put("Uid",uid);
            User.put("UserEmail",uemail);
            User.put("Age", age.toString() );
            User.put("Embryo Count",embryoCount.toString());
            User.put("IVF Count",ivfCount.toString());
            User.put("Pregnancy Count",pregCount.toString());
            User.put("Live Birth Count",birthCount.toString());
            upload_all.setParameters(User);
        }
    }

};
