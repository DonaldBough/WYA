package edu.purdue.dbough.wya;


import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreateRequestFragment extends Fragment {
    Button sendButton;
    OnFinishedRequestListener mCallback;

    public CreateRequestFragment() {
        // Required empty public constructor
    }

    /**
     * Interface implemented in RequestActivity, so when the user finished their request,
     * CreateRequestFragment can tell RequestActivity to go back to the home activity/tab
     */
    public interface OnFinishedRequestListener {
        public void onFinishedRequest();
    }

    /**
     * Make sure RequestActivity, or other activties using this fragment implement the
     * interface so CreateRequestFragment can communicate between the two
     * @param activity
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnFinishedRequestListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_request, container, false);

        sendButton = (Button) view.findViewById(R.id.sendRequestButton);

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (mCallback != null) {
                    mCallback.onFinishedRequest();
                }
            }
        });

        return view;
    }

}
