package com.firstapp.joel.myquizz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by joel on 11/5/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mydb";                                       // Database Name
    private static final String TABLE_NEW = "USER";                                                 // User table name

    private static DatabaseHelper dbHelper;

    //-------------------------------------------------------------------------------------------------------------------------------------
    private static final String COLUMN_USER = "username";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";

    //----------------------------------------------------------------------------------------------------------------------------------
    public DatabaseHelper(Context context)
    {
        super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    //public static synchronized
    public static synchronized DatabaseHelper createInstance(Context context) {
        if (dbHelper == null) {
            dbHelper = new DatabaseHelper(context.getApplicationContext());
        }

        Log.i("Tag for dbHelper", "it is created");

        return dbHelper;
    }

    public static synchronized DatabaseHelper getInstance() {
        return dbHelper;
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + TABLE_NEW + "(" +
                COLUMN_USER + " TEXT," +
                COLUMN_NAME + " TEXT," +
                COLUMN_EMAIL + " TEXT," +
                COLUMN_PASSWORD + " TEXT" + ")";
        db.execSQL(query);
        Log.i("database ", "created");
    }

    public void insertContact(Contact c)
    {
        SQLiteDatabase database = DatabaseHelper.getInstance().getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(COLUMN_USER,c.getUsername());
        values.put(COLUMN_NAME,c.getName());
        values.put(COLUMN_EMAIL,c.getEmail());
        values.put(COLUMN_PASSWORD,c.getPassword());
        database.insert(DatabaseHelper.TABLE_NEW,null,values);
        Log.i("TAG for inserted", " inserted");
        //database.close();
    }


    public String searchPass(String str) {
        SQLiteDatabase db = DatabaseHelper.getInstance().getWritableDatabase();
        String query = "SELECT * from " + TABLE_NEW ;
        Cursor cursor = db.rawQuery(query, null);

        String username,b;
        b = "not found";
        cursor.moveToFirst();
        if(cursor.getCount() > 0)
        {
            do{
               username = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_USER));
                if(str.equals(username))
                {
                    b = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_PASSWORD));
                    break;
                }
            } while(cursor.moveToNext());
        }
        return b;
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1)
    {
        String query = "DROP TABLE IF EXISTS "+TABLE_NEW;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }
}
