package com.example.ferooz.horticulture.LandDetails;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.ferooz.horticulture.R;

import java.util.List;

/**
 * Created by admin on 4/25/2017.
 */

public class CustomizeList extends ArrayAdapter<String> {
    private final Activity context;
    private final List<String> web;

    public CustomizeList(Activity context,
                         List<String> web) {
        super(context, R.layout.list_item, web);
        this.context = context;
        this.web = web;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_item, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);
        TextView numbers= (TextView) rowView.findViewById(R.id.txt1);
        txtTitle.setText(web.get(position));

        if(position==0){
            numbers.setText("A.");
        }
        if (position==1){
            numbers.setText("B.");
        }
        if (position==2){
            numbers.setText("C.");
        }
        if (position==3){
            numbers.setText("D.");
        }
        if (position==4){
            numbers.setText("E.");
        }

        if (position==5){
            numbers.setText("F.");
        }

        return rowView;
    }


}
