package com.example.subhash.sqlitewithsearch.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.subhash.sqlitewithsearch.Model.DetailGetSet;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {


    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "detail_db";

    private String TABLE_DETAIL = "detail";

    // column_name
    public String name = "name";
    public String age = "age";
    public String contact = "contact";
    public String email = "email";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE =
                "CREATE TABLE " + TABLE_DETAIL + "("
                        + name + " TEXT, "
                        + age + " TEXT, "
                        + contact + " TEXT, "
                        + email + " TEXT "
                        + ")";


        // create notes table
        db.execSQL(CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAIL);

        // Create tables again
        onCreate(db);
    }

    public void addDetail(DetailGetSet detailGetSet) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(name,detailGetSet.getName());
        values.put(age,detailGetSet.getAge());
        values.put( contact,detailGetSet.getConatct());
        values.put( email,detailGetSet.getEmail());

        // insert row
        db.insert(TABLE_DETAIL,null, values);
        // close db connection
        db.close();
    }

    public List<DetailGetSet> getAllDetail(){
        List<DetailGetSet> detail_array = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery = " SELECT * FROM "+ TABLE_DETAIL;

        Cursor cursor      = db.rawQuery(selectQuery, null);
        if(cursor!=null){

            if(cursor.moveToFirst())

                do {

                    DetailGetSet detailGetSet = new DetailGetSet();
                    detailGetSet.setName(cursor.getString(0));
                    detailGetSet.setAge(cursor.getString(1));
                    detailGetSet.setConatct(cursor.getString(2));
                    detailGetSet.setEmail(cursor.getString(3));

                    //Add movie details to list
                    detail_array.add(detailGetSet);

                    // get the data into array, or class variable
                } while (cursor.moveToNext());

        }


        cursor.close();
        db.close();

        return detail_array;
    }


    public int deleteDetail(String conatctNo) {

        SQLiteDatabase db = this.getWritableDatabase();
         db.execSQL("DELETE FROM " + TABLE_DETAIL+ " WHERE "+contact+"='"+conatctNo+"'");
         db.close();
//        return db.delete(TABLE_DETAIL, contact + "=" + conatctNo, null);
        return 0;


    }
}
