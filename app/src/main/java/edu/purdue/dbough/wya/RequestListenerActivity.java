package edu.purdue.dbough.wya;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RequestListenerActivity extends AppCompatActivity
        implements FriendSearchFragment.OnFriendSelectedListener,
        CreateRequestFragment.OnFinishedRequestListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        FriendSearchFragment newFragment = new FriendSearchFragment();
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, newFragment).commit();

    }

    @Override
    public void onFriendForRequestSelected(String name) {
        //open CreateRequest
    }

    @Override
    public void onFinishedRequest() {
        //go back to home activity and tab
    }
}
