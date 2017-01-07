package edu.purdue.dbough.wya;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.*;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;


public class RequestsFragment extends ListFragment{

    ArrayList<GpsRequest> requestList;
    private EventAdapter adapter;
    private String selectedUser;
    private int selectedItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_requests, container, false);

        //Fake data for temp list. Use firebase here
        String date = "Friday";
        requestList = new ArrayList<>();
        requestList.add(new GpsRequest("5:35 PM through 8:35 PM","Bob Ross", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("5:35 PM through 8:35 PM","Sheila Bikey", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("5:35 PM through 8:35 PM","Co-Worker Bob", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("5:35 PM through 8:35 PM!","US Goverment", R.drawable.alicecooper_avatar,date));

        adapter = new EventAdapter(getActivity(),R.layout.request_row_layout, requestList);
        setListAdapter(adapter);

        requestList.add(new GpsRequest("I added this after setListAdapter()","Developer", R.drawable.alicecooper_avatar, date));
        requestList.add(new GpsRequest("5:35 PM through 8:35 PM","Bob", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("5:35 PM through 8:35 PM","Sheila Bikey", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("5:35 PM through 8:35 PM","Co-Worker Bob", R.drawable.alicecooper_avatar,date));
        requestList.add(new GpsRequest("5:35 PM through 8:35 PM!","US Goverment", R.drawable.alicecooper_avatar,date));
        adapter.notifyDataSetChanged();

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        selectedUser = requestList.get(position).getRequestSender();
        selectedItem = position;
        super.onListItemClick(l, v, position, id);
        registerForContextMenu(l);
        getActivity().openContextMenu(l);
    }

    /**
     * Open a context menu on long press, allows for a listview request
     * to be deleted.
     */
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //Context menu
        menu.setHeaderTitle("Request From " + selectedUser);
        menu.add(Menu.NONE, 0, Menu.NONE, "Delete");
    }

    /**
     * Delete request when delete is selected from context menu
     */
    @Override
    public boolean onContextItemSelected (MenuItem item){
        if (selectedUser != null) {
            requestList.remove(selectedItem);
            adapter.notifyDataSetChanged();
            Toast.makeText(getActivity(), "Deleted request from " + selectedUser,Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
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
                rowView = inflater.inflate(R.layout.request_row_layout,parent,false);
            else
                rowView = convertView;

            //Setup rowView UI elements with parts from GpsRequest class
            //This is why we can't just use ArrayAdapter
            TextView textView = (TextView)rowView.findViewById(R.id.requestSender);
            textView.setText(gpsRequest.getRequestSender());
            TextView textView2 = (TextView)rowView.findViewById(R.id.requestTime);
            textView2.setText(gpsRequest.getRequestTime());
            TextView textView3 = (TextView)rowView.findViewById(R.id.requestDay);
            textView3.setText(gpsRequest.getDayRequested());
            CircularImageView requesterPicture = (CircularImageView) rowView.findViewById(R.id.circularImageView);
            Glide.with(getContext()).load(gpsRequest.getRequesterPicture()).into(requesterPicture);

            return rowView; //This returned view will become a row in our ListView
        }
    }

}
