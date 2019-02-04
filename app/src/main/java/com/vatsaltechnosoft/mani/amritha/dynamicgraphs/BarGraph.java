package com.vatsaltechnosoft.mani.amritha.dynamicgraphs;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

/**
 * Created by Amritha on 7/24/18.
 */
public class BarGraph extends AppCompatActivity {

    GraphView graphView;

    BarGraphSeries<DataPoint> series;

    Cursor cursor;

    MyHelper mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bar_graph);

        graphView = findViewById(R.id.barGraph);

        //creating instance of database using MainActivity

        mydb = new MyHelper(this);

        cursor = mydb.getData();

        series = new BarGraphSeries<>(new DataPoint[0]);

        series.resetData(getData());

        graphView.addSeries(series);
    }

    private DataPoint[] getData() {
        //Read data from database
        //calling the getData method in Database

        DataPoint[] dp = new DataPoint[cursor.getCount()];

        for (int i = 0; i < cursor.getCount(); i++) {

            cursor.moveToNext();

            dp[i] = new DataPoint(cursor.getInt(0), cursor.getInt(1));

        }

        return dp;
    }

}
