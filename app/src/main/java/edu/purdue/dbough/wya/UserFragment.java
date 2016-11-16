package edu.purdue.dbough.wya;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class UserFragment extends ListFragment{

    ArrayList<GpsRequest> requestList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String strDate = dateFormat.format(date);

        //Fake data for temp list. Use firebase here
        requestList = new ArrayList<>();
        requestList.add(new GpsRequest("Waiting to leave for Hackathon!","Bob", date));
        requestList.add(new GpsRequest("We were supposed to meet for dinner","Sheila", date));
        requestList.add(new GpsRequest("Someone let the dogs out","Co-Worker Bob", date));
        requestList.add(new GpsRequest("The president is missing!","US Goverment", date));

        EventAdapter adapter = new EventAdapter(getActivity(),R.layout.row_layout, requestList);
        setListAdapter(adapter);

        requestList.add(new GpsRequest("I added this after setListAdapter()","Developer", date));
        requestList.add(new GpsRequest("Waiting to leave for Hackathon!","Bob", date));
        requestList.add(new GpsRequest("We were supposed to meet for dinner","Sheila", date));
        requestList.add(new GpsRequest("Someone let the dogs out","Co-Worker Bob", date));
        requestList.add(new GpsRequest("The president is missing!","US Goverment", date));
        adapter.notifyDataSetChanged();
        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Load the event name and display a message
        String name = requestList.get(position).getRequestSender();
        Toast.makeText(getActivity(), "List item #"+position+" was clicked." +
                " Name: "+name,Toast.LENGTH_SHORT).show();
    }

    /**
     * Converts a list of events into row Views to be placed in the ListView.
     * Most of the work is already done for us in ArrayAdapter, but we need
     * to hook it up to our specific layout
     */
    private class EventAdapter extends ArrayAdapter<GpsRequest> {
        private ArrayList<GpsRequest> values;


        public EventAdapter(Context context, int resource, ArrayList<GpsRequest> events) {
            super(context, resource, events);
            values = events;
        }

        /**
         * Generates the view for a particular item
         * We don't call this ourselves, it's all handled by the ListView automatically.
         * We just need to say where the data should go
         */
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View rowView;   //rowView represents one row in our ListView

            //Inflating views can be slow, so android lets us recycle old views
            if(convertView == null)
                rowView = inflater.inflate(R.layout.row_layout,parent,false);
            else
                rowView = convertView;

            //Setup rowView UI elements with parts from GpsRequest class
            //This is why we can't just use ArrayAdapter

            //Set the requester of the event
            TextView textView = (TextView)rowView.findViewById(R.id.requestSender);
            textView.setText(values.get(position).getRequestSender()); //Load the event name from the position array

            TextView textView2 = (TextView)rowView.findViewById(R.id.requestReason);
            textView2.setText(values.get(position).getRequestReason());
            //Set the swag description
            TextView textView3 = (TextView)rowView.findViewById(R.id.timeRequested);
            textView3.setText(values.get(position).getTimeRequestedString());

            return rowView; //This returned view will become a row in our ListView
        }
    }

}
