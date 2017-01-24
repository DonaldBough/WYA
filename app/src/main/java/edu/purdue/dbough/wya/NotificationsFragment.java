package edu.purdue.dbough.wya;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;

/**
 * Created by donaldbough on 1/4/17.
 */

public class NotificationsFragment extends ListFragment {

    ArrayList<GpsRequest> notificationList;
    private EventAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications, container, false);

        //Fake data for temp list. Use firebase here
        String date = "Friday";
        notificationList = new ArrayList<>();
        notificationList.add(new GpsRequest("5:35 PM through 8:35 PM","Selena Gomez", R.drawable.user1, date));
        notificationList.add(new GpsRequest("4:35 PM through 10:35 PM","Kim Kardasian", R.drawable.user2, date));
        notificationList.add(new GpsRequest("3:35 PM through 11:35 PM","Adele YoGirl", R.drawable.user9, date));
        notificationList.add(new GpsRequest("6:35 PM through 8:35 PM","Bob Marley", R.drawable.user8, date));

        adapter = new EventAdapter(getActivity(),R.layout.notification_row_layout, notificationList);
        setListAdapter(adapter);

        return view;
    }


    /**
     * Converts a list of events into row Views to be placed in the ListView.
     * Most of the work is already done for us in ArrayAdapter, but we need
     * to hook it up to our specific layout
     */
    private class EventAdapter extends ArrayAdapter<GpsRequest> {
        private ArrayList<GpsRequest> values;


        public EventAdapter(Context context, int resource, ArrayList<GpsRequest> notifications) {
            super(context, resource, notifications);
            values = notifications;
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

            if(convertView == null)
                rowView = inflater.inflate(R.layout.notification_row_layout,parent,false);
            else
                rowView = convertView;

            //Setup rowView UI elements with parts from GpsRequest class
            //This is why we can't just use ArrayAdapter
            TextView requestSender = (TextView)rowView.findViewById(R.id.requestSender);
            requestSender.setText(gpsRequest.getRequestSender());

            TextView date = (TextView)rowView.findViewById(R.id.dateTextView);
            date.setText("Sent a request " + gpsRequest.getDayRequested());

            CircularImageView requesterPicture = (CircularImageView) rowView.findViewById(R.id.circularImageView);
            Glide.with(getContext()).load(gpsRequest.getRequesterPicture()).into(requesterPicture);

            return rowView; //This returned view will become a row in our ListView
        }
    }
}
