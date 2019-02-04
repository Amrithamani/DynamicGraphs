package com.vatsaltechnosoft.mani.amritha.dynamicgraphs;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    public static String KEY_ID = "ListID";
    public static String KEY_X_VALUE = "ListXValue";

    //Initializing Activity
    public static String KEY_Y_VALUE = "ListYValue";

    //Initializing Database
    ListView listView;

    //Initializing Hash Map Array List
    Activity activity;

    //Initializing all table Contents
    MyHelper mydb;
    ArrayList<HashMap<String, String>> list = new ArrayList<>();
    private FloatingActionButton mAddReminderButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = MainActivity.this;//Setting Activity to MainActivity class

        //creating instance of database using MainActivity

        mydb = new MyHelper(activity);

        //finding id for List View Items

        listView = findViewById(R.id.list);

        mAddReminderButton = findViewById(R.id.fab);

        mAddReminderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), AddValues.class);
                startActivity(intent);
            }
        });
    }

    // Implement method onCreateOptionsMenu

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.menu, menu);//inflating menu layout in actionbar

        return true;
    }

    // Implement method onOptionsItemSelected

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (list.size() >= 5) {

            switch (item.getItemId()) {

                //checking the id to match action_complete

                case R.id.action_line_graph:
                    lineGraph();
                    break;

                case R.id.action_bar_graph:
                    barGraph();
                    break;

                case R.id.action_points_graph:
                    pointGraph();
                    break;

                default:
                    return super.onOptionsItemSelected(item);
            }
            return true;
        }

        Toast.makeText(activity, "Enter minimum 5 values ", Toast.LENGTH_SHORT).show();

        return false;


    }

    private void pointGraph() {

        Intent intent = new Intent(this, PointsGraph.class);

        startActivity(intent);

    }

    private void barGraph() {

        Intent intent = new Intent(this, BarGraph.class);

        startActivity(intent);

    }

    private void lineGraph() {

        Intent intent = new Intent(this, LineGraph.class);

        startActivity(intent);

    }

    //creating instance of Database using Main Activity
    public void populateData() {
        mydb = new MyHelper(activity);

    }

    //After the page loaded it will update with this class

    @Override
    public void onResume() {
        super.onResume();
        LoadTask loadTask = new LoadTask();//creating a instance of LoadTask class
        loadTask.execute();//executing that class

        populateData();//calling the method.

    }

    //creating a class extends AsyncTask runs in background

    public void loadDataList(Cursor cursor, ArrayList<HashMap<String, String>> dataList) {
        //checking whether  the cursor is not equal to null

        if (cursor != null) {
            //moving to first position in cursor

            cursor.moveToFirst();

            //cursor will go till its last positon

            while (cursor.isAfterLast() == false) {

                //creating another HashMap variable mapToday

                HashMap<String, String> mapToday = new HashMap<>();

                //putting Strings inside mapToday

                mapToday.put(KEY_ID, cursor.getString(0).toString());
                mapToday.put(KEY_X_VALUE, cursor.getString(1).toString());
                mapToday.put(KEY_Y_VALUE, cursor.getString(2).toString());

                dataList.add(mapToday);//adding mapToday Variables to ArrayList
                cursor.moveToNext();//moving to next
            }
        }
    }

    public void loadListView(ListView listView, final ArrayList<HashMap<String, String>> dataList) {
        //creating instance of Adapter passing Arguments MainActivity class and ArrayList

        ValuesTaskAdapter adapter = new ValuesTaskAdapter(activity, dataList);

        //setting the Adapter

        listView.setAdapter(adapter);

    }

    public class LoadTask extends AsyncTask<String, Void, String> {
        @Override
        protected void onPreExecute() {

            list.clear();//clearing the list variable using Hash Map

            super.onPreExecute();

        }

        protected String doInBackground(String... args) {
            String xml = "";

            Cursor today = mydb.getData();//calling the getData method in Database

            loadDataList(today, list);//calling method passing cursor and list String variables

            return xml;
        }

        @Override
        protected void onPostExecute(String xml) {


            loadListView(listView, list);//calling the method passing List View and Hash Map String

        }
    }
}
