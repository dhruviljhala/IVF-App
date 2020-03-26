package com.example.projectx;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class OrderDetails extends AppCompatActivity {
    EditText t1,t2,t3,t4;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);
        t1=findViewById(R.id.editText7);
        t2=findViewById(R.id.editText9);
        t3=findViewById(R.id.editText10);
        t4=findViewById(R.id.editText11);
        b1=findViewById(R.id.cotinue);
        final StringBuilder stringBuilder = new StringBuilder();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();

                stringBuilder.append("Contact - "+(t1.getText().toString()));
                stringBuilder.append("\nAddress - \n"+(t2.getText().toString()));
                stringBuilder.append("\n"+(t3.getText().toString()));
                stringBuilder.append("\n"+(t4.getText().toString()));

                bundle.putString("v7",stringBuilder.toString());
                Intent intent = new Intent(OrderDetails.this,GoodBye.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
