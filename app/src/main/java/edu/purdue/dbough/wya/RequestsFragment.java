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
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class RequestsFragment extends ListFragment{

    ArrayList<GpsRequest> requestList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requests, container, false);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String strDate = dateFormat.format(date);

        //Fake data for temp list. Use firebase here
        requestList = new ArrayList<>();
        requestList.add(new GpsRequest("Waiting to leave for Hackathon!","Bob", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("We were supposed to meet for dinner","Sheila", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("Someone let the dogs out","Co-Worker Bob", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("The president is missing!","US Goverment", R.drawable.alicecooper_avatar,date));

        EventAdapter adapter = new EventAdapter(getActivity(),R.layout.home_row_layout, requestList);
        setListAdapter(adapter);

        requestList.add(new GpsRequest("I added this after setListAdapter()","Developer", R.drawable.alicecooper_avatar, date));
        requestList.add(new GpsRequest("Waiting to leave for Hackathon!","Bob", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("We were supposed to meet for dinner","Sheila", R.drawable.alicecooper_avatar, date));
        requestList.add(new GpsRequest("Someone let the dogs out","Co-Worker Bob", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("The president is missing!","US Goverment", R.drawable.alicecooper_avatar,date));
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
            GpsRequest gpsRequest = values.get(position);
            //Inflating views can be slow, so android lets us recycle old views
            if(convertView == null)
                rowView = inflater.inflate(R.layout.home_row_layout,parent,false);
            else
                rowView = convertView;

            //Setup rowView UI elements with parts from GpsRequest class
            //This is why we can't just use ArrayAdapter

            //Set the requester of the event
            TextView textView = (TextView)rowView.findViewById(R.id.requestSender);
            //Load the event name from the position array
            textView.setText(gpsRequest.getRequestSender());

            TextView textView2 = (TextView)rowView.findViewById(R.id.requestReason);
            textView2.setText(gpsRequest.getRequestReason());
            //Set the swag description
            TextView textView3 = (TextView)rowView.findViewById(R.id.timeRequested);
            textView3.setText(gpsRequest.getTimeRequestedString());
            //Set the profile picture using glide
            CircularImageView requesterPicture = (CircularImageView) rowView.findViewById(R.id.circularImageView);
            Glide.with(getContext()).load(gpsRequest.getRequesterPicture()).into(requesterPicture);

            return rowView; //This returned view will become a row in our ListView
        }
    }

}
