package com.example.pratiksha.lifecare_20;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.LinkedList;

/**
 * Created by pratiksha on 7/11/16.
 */
public class CustomAdapter extends ArrayAdapter
{
    private LinkedList<MyDonorList> singleitem;
    public CustomAdapter(Context context, LinkedList<MyDonorList> list)
    {

        super(context, R.layout.activity_donor_coustom,list);
        singleitem=list;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater list_inflater=LayoutInflater.from(getContext());
        View coustemview = list_inflater.inflate(R.layout.activity_donor_coustom,parent,false);


        TextView donarname_cstm=(TextView) coustemview.findViewById(R.id.namecstm_txt);
        TextView donorcontact_cstm =(TextView) coustemview.findViewById(R.id.contactcstm_txt);




        donarname_cstm.setText(singleitem.get(position). getDonarName());
        donorcontact_cstm.setText(singleitem.get(position).getDonorContact());

        return coustemview;


    }
}

