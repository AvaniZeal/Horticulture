package com.example.ferooz.horticulture.pojoclasses;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ferooz.horticulture.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rajeshwari on 4/1/2017.
 */

public class AdvisorAdapter extends ArrayAdapter {


RoundableImage roundableImage;
    List list=new ArrayList();


    public AdvisorAdapter(Context context, ArrayList<HashMap<String, String>> advisorList, int resource, String[] strings) {
        super(context, resource);
    }

    static  class DataHandler{

        TextView images;

        TextView dates;
        ImageView bitmap;
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
        row = convertView;
        DataHandler handler;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.advisor_row_layout, parent, false);
            handler = new DataHandler();
            handler.images = (TextView) row.findViewById(R.id.advisorImages);
            handler.dates = (TextView) row.findViewById(R.id.advised_dates);
            handler.bitmap = (ImageView) row.findViewById(R.id.bitmap);
            row.setTag(handler);
        } else {
            handler = (DataHandler) row.getTag();
        }
        AdvisorDataProvider dataProvider;
        dataProvider = (AdvisorDataProvider) this.getItem(position);
        handler.images.setText(dataProvider.getAdvisor_image());
        handler.dates.setText(dataProvider.advised_date);

        Bitmap bm=BitmapFactory.decodeByteArray(dataProvider.getBitmap(), 0, dataProvider.getBitmap().length);

        roundableImage=new RoundableImage(bm);
        handler.bitmap.setImageDrawable(roundableImage);



        return row;


    }


    }
