package com.example.projectx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class BillActivity extends AppCompatActivity {

    Button b1,b2;
    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bill_activity);

        t1=findViewById(R.id.textView2);
        t2=findViewById(R.id.textView6);
        t3=findViewById(R.id.textView11);
        b1=findViewById(R.id.confirmorder);
        b2=findViewById(R.id.back);

        Bundle bundle= getIntent().getExtras();
        String s1=bundle.getString("v1");
        String s2=bundle.getString("v2");
        String s3=bundle.getString("v3");

        t1.setText(s1);
        t2.setText(s2);
        t3.setText(s3);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BillActivity.this,OrderDetails.class));
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1=new Intent(BillActivity.this, Menu.class);
                startActivity(i1);
            }
        });
    }
}
