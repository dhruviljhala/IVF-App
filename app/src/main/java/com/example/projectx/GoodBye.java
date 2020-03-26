package com.example.projectx;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GoodBye extends AppCompatActivity {
    Button b1,btn;
    TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_good_bye);
        Bundle bundle = getIntent().getExtras();
        b1=(Button) findViewById(R.id.placeanotherorder);
        btn=(Button)findViewById(R.id.btn);
        t1=(TextView) findViewById(R.id.textView8);
        String s1= bundle.getString("v7");
        t1.setText(s1);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GoodBye.this, Menu.class));
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GoodBye.this,SplashActivity.class));
            }
        });


    }
}
