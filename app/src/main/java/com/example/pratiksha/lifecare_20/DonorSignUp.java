package com.example.pratiksha.lifecare_20;

import android.content.Context;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class DonorSignUp extends AppCompatActivity implements View.OnClickListener{

    public static Context context;
    EditText name,username,password,confirm,contact,bloodgrp,age;
    Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor_sign_up);
        name=(EditText)findViewById(R.id.dname_et);
        username=(EditText)findViewById(R.id.dusername_et);
        password=(EditText)findViewById(R.id.dpassword_et);
        confirm=(EditText)findViewById(R.id.dconfirm_et);
        contact=(EditText)findViewById(R.id.dcontact_et);
        bloodgrp=(EditText)findViewById(R.id.dblooggroup_et);
        age=(EditText)findViewById(R.id.dage_et);
        context=this;
        register=(Button)findViewById(R.id.donorregistersubmit_bt);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.donorregistersubmit_bt)
        {
            String full_nm=name.getText().toString();
            String usernm=username.getText().toString();
            String pass=password.getText().toString();
            String cpass=confirm.getText().toString();
            String cont=contact.getText().toString();
            String blood=bloodgrp.getText().toString();
            String ag=age.getText().toString();
            int int_age=Integer.parseInt(ag);
            if(full_nm.isEmpty())
                Toast.makeText(getApplicationContext(),"Enter fullname",Toast.LENGTH_SHORT).show();
            else if(usernm.isEmpty())
                Toast.makeText(getApplicationContext(),"Enter username",Toast.LENGTH_SHORT).show();
            else if(pass.isEmpty())
                Toast.makeText(getApplicationContext(),"Enter password",Toast.LENGTH_SHORT).show();
            else if(cpass.isEmpty())
                Toast.makeText(getApplicationContext(),"Enter password again",Toast.LENGTH_SHORT).show();
            else if(cont.isEmpty())
                Toast.makeText(getApplicationContext(),"Enter contact",Toast.LENGTH_SHORT).show();
            else if(blood.isEmpty())
                Toast.makeText(getApplicationContext(),"Enter bloodgroup",Toast.LENGTH_SHORT).show();
            else if(ag.isEmpty())
                Toast.makeText(getApplicationContext(),"Enter age",Toast.LENGTH_SHORT).show();
            else if(!password.getText().toString().equals(cpass))
            {
                Toast.makeText(getApplicationContext(),"Password not matching",Toast.LENGTH_SHORT).show();
                confirm.setText("");
            }
            else if(cont.length()!=10)
            {
                Toast.makeText(getApplicationContext(),"Enter proper contact",Toast.LENGTH_SHORT).show();
                contact.setText("");
            }

           else if(!blood.equals("A-") && !blood.equals("A+") && !blood.equals("AB+") && !blood.equals("AB-") &&
                    !blood.equals("O+") && !blood.equals("O-") && !blood.equals("B+") && !blood.equals("B-")) {
                                            Toast.makeText(getApplicationContext(), "Invalid blood group", Toast.LENGTH_SHORT).show();
                                            bloodgrp.setText("");
                                        }



            else if(int_age>55&&int_age<17)
            {
                Toast.makeText(getApplicationContext(),"Enter proper age",Toast.LENGTH_SHORT).show();
                age.setText("");
            }
            else {

            BackgroundTask bg=new BackgroundTask(this);
            String method="register";


            bg.execute(method,name.getText().toString(),username.getText().toString(),password.getText().toString(),contact.getText().toString(), bloodgrp.getText().toString(), age.getText().toString(),"23.3","45.3");

        }}
    }
}
