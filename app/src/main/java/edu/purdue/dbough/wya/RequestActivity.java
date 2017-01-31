package edu.purdue.dbough.wya;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RequestActivity extends AppCompatActivity
        implements FriendSearchFragment.OnFriendSelectedListener,
        CreateRequestFragment.OnFinishedRequestListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        //Start with FriendSearchFragment
        FriendSearchFragment newFragment = new FriendSearchFragment();
        getFragmentManager().beginTransaction().replace(R.id.fragment_container, newFragment)
                .commit();
    }

    /**
     * Callback from FriendSearchFragment. Happens when user clicks on a name from list.
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
