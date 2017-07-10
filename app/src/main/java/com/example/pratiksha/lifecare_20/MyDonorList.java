package com.example.pratiksha.lifecare_20;

/**
 * Created by pratiksha on 7/11/16.
 */
public class MyDonorList {
    private String donor_name;
    private String donor_contact;
    private String donor_blood;
    private String donor_lat;
    private String donor_lon;


    public void setDonorName(String donor_name)

    {
        this.donor_name = donor_name;
    }
    public String getDonarName()

    {
        return donor_name;
    }
    public void setDonorContact(String donor_contact)
    {
        this.donor_contact=donor_contact;
    }
    public String getDonorContact()

    {
        return  donor_contact;
    }
    public String getDonorblood()

    {
        return  donor_blood;
    }
    public String getLatLon(){return  donor_lat+" "+donor_lon;}


    MyDonorList(String donor_name,String donor_contact,String donor_lat,String donor_lon,String donor_blood)
    {

        setDonorContact(donor_contact);
        setDonorName(donor_name);
        this.donor_blood=donor_blood;
        this.donor_lat=donor_lat;
        this.donor_lon=donor_lon;

    }

}
