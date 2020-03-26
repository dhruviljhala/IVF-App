package com.example.projectx;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class team extends AppCompatActivity {
    ListView lst1;
    Button but;
    String[] memname = {"Anuj Jhamb", "Sumantra Ghosh", "Yash Goyal", "Shivansh Agrawal", "Nishant Dutta"};
    String[] memdesc = {"Btech CS II Year , Firebase and UI", "Btech CS II Year , Firebase and UI", "Btech CS II Year , UI and Content", "Btech CS II Year , UI and Content", "Btech CS II Year , UI and Content"};
    Integer[] memid = {R.drawable.anuj, R.drawable.sumo, R.drawable.yash, R.drawable.shiv, R.drawable.tutta};
    String[] age = {"21 years", "19 years", "20 years", "21 years", "18 years"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        lst1 = (ListView) findViewById(R.id.listview);
        but = (Button) findViewById(R.id.but);
        CustomListView customListView1 = new CustomListView(this, memname, memdesc, memid, age);
        lst1.setAdapter(customListView1);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i2 = new Intent(team.this,MainActivity.class);
                startActivity(i2);
            }
        });

    }
}

