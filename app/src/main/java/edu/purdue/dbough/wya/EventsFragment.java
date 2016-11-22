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
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;


public class EventsFragment extends Fragment {

    private UsersAdapter UsersAdapter;
    private List<User> userList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        userList = new ArrayList<>();
        UsersAdapter = new UsersAdapter(getContext(), userList);

        //Setup for recycler view
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getContext(), 1);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(UsersAdapter);
        prepareUsers();

        return view;
    }

    /**
     * Adding few Users for testing
     */
    private void prepareUsers() {
        int[] covers = new int[]{
                R.drawable.user1,
                R.drawable.user2,
                R.drawable.user3,
                R.drawable.user4,
                R.drawable.user5,
                R.drawable.user6,
                R.drawable.user7,
                R.drawable.user8,
                R.drawable.user9,
                R.drawable.user10,
                R.drawable.user11};

        User a = new User("True Romance", R.drawable.user1, "5 miles away");
        userList.add(a);

        a = new User("Xscpae", R.drawable.user2, "0.8 miles away");
        userList.add(a);

        a = new User("Maroon 5", R.drawable.user3, "Less Than A Mile Away");
        userList.add(a);

        a = new User("Born to Die", R.drawable.user4, "20 miles away");
        userList.add(a);

        a = new User("Honeymoon", R.drawable.user5, "No Location Found");
        userList.add(a);

        a = new User("I Need a Doctor", R.drawable.user6, "1 miles away");
        userList.add(a);

        a = new User("Loud", R.drawable.user7, "Less than a mile away");
        userList.add(a);

        a = new User("Legend", R.drawable.user8, "9 miles away");
        userList.add(a);

        a = new User("Hello", R.drawable.user9, "2 miles away");
        userList.add(a);

        a = new User("Greatest Hits", R.drawable.user10, "No Location Found");
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

    class UsersAdapter extends RecyclerView.Adapter<EventsFragment.UsersAdapter.FriendViewHolder> {
        private Context context;
        private List<User> userList;

        public class FriendViewHolder extends RecyclerView.ViewHolder {
            public TextView title, count;
            public ImageView profilePicture, overflow;

            public FriendViewHolder(View view) {
                super(view);
                title = (TextView) view.findViewById(R.id.title);
                count = (TextView) view.findViewById(R.id.count);
                profilePicture = (ImageView) view.findViewById(R.id.inside_profile);
                overflow = (ImageView) view.findViewById(R.id.overflow);
            }
        }


        public UsersAdapter(Context context, List<User> UserList) {
            this.context = context;
            this.userList = UserList;
        }

        @Override
        public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.friendinfo_card, parent, false);

            return new FriendViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(final FriendViewHolder holder, int position) {
            User user = userList.get(position);
            holder.title.setText(user.getName());
            holder.count.setText(user.getDistance());

            // loading User cover using Glide library
            Glide.with(context).load(user.getProfilePicture()).into(holder.profilePicture);

            holder.overflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    showPopupMenu(holder.overflow);
                }
            });
        }

        /**
         * Showing popup menu when tapping on 3 dots
         */
        private void showPopupMenu(View view) {
            // inflate menu
            android.support.v7.widget.PopupMenu popup = new android.support.v7.widget.PopupMenu(context, view);
            MenuInflater inflater = popup.getMenuInflater();
            inflater.inflate(R.menu.menu_friend, popup.getMenu());
            popup.setOnMenuItemClickListener(new FriendMenuItemClickListener());
            popup.show();
        }

        /**
         * Click listener for popup menu items
         */
        class FriendMenuItemClickListener implements android.support.v7.widget.PopupMenu.OnMenuItemClickListener {

            public FriendMenuItemClickListener() {
                //Leave for possible use one day
            }

            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.action_request_location:
                        Toast.makeText(context, "Request Location Here", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.action_remind:
                        Toast.makeText(context, "Ask friend to leave here", Toast.LENGTH_SHORT).show();
                        return true;
                    default:
                }
                return false;
            }
        }

        @Override
        public int getItemCount() {
            return userList.size();
        }
    }


}
