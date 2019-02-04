package com.vatsaltechnosoft.mani.amritha.dynamicgraphs;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Amritha on 7/24/18.
 */
public class AddValues extends AppCompatActivity {

    MyHelper helper;
    SQLiteDatabase database;
    int xVal, yVal;
    private EditText xValue, yValue;
    private Button insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_values);

        xValue = findViewById(R.id.x_value);

        yValue = findViewById(R.id.y_value);

        insert = findViewById(R.id.insert_value);

        helper = new MyHelper(this);

        database = helper.getWritableDatabase();

        insertValue();
    }

    private void insertValue() {
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //initializing Integer variable

                int errorStep = 0;

                //Converting EditText values to int

                xVal = Integer.parseInt(String.valueOf(xValue.getText()));

                yVal = Integer.parseInt(String.valueOf(yValue.getText()));

                // Checking for validation

                if (xValue.length() < 1) {
                    errorStep++;
                    xValue.setError("Provide a value.");
                }

                if (yValue.length() < 1) {
                    errorStep++;
                    yValue.setError("Provide a value.");
                }

                //If there is any error in validation it won't insert or update
                if (errorStep == 0) {

                    //calling method in database class passing title, description and date

                    helper.insertValues(xVal, yVal);//calling insert method in Database

                    //creating Toast
                    Toast.makeText(getApplicationContext(), "Values Added.", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Try again", Toast.LENGTH_SHORT).show();
                }

                finish();//finishing after it's done go back to previous activity
            }
        });
    }
}
