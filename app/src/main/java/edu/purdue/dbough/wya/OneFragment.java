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


public class OneFragment extends ListFragment{

    ArrayList<GpsRequest> requestList;
    /**
     * Here we set up the listView.
     * This is sloppy but it's all here just as an example.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Convert the fragment_main xml into an actual view we can render
        View view = inflater.inflate(R.layout.fragment_one, container, false);

        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        String strDate = dateFormat.format(date);

        //Create some fake events to put in the list
        //In reality you would pull these events from Firebase
        //The GpsRequest class is really simple, but in reality you'd have a lot more data for it
        requestList = new ArrayList<>();
        requestList.add(new GpsRequest("BoilerCamp","An unbelievable amount of food", date));
        requestList.add(new GpsRequest("BoilerMake","Literally All The Swag", date));
        requestList.add(new GpsRequest("Ignite","T-Shirts, coming soon!", date));
        requestList.add(new GpsRequest("Some Company","A keychain", date));

        //The adapter converts our data (GiveawayEvents in this case) into rows for our ListView
        //EventAdapter is a private class that extends the ArrayAdapter class (see below)
        EventAdapter adapter = new EventAdapter(getActivity(),R.layout.row_layout, requestList);

        //We have to attach the adapter to the list to make it actually work
        //Because this is a ListFragment we just need to call this one method
        setListAdapter(adapter);

        //It's okay to add more data after setting the adapter, like if the user refreshes the page
        //but we need to let the adapter know that we've changed the data.
        requestList.add(new GpsRequest("Hack Duke","I added this after setListAdapter()", date));
        adapter.notifyDataSetChanged();
        return view;
    }

    /**
     * Called when the user clicks on a list item
     * Here you might switch to another fragment with more
     * details about the events. I just show a message.
     * Because we're using a ListFragment this is super easy.
     */

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
            //Get the inflater. This is used to convert xml layout files into an actual View
            LayoutInflater inflater = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            //rowView represents one row in our ListView
            View rowView;

            //Inflating views can be slow, so android lets us recycle old views
            //This isn't required but Android Studio will complain if you don't
            //If convertView is null, android didn't give us anything to recycle, so we inflate a new view
            //If convertView is set, we don't need to inflate anything new
            if(convertView == null) {
                rowView = inflater.inflate(R.layout.row_layout,parent,false);
            } else {
                rowView = convertView;
            }

            //Depending on our list, we might want to set a picture, check a checkbox, etc....
            //In our case, we just need to set the text for each row

            //Set the requester of the event
            TextView textView = (TextView)rowView.findViewById(R.id.requestSender);
            //Load the event name from the position array
            textView.setText(values.get(position).getRequestSender());

            //Set the swag description
            TextView textView2 = (TextView)rowView.findViewById(R.id.requestReason);
            //Load the swag name from the position array
            textView2.setText(values.get(position).getRequestReason());

            //Set the swag description
            TextView textView3 = (TextView)rowView.findViewById(R.id.timeRequested);
            //Load the swag name from the position array
            textView3.setText(values.get(position).getTimeRequestedString());

            //This returned view will become a row in our ListView
            return rowView;
        }
    }

}
