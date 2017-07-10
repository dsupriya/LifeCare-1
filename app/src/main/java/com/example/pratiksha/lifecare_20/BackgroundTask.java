package com.example.pratiksha.lifecare_20;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * Created by Inspiron on 02-09-2016.
 */
public class BackgroundTask extends AsyncTask<String, Void, String>
{

    public String method,type,username_str,password_str,username,password,registration;
    public static String[] donordetail_name;
    public static String[] donordetail_contact;
    public static String[] alldonordeatil;
    public static  LinkedList <MyDonorList> blglist;

    Context ctx;


    public BackgroundTask(Context ctx) {
        this.ctx = ctx;
    }

    @Override
    protected void onPreExecute()
    {



    }

    @Override
    protected String doInBackground(String... params) {

        String login_url = "http://lifecare.esy.es/swara/login.php";
        String reg_url = "http://lifecare.esy.es/swara/signin.php";
        String preg_url="http://lifecare.esy.es/swara/psignin.php";
        String finddonor_url="http://lifecare.esy.es/swara/finddonor.php";
        String donorlist_url="http://lifecare.esy.es/swara/donorlist.php";
        String latlon_url="http://lifecare.esy.es/swara/latlon.php";


        method = params[0];

         if (method.equals("login"))
        {
            username_str = params[1];
            password_str = params[2];
            type=params[3];

            try {
                URL url = new URL(login_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(outputStream, "UTF-8")));

                String data = URLEncoder.encode("username_str", "UTF-8") + "=" + URLEncoder.encode(username_str, "UTF-8") + "&" +
                        URLEncoder.encode("password_str", "UTF-8") + "=" + URLEncoder.encode(password_str, "UTF-8") + "&" +
                        URLEncoder.encode("type", "UTF-8") + "=" + URLEncoder.encode(type, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
        if (method.equals("register")) {
            String name = params[1];
            username = params[2];
            password = params[3];
            String contact=params[4];
            String blood=params[5];
            String age=params[6];
            String lat=params[7];
            String lon=params[8];

            registration = params[4];
            try {

                URL url = new URL(reg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Log.e("Connection","Established");

                httpURLConnection.setRequestMethod("POST");httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(OS, "UTF-8")));

                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(username, "UTF-8") + "&" +
                        URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8") + "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&" +
                        URLEncoder.encode("bloodgroup", "UTF-8") + "=" + URLEncoder.encode(blood, "UTF-8") + "&" +
                        URLEncoder.encode("latitude","UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8") + "&" +
                        URLEncoder.encode("longitude", "UTF-8") + "=" + URLEncoder.encode(lon, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else  if (method.equals("pregister")) {
            String name = params[1];
            String pusername = params[2];
            String ppassword = params[3];
            String contact=params[4];
            String age=params[5];

            try {

                URL url = new URL(preg_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Log.e("Connection","Established");

                httpURLConnection.setRequestMethod("POST");httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(OS, "UTF-8")));

                String data = URLEncoder.encode("name", "UTF-8") + "=" + URLEncoder.encode(name, "UTF-8") + "&" +
                        URLEncoder.encode("username", "UTF-8") + "=" + URLEncoder.encode(pusername, "UTF-8") + "&" +
                        URLEncoder.encode("age", "UTF-8") + "=" + URLEncoder.encode(age, "UTF-8") + "&" +
                        URLEncoder.encode("contact", "UTF-8") + "=" + URLEncoder.encode(contact, "UTF-8") + "&" +
                        URLEncoder.encode("password", "UTF-8") + "=" + URLEncoder.encode(ppassword, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        else  if (method.equals("finddonor")) {
            String bloodgrp = params[1];
            String lat = params[2];
            String lon = params[3];
            //Toast.makeText(ctx,"inside donor",Toast.LENGTH_SHORT).show();

            try {

                URL url = new URL(finddonor_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Log.e("Connection","Established");

                httpURLConnection.setRequestMethod("POST");httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(OS, "UTF-8")));

                String data = URLEncoder.encode("bloodgrp", "UTF-8") + "=" + URLEncoder.encode(bloodgrp, "UTF-8") + "&" +
                        URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8") + "&" +
                        URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(lon, "UTF-8") ;

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null)
                {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

       else if (method.equals("donorlist"))
        {
            String bloodgrp = params[1];
            try {

                URL url = new URL(donorlist_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(OS, "UTF-8")));

               String data = URLEncoder.encode("bloodgroup", "UTF-8") + "=" + URLEncoder.encode(bloodgrp, "UTF-8");


                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        else  if (method.equals("latlon")) {
            String id = params[1];
            String lat = params[2];
            String lon = params[3];
            //Toast.makeText(ctx,"inside donor",Toast.LENGTH_SHORT).show();

            try {

                URL url = new URL(latlon_url);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                Log.e("Connection","Established");

                httpURLConnection.setRequestMethod("POST");httpURLConnection.setDoOutput(true);
                OutputStream OS = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter((new OutputStreamWriter(OS, "UTF-8")));

                String data = URLEncoder.encode("id", "UTF-8") + "=" + URLEncoder.encode(id, "UTF-8") + "&" +
                        URLEncoder.encode("lat", "UTF-8") + "=" + URLEncoder.encode(lat, "UTF-8") + "&" +
                        URLEncoder.encode("lon", "UTF-8") + "=" + URLEncoder.encode(lon, "UTF-8") ;

                bufferedWriter.write(data);
                bufferedWriter.flush();
                bufferedWriter.close();
                OS.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "iso-8859-1"));
                String response = "", line = "";
                while ((line = bufferedReader.readLine()) != null)
                {
                    response += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return response;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }




        return null;
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }


    @Override
    protected void onPostExecute(String result)
    {


            if(result.contains("dregister..."))
            {
                Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
                ((Activity) DonorSignUp.context).startActivity(new Intent(((Activity) DonorSignUp.context), LoginActivity.class));
                ((Activity) DonorSignUp.context).finish();

            }
            else if(result.contains("pregister..."))
            {
                Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
                ((Activity) PatientSignUp.context).startActivity(new Intent(((Activity) PatientSignUp.context), LoginActivity.class));
                ((Activity) PatientSignUp.context).finish();

            }
            else if(result.contains("dregisterfail"))
            {
                Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
            }

            //Toast.makeText(ctx,result, Toast.LENGTH_SHORT).show();

                if(result.contains("Donor"))
                {
                    SharedPreferences sharedPreferences=ctx.getSharedPreferences("user_data",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    //Toast.makeText(ctx,username_str+password_str+type,Toast.LENGTH_LONG).show();
                    editor.putString("username_str",username_str);
                    editor.putString("password_str",password_str);
                    editor.putString("type",type);
                    editor.commit();

                  Toast.makeText(ctx, "Donor logged in ", Toast.LENGTH_SHORT).show();
                    String str[]=result.split("---");

                    DBHelper db=new DBHelper(ctx);

                    boolean bl=db.updateData(str[2],1);


                    ((Activity) LoginActivity.context).startActivity(new Intent(((Activity) LoginActivity.context), AboutLifeCare.class));
                    ((Activity) LoginActivity.context).finish();


                }
               else if(result.contains("Patient"))
                {
                    SharedPreferences sharedPreferences=ctx.getSharedPreferences("user_data",Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    //toast.makeText(ctx,username_str+password_str+type,Toast.LENGTH_LONG).show();
                    editor.putString("username_str",username_str);
                    editor.putString("password_str",password_str);
                    editor.putString("type",type);
                    editor.commit();
                    Toast.makeText(ctx, "Patient logged in", Toast.LENGTH_SHORT).show();
                   ((Activity) LoginActivity.context).startActivity(new Intent(((Activity) LoginActivity.context), RequestBlood.class));
                    ((Activity) LoginActivity.context).finish();
                    String str[]=result.split("---");

                    DBHelper db=new DBHelper(ctx);

                    boolean bl=db.updateData(str[2],1);


                }
            else if(result.contains("Failed"))
                {
                    Toast.makeText(ctx,"Invalid User",Toast.LENGTH_SHORT).show();
                }
            else if(result.contains("!!!"))
                {
                    Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();

                }
            else if(result.contains("Not"))
                {
                    Toast.makeText(ctx,result,Toast.LENGTH_SHORT).show();
                }
                else if(result.contains("&&&"))
                {

                    String []allblg=result.split("&&&");
                    String cnt_str=allblg[0];
                    String allblogdata=allblg[1];

                    int cnt=Integer.parseInt(cnt_str);
                    donordetail_name=new String[cnt];
                     donordetail_contact=new String[cnt];


                   blglist=new LinkedList();

                    alldonordeatil=allblogdata.split("@@@");
                    for(int i=0;i<alldonordeatil.length;i++)
                    {

                        String []tempblog=alldonordeatil[i].split("---");
                        MyDonorList myDonorList=new MyDonorList(tempblog[0],tempblog[1],tempblog[2],tempblog[3],tempblog[4]);

                        blglist.add(myDonorList);


                    }
                    ArrayAdapter adapter= new CustomAdapter(ctx,blglist);
                    DonarList.donor_lst.setAdapter(adapter);


                }





            return;

        }
    }


