package edu.purdue.dbough.wya;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.*;
import android.util.TypedValue;
import android.view.*;
import android.widget.*;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private UsersAdapter UsersAdapter;
    private List<User> userList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        userList = new ArrayList<>();
        UsersAdapter = new UsersAdapter(getContext(), userList);

        //Setup for recycler view
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(7), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(UsersAdapter);
        prepareUsers();

        return view;
    }

    /**
     * Adding few Users for testing
     */
    private void prepareUsers() {
        User a = new User("Alice Cooper", R.drawable.user1, "5 miles away");
        userList.add(a);
        a = new User("Ronald McDonald", R.drawable.user2, "0.8 miles away");
        userList.add(a);
        a = new User("Donald Trump", R.drawable.user3, "Less Than A Mile Away");
        userList.add(a);
        a = new User("Jill Stein", R.drawable.user4, "20 miles away");
        userList.add(a);
        a = new User("Donald Bough", R.drawable.user5, "No Location Found");
        userList.add(a);
        a = new User("Bill Nye", R.drawable.user6, "1 miles away");
        userList.add(a);

        UsersAdapter.notifyDataSetChanged();
    }



    /**
     * RecyclerView item decoration - give equal margin around grid item
     */
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }

    /**
     * Adapter for events_info_card
     */
    class UsersAdapter extends RecyclerView.Adapter<HomeFragment.UsersAdapter.FriendViewHolder> {
        private Context context;
        private List<User> userList;

        public class FriendViewHolder extends RecyclerView.ViewHolder {
            public TextView friendName;
            public CircularImageView profilePicture;

            public FriendViewHolder(View view) {
                super(view);
                friendName = (TextView) view.findViewById(R.id.friend_name);
                profilePicture = (CircularImageView) view.findViewById(R.id.inside_profile);
            }
        }

        public UsersAdapter(Context context, List<User> UserList) {
            this.context = context;
            this.userList = UserList;
        }

        @Override
        public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.friend_info_card, parent, false);

            return new FriendViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final FriendViewHolder holder, int position) {
            User user = userList.get(position);

            // loading user profile pic using Glide library
            //TODO Figure out the best way of permantely cropping profile pictures so Glide can be used
            //Glide.with(context).load(user.getUncroppedProfilePicture()).into(holder.profilePicture);
//            holder.profilePicture = user.getProfilePicture();
            holder.profilePicture.setImageResource(user.getUncroppedProfilePicture());
            holder.friendName.setText(user.getName());
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }


}
