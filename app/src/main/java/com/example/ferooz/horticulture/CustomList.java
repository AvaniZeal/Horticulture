package com.example.ferooz.horticulture;

/**
 * Created by ferooz on 05-04-2017.
 */
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Belal on 7/22/2015.
 */
public class CustomList extends ArrayAdapter<String> {
    private String[] querytitle;
    private String[] query;

    private String[] status;;
    private Activity context;

    public CustomList(Activity context, String[] querytitle, String[] status, String[] query) {
        super(context, R.layout.list_layout, querytitle);
        this.context = context;
        this.querytitle = querytitle;
        this.status = status;
        this.query = query;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.list_layout, null, true);
        TextView textqueryTitle = (TextView) listViewItem.findViewById(R.id.queryTitle);
        TextView textQuery = (TextView) listViewItem.findViewById(R.id.query);
        TextView textstatus = (TextView) listViewItem.findViewById(R.id.status);


        textqueryTitle.setText(querytitle[position]);
        textQuery.setText(query[position]);
        textstatus.setText(status[position]);
        return  listViewItem;
    }
}
