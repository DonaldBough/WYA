package edu.purdue.dbough.wya;


import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.support.v7.widget.*;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import java.util.ArrayList;

/**
 * Lets user search and select a friend from a listView.
 * Sends name to RequestListenerActivity, then to CreateRequestFragment
 */
public class SearchFriendFragment extends ListFragment{

    private ListView lv;
    ArrayAdapter<String> adapter;
    EditText inputSearch;
    private ArrayList<String> friends; //List of all possible friends
    OnFriendSelectedListener mCallback;
    Toolbar toolbar;


    /**
     * Interface implemented in RequestActivity, so when the user selects a friend
     * SearchFriendFragment can tell RequestActivity what friend was selected
     */
    public interface OnFriendSelectedListener {
        public void onFriendForRequestSelected(String name);
    }

    /**
     * Make sure RequestActivity, or other activties using this fragment implement the
     * interface so SearchFriendFragment can communicate between the two
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnFriendSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    public SearchFriendFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_friend_search, container, false);
        toolbar = (android.support.v7.widget.Toolbar) getActivity().findViewById(R.id.request_toolbar);

        // Add fake data for now
        friends = new ArrayList<>();
        friends.add("Donald Trump");
        friends.add("Ariana Grande");
        friends.add("Alice Cooper");
        friends.add("Ronald McDonald");
        friends.add("Bob TheBuilder");
        friends.add("Bill Gates");
        friends.add("Ron Swanson");

        lv = (ListView) view.findViewById(android.R.id.list);
        inputSearch = (EditText) getActivity().findViewById(R.id.inputSearch);

        // Adding items to listview
        adapter = new ArrayAdapter<>(view.getContext(), R.layout.request_row_layout, R.id.requestSender, friends);
        lv.setAdapter(adapter);

        /**
         * Enabling Search Filter
         * */
        inputSearch.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                // When user changed the Text
                toolbar.setTitle("To    " + cs);
                SearchFriendFragment.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
        });

        return view;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);

        //Load the name and display a message
        String name = (String) lv.getItemAtPosition(position);

        //Send name to RequestActivity through our fancy interface
        mCallback.onFriendForRequestSelected(name);

    }

}
