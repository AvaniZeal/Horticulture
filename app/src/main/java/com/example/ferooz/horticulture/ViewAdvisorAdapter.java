package com.example.ferooz.horticulture;

/**
 * Created by ferooz on 05-04-2017.
 */
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferooz.horticulture.pojoclasses.ViewAdvisorDataProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Belal on 7/22/2015.
 */
public class ViewAdvisorAdapter extends ArrayAdapter {


    List list=new ArrayList();


    public ViewAdvisorAdapter(Context context, int resource, String[] strings) {
        super(context, resource);
    }

    static  class DataHandler{

        TextView queryId;
        TextView queryCatagory;
        TextView places;


    }

    @Override
    public void add(Object object) {
        super.add(object);
        list.add(object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row;
        row=convertView;
        DataHandler handler;

        if(convertView==null){
            LayoutInflater inflater= (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=inflater.inflate(R.layout.view_row_layout, parent, false);
            handler=new DataHandler();


            handler.queryId= (TextView) row.findViewById(R.id.queryId);
            handler.queryCatagory=(TextView) row.findViewById(R.id.querycatagory);
            handler.places=(TextView) row.findViewById(R.id.place);

            row.setTag(handler);
        }
        else{
            handler= (DataHandler) row.getTag();
        }
        ViewAdvisorDataProvider viewAdvisorDataProvider;
        viewAdvisorDataProvider= (ViewAdvisorDataProvider) this.getItem(position);

        handler.queryId.setText(viewAdvisorDataProvider.getQueryId());
        handler.queryCatagory.setText(viewAdvisorDataProvider.getQueryCategory());
        handler.places.setText(viewAdvisorDataProvider.getPlace());




        return row;





    }



}
