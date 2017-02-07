package edu.purdue.dbough.wya;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class RequestActivity extends AppCompatActivity
        implements SearchFriendFragment.OnFriendSelectedListener,
        CreateRequestFragment.OnFinishedRequestListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        //Setting up the toolbar
        toolbar = (Toolbar) findViewById(R.id.request_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("To");

        //Start with SearchFriendFragment
        SearchFriendFragment newFragment = new SearchFriendFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, newFragment)
                .commit();
    }

    /**
     * Callback from SearchFriendFragment. Happens when user clicks on a name from list.
     * Sends user to fragment where they create the request, set request date etc
     * @param name Name of the selected user the request is for
     */
    @Override
    public void onFriendForRequestSelected(String name) {
        CreateRequestFragment newFragment = new CreateRequestFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, newFragment)
                .commit();
    }

    /**
     * Callback from CreateRequestFragment. Go back to MainActivity
     */
    @Override
    public void onFinishedRequest() {
        finish();

    }
}
