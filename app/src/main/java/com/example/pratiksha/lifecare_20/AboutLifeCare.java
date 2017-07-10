package com.example.pratiksha.lifecare_20;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class AboutLifeCare extends AppCompatActivity implements View.OnClickListener {

    protected Button whydonate,whodonate,compabileblood;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_life_care);
        whydonate=(Button)findViewById(R.id.why_bt);
        whodonate=(Button)findViewById(R.id.who_bt);
        compabileblood=(Button)findViewById(R.id.com_bt);
        whydonate.setOnClickListener(this);
        whodonate.setOnClickListener(this);
        compabileblood.setOnClickListener(this);




        SharedPreferences sharedPreferences=getSharedPreferences("user_data", Context.MODE_PRIVATE);
        String username=sharedPreferences.getString("username_str","N/A");
        if(username.contains("N/A"))
        {
            Intent intent=new Intent(this,LoginActivity.class);
            startActivity(intent);
            finish();
        }
        else
        {
            if(sharedPreferences.getString("type","N/A").contains("Patient"))
            {
                Intent intent=new Intent(this,RequestBlood.class);
                startActivity(intent);
                finish();
            }

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {


        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.patientmenu, menu);
        invalidateOptionsMenu();


        return true;
    }
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId())
        {

            case R.id.signout_patient:
            {

                DBHelper db=new DBHelper(this);
                boolean flag=db.updateData("",0);

                SharedPreferences sharedPreferences=getSharedPreferences("user_data", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();

                editor.putString("username_str","N/A");
                editor.putString("password_str","N/A");
                editor.putString("type","N/A");
                editor.commit();

                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                finish();
            }
            break;



        }
        return super.onOptionsItemSelected(menuItem);
    }


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.why_bt) {
            Intent i = new Intent(getApplicationContext(), Why.class);
            startActivity(i);

        }
        if (view.getId() == R.id.who_bt) {
            Intent i = new Intent(getApplicationContext(), Who.class);
            startActivity(i);

        }
        if (view.getId() == R.id.com_bt) {
            Intent i = new Intent(getApplicationContext(), Compatible.class);
            startActivity(i);

        }
    }

}
