package com.example.pratiksha.lifecare_20;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Launcher extends AppCompatActivity {

    DBHelper db;
    public static boolean ss;
    static Activity c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        c = this;

        //Create sqlite database object.
        db = new DBHelper(this);
        Cursor c = db.retrieveData();

        //Only one row is allowed in my sqlite. So check if already present. If not, insert data.
        if (c != null && c.getCount() == 0)
            db.insertData();


        Cursor cursor = db.retrieveData();
        cursor.moveToFirst();

        //Get id of user from database
        String id = cursor.getString(cursor.getColumnIndex("id"));
        if (id.equals("")) {
            //If user is already not logged in, switch to Login page
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
        }


        //If user is already logged in, switch to corresponding user's home page
        else {
            if (id.startsWith("100")) {

                startActivity(new Intent(getApplicationContext(), RequestBlood.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(), AboutLifeCare.class));
                finish();
            }
        }

    }}
