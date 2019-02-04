package com.vatsaltechnosoft.mani.amritha.dynamicgraphs;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Amritha on 7/24/18.
 */
public class ValuesTaskAdapter extends BaseAdapter {

    //Initializing Activity variable and ArrayList

    private Activity activity;

    private ArrayList<HashMap<String, String>> data;

    //creating constructor for the class having two parameters

    ValuesTaskAdapter(Activity a, ArrayList<HashMap<String, String>> d) {
        activity = a;
        data = d;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ValuesTaskViewHolder holder;

        if (view == null) {
            holder = new ValuesTaskViewHolder();//creating instance of ListTaskViewHolder
            view = LayoutInflater.from(activity).inflate(
                    R.layout.list_adapter, viewGroup, false);//Inflating the to_do_list layout in particular activity

            //Assigning values to all the variables in holder instance variable

            holder.xValue = view.findViewById(R.id.x_id_value);
            holder.yValue = view.findViewById(R.id.y_id_value);
            view.setTag(holder);
        } else {
            holder = (ValuesTaskViewHolder) view.getTag();
        }
        //setting the variables in particular position

        holder.xValue.setId(i);
        holder.yValue.setId(i);

        HashMap<String, String> song;
        song = data.get(i);//Assigning the position of data ArrayList to song ArrayList

        try {

            //setting the Keys from MainActivity to holder

            holder.xValue.setText(song.get(MainActivity.KEY_X_VALUE));
            holder.yValue.setText(song.get(MainActivity.KEY_Y_VALUE));

        } catch (Exception e) {
        }


        return view;//returning the views
    }
}

class ValuesTaskViewHolder {

    //Initializing TextView Variable
    TextView xValue, yValue;

}
