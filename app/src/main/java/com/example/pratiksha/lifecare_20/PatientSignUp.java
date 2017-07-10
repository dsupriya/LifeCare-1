package com.example.pratiksha.lifecare_20;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PatientSignUp extends AppCompatActivity implements View.OnClickListener {
    EditText name,username,password,contact,confirm,age;
    Button pregister;
    public static Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_sign_up);
        name=(EditText)findViewById(R.id.pname_et);
        username=(EditText)findViewById(R.id.pusername_et);
        password=(EditText)findViewById(R.id.ppassword_et);
        confirm=(EditText)findViewById(R.id.pconfirm_et);
        contact=(EditText)findViewById(R.id.pcontact_et);
        age=(EditText)findViewById(R.id.page_et);
        context=this;
        pregister=(Button)findViewById(R.id.patientreg_bt);
        pregister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view)
    {

        String full_nm=name.getText().toString();
        String usernm=username.getText().toString();
        String pass=password.getText().toString();
        String cpass=confirm.getText().toString();
        String cont=contact.getText().toString();

        String ag=age.getText().toString();

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
        else{



        if(view.getId()==R.id.patientreg_bt) {
            BackgroundTask bg = new BackgroundTask(this);
            String method = "pregister";
            if(name.getText().toString().isEmpty())
            {
                Toast.makeText(getApplicationContext(), "Invalid input", Toast.LENGTH_SHORT).show();

            }
            else
            {
                bg.execute(method, name.getText().toString(), username.getText().toString(), password.getText().toString(), contact.getText().toString()
                        , age.getText().toString());}



        }

    }}
}
