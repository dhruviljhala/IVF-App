package com.example.projectx;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {
    ListView lst;
    String s1,s2;
    String[] pizzaName={"Margherita"," Siciliano"," Al Funghi","Barbecued Cottage Cheese"," Milano"," All Veggie", " Jerk Cottage \nCheese","Peri-Peri"," Quattro Formaggi"};
    String[] pizzaDesc={"Tomato Sauce, Mozzarella, Olive Oil, Tomatoes, Basil",
            "Tomato Sauce, Mozzarella, Olives, Mushrooms and basil",
            "Tomato Sauce, Mozzarella,Mushrooms and Garlic Flakes, Oregano and basil drizzled with chili and garlic oil",
            "Brabecued cottage cheese, mozarella, tomato sause, Onion & Peppers",
            "Tomato Sauce, Mozarella, Tossed Spinach, Mushrooms and corn",
            "Tomato Sauce, Mozzarella,Mushrooms, Baby Corn, Brocolli, Peppers, Onion, capsicum, Olives and jalepeno",
            "Tomato Sauce, Mozarella, Jerk Marinated cottage cheese, onions and Bellpepers",
            "Tomato Sauce, Mozzarella, Sauted Mushrooms, Cottage cheese and broccoli in peri-peri marination",
            "Tomato Sauce, Mushroom, Corn, Tomato, Mozarella, Parmesan, Feta and Ricotta cheese sprinkled with mix herbs"};
    String[] price ={"270","290","290","390","290","350","360","360","420"};
    Integer[] imgid ={R.drawable.margherita1,R.drawable.siciliano1,R.drawable.alfunghi,R.drawable.barbecued_cottage,R.drawable.milano1,R.drawable.allveggie1,R.drawable.jerkcottage,R.drawable.periperi,R.drawable.quattroformaggi};
    Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        b1=findViewById(R.id.confirmorder);
        lst=findViewById(R.id.listview);
        CustomListView customListView = new CustomListView(this,pizzaName,pizzaDesc,imgid,price);
        lst.setAdapter(customListView);
        lst.setOnItemClickListener(onItemClickListener);
    }

    final Bundle bundle = new Bundle();
    int Total = 0;
    String T;
    final StringBuilder stringBuilder = new StringBuilder();
    final StringBuilder stringBuilder1 =new StringBuilder();
    private AdapterView.OnItemClickListener onItemClickListener =new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


            if(i==0){
                stringBuilder1.append("270\n");
                Total=Total+270;
                stringBuilder.append("Margherita\n");
                Toast.makeText(getApplicationContext(),"Margherita added to cart",Toast.LENGTH_SHORT).show();
            }
            if(i==1){
                stringBuilder1.append("290\n");
                Total=Total+290;
                stringBuilder.append("Siciliano\n");
                Toast.makeText(getApplicationContext(),"Siciliano added to cart",Toast.LENGTH_SHORT).show();
            }
            if(i==2){
                stringBuilder1.append("290\n");
                Total=Total+290;
                stringBuilder.append("Al Funghi\n");
                Toast.makeText(getApplicationContext(),"Al Funghi added to cart",Toast.LENGTH_SHORT).show();
            }
            if(i==3){
                stringBuilder1.append("390\n\n");
                Total=Total+390;
                stringBuilder.append("Barbecued Cottage Cheese\n");
                Toast.makeText(getApplicationContext(),"Barbecued Cottage Cheese added to cart",Toast.LENGTH_SHORT).show();
            }
            if(i==4){
                stringBuilder1.append("290\n");
                Total=Total+290;
                stringBuilder.append("Milano\n");
                Toast.makeText(getApplicationContext(),"Milano added to cart",Toast.LENGTH_SHORT).show();
            }
            if(i==5){
                stringBuilder1.append("350\n");
                Total=Total+350;
                stringBuilder.append("All Veggie\n");
                Toast.makeText(getApplicationContext(),"All Veggie added to cart",Toast.LENGTH_SHORT).show();
            }
            if(i==6){
                stringBuilder1.append("360\n\n");
                Total=Total+360;
                stringBuilder.append("Jerk Cottage Cheese\n");
                Toast.makeText(getApplicationContext(),"Jerk Cottage Cheese added to cart",Toast.LENGTH_SHORT).show();
            }
            if(i==7){
                stringBuilder1.append("360\n");
                Total=Total+360;
                stringBuilder.append("Peri-Peri\n");
                Toast.makeText(getApplicationContext(),"Peri-Peri added to cart",Toast.LENGTH_SHORT).show();
            }
            if(i==8){
                stringBuilder1.append("420\n");
                Total=Total+420;
                stringBuilder.append("Quattro Formaggi\n");
                Toast.makeText(getApplicationContext(),"Quattro Formaggi added to cart",Toast.LENGTH_SHORT).show();
            }
            b1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    Intent intent= new Intent(Menu.this, BillActivity.class);
                    T=Integer.toString(Total);
                    bundle.putString("v1",stringBuilder.toString());
                    bundle.putString("v2",stringBuilder1.toString());
                    bundle.putString("v3",T);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        }
    };








/*

    public boolean onContextItemSelected(MenuItem item){
        if(item.getTitle()=="Add To Cart"){
            Bundle bundle = new Bundle();

            if(lst.getCheckedItemPosition()==0){
                bundle.putString("v1","Margherita");
                Toast.makeText(getApplicationContext(),"Margherita added to cart",Toast.LENGTH_SHORT).show();
            }
            if(lst.getCheckedItemPosition()==1){
                bundle.putString("v2","Siciliano");
                Toast.makeText(getApplicationContext(),"Siciliano added to cart",Toast.LENGTH_SHORT).show();
            }
            if(lst.getCheckedItemPosition()==2){
                bundle.putString("v3","Al Funghi");
                Toast.makeText(getApplicationContext(),"Al Funghi added to cart",Toast.LENGTH_SHORT).show();
            }
            if(lst.getCheckedItemPosition()==3){
                bundle.putString("v4","Barbecued Cottage Cheese");
                Toast.makeText(getApplicationContext(),"Barbecued Cottage Cheese added to cart",Toast.LENGTH_SHORT).show();
            }
            if(lst.getCheckedItemPosition()==4){
                bundle.putString("v5","Milano");
                Toast.makeText(getApplicationContext(),"Milano added to cart",Toast.LENGTH_SHORT).show();
            }
            if(lst.getCheckedItemPosition()==5){
                bundle.putString("v6","All Veggie");
                Toast.makeText(getApplicationContext(),"All Veggie added to cart",Toast.LENGTH_SHORT).show();
            }
            if(lst.getCheckedItemPosition()==6){
                bundle.putString("v7","Jerk Cottage Cheese");
                Toast.makeText(getApplicationContext(),"Jerk Cottage Cheese added to cart",Toast.LENGTH_SHORT).show();
            }
            if(lst.getCheckedItemPosition()==7){
                bundle.putString("v8","Peri-Peri");
                Toast.makeText(getApplicationContext(),"Peri-Peri added to cart",Toast.LENGTH_SHORT).show();
            }
            if(lst.getCheckedItemPosition()==8){
                bundle.putString("v9","Quattro Formaggi");
                Toast.makeText(getApplicationContext(),"Quattro Formaggi added to cart",Toast.LENGTH_SHORT).show();
            }
            Intent intent= new Intent(Menu.this,BillActivit.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else if(item.getTitle()=="Back"){

        }
        return true;
    }*/

}
