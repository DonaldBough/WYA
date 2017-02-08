package edu.purdue.dbough.wya;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting up the toolbar
        toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");

        //Setting up ViewPager
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     *
     * Add fragments to viewPager adapter and change toolbar title
     */
    private void setupViewPager(final ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.addFragment(new HomeFragment(), "ONE");
        adapter.addFragment(new RequestsFragment(), "TWO");
        adapter.addFragment(new NotificationsFragment(), "THREE");
        viewPager.setAdapter(adapter);

        //Change toolbar title to current tab on page change
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
//                        toolbar.setTitle("Home");
                        break;
                    case 1:
//                        toolbar.setTitle("Requests");
                        break;
                    case 2:
//                        toolbar.setTitle("Notifications");
                        break;
                }
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void setupTabIcons() {
        toolbar.setTitle("WYA");

        tabLayout.getTabAt(0).setText("ME");
        tabLayout.getTabAt(1).setText("FRIENDS");
        tabLayout.getTabAt(2).setText("REQUESTS");

//        tabLayout.getTabAt(0).setIcon(R.drawable.home_icon);
//        tabLayout.getTabAt(1).setIcon(R.drawable.binoculars);
//        tabLayout.getTabAt(2).setIcon(R.drawable.bell);
    }

    /**
     * Inside fragment_requests
     */
    public void onClickSendAlarm(View view) {
        Toast.makeText(view.getContext(), "Send Alarm here",Toast.LENGTH_SHORT).show();
    }

    /**
     * Inside fragment_requests
     */
    public void onClickOpenMap(View view) {
        Toast.makeText(view.getContext(), "Open Gmaps here",Toast.LENGTH_SHORT).show();
    }

    /**
     * Inside fragment_home
     * @param view
     */
    public void onClickAddRequest(View view) {
        Intent intent = new Intent(this, RequestActivity.class);
        startActivity(intent);
    }

    /**
     * Inside fragment_notifications
     * Happens on notifications being accepted
     */
    public void onClickAccept(View view) {
        Toast.makeText(view.getContext(), "Delete from List Here",Toast.LENGTH_SHORT).show();
    }

    /**
     * Inside fragment_notifications
     * Happens on notifications being declined
     */
    public void onClickDecline(View view) {
        Toast.makeText(view.getContext(), "Delete from List Here", Toast.LENGTH_SHORT).show();
    }

    /**
     * Holds fragments and titles of the tabbed "ViewPager"
     */
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> fragmentList = new ArrayList<>();
        private final List<String> fragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            fragmentList.add(fragment);
            fragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return null; //only using icons for tabs, not titles
        }
    }

}
