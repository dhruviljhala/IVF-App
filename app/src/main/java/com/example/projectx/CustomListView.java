package com.example.projectx;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ayush Ruel Das on 20-04-2018.
 */

public class CustomListView extends ArrayAdapter<String>{

    private String[] pizzaName;
    private String[] pizzaDesc;
    private String[] price;
    private Integer[] imgid;

    private Activity context;
    public CustomListView(Activity context,String[] pizzaName,String[] pizzaDesc,Integer[] imgid, String[] price) {
        super(context, R.layout.listview_layout, pizzaName);

        this.context = context;
        this.pizzaName = pizzaName;
        this.pizzaDesc = pizzaDesc;
        this.price = price;
        this.imgid = imgid;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View r = convertView;
        ViewHolder viewHolder= null;
        if(r==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            r = layoutInflater.inflate(R.layout.listview_layout,null,true);
            viewHolder= new ViewHolder(r);
            r.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder)r.getTag();
        }
        viewHolder.i1.setImageResource(imgid[position]);
        viewHolder.t1.setText(pizzaName[position]);
        viewHolder.t2.setText(pizzaDesc[position]);
        viewHolder.t3.setText(price[position]);

        return r;
    }
    class ViewHolder{
        TextView t1,t2,t3;
        ImageView i1;


        ViewHolder(View v){
            t1=v.findViewById(R.id.pizzaname);
            t2=v.findViewById(R.id.pizzadesc);
            i1=v.findViewById(R.id.imageView);
            t3=v.findViewById(R.id.price);
        }
    }
}
