package edu.purdue.dbough.wya;

import android.support.v4.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.*;
import android.widget.*;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;
import java.util.ArrayList;


public class HomeFragment extends ListFragment {

    ArrayList<GpsRequest> requestList;
    private EventAdapter adapter;
    private String selectedUser;
    private int selectedItem;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //Fake data for temp list. Use firebase here
        String hours1 = "3 hours left";
        String hours2 = "2 days left";
        String hours3 = "30 min left";

        requestList = new ArrayList<>();
        requestList.add(new GpsRequest("Meeting for some ice cream! ","Bob Ross", R.drawable.user1, hours1));
        requestList.add(new GpsRequest("Meeting for some ice cream! ","Sheila Bikey", R.drawable.user2,hours2));
        requestList.add(new GpsRequest("Meeting for some ice cream! ","Co-Worker Bob", R.drawable.user3,hours3));
        requestList.add(new GpsRequest("Meeting for some ice cream! !","US Goverment", R.drawable.user4,hours2));

        adapter = new EventAdapter(getActivity(),R.layout.user_row_layout, requestList);
        setListAdapter(adapter);

        requestList.add(new GpsRequest("I added this after setListAdapter()","Developer", R.drawable.user5, hours2));
        requestList.add(new GpsRequest("Going out on the town","Bob", R.drawable.user6,hours3));
        requestList.add(new GpsRequest("Going out on the town","Sheila Bikey", R.drawable.user7,hours2));
        requestList.add(new GpsRequest("Going out on the town","Co-Worker Bob", R.drawable.user8,hours3));
        requestList.add(new GpsRequest("Going out on the town!","US Goverment", R.drawable.user9,hours2));
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
                rowView = inflater.inflate(R.layout.user_row_layout,parent,false);
            else
                rowView = convertView;

            //Setup rowView UI elements with parts from GpsRequest class
            //This is why we can't just use ArrayAdapter
            TextView textView = (TextView)rowView.findViewById(R.id.name);
            textView.setText(gpsRequest.getRequestSender());

            TextView textView2 = (TextView)rowView.findViewById(R.id.reason);
            textView2.setText(gpsRequest.getRequestTime()); //TODO Put field "reason" in GPS request

            TextView textView3 = (TextView)rowView.findViewById(R.id.hours_remaining);
            textView3.setText(gpsRequest.getDayRequested());

            CircularImageView requesterPicture = (CircularImageView) rowView.findViewById(R.id.profile_picture);
            Glide.with(getContext()).load(gpsRequest.getRequesterPicture()).into(requesterPicture);


            return rowView; //This returned view will become a row in our ListView
        }
    }


}
