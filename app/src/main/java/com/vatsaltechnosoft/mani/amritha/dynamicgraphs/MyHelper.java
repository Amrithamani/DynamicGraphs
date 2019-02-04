package com.vatsaltechnosoft.mani.amritha.dynamicgraphs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Amritha on 7/24/18.
 */
public class MyHelper extends SQLiteOpenHelper {

    // Database Name
    public static final String DATABASE_NAME = "DynamicGraphs.db";

    // table name
    public static final String TABLE_NAME = "MyTable";

    public static final String COLUMN_USER_ID = "id";

    public static final String COLUMN_X_VALUES = "xValues";

    public static final String COLUMN_Y_VALUES = "yValues";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    private Context context;


    public MyHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
                + COLUMN_USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_X_VALUES + " INTEGER,"
                + COLUMN_Y_VALUES + " INTEGER" +
                ")";

        sqLiteDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(sqLiteDatabase);
    }

    public void insertValues(int x, int y) {

        SQLiteDatabase database = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(COLUMN_X_VALUES, x);

        values.put(COLUMN_Y_VALUES, y);

        // Inserting Row
        database.insert(TABLE_NAME, null, values);
        database.close();

    }

    //creating cursor method

    public Cursor getData() {

        //reading database

        SQLiteDatabase db = this.getReadableDatabase();

        //getting all the data inside Table by particular order

        Cursor cur = db.rawQuery("select * from " + TABLE_NAME, null);

        return cur;//returning cursor
    }
}
