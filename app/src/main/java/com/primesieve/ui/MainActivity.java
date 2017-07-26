package com.primesieve.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.primesieve.R;

/**
 * This app displays an animated Gridview fragment with prime numbers flagged using Sieve Of Eratosthenes method
 * Also plays a related Sieve Of Eratosthenes YouTube video in another fragment.
 * The Youtube video was embedded into the app using the YouTube Android API
 */

public class MainActivity extends AppCompatActivity {

    private static final String GRIDVIEW = "GRID VIEW";
    private static final String YOUTUBE_VIDEO = "YOUTUBE VIDEO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter( getSupportFragmentManager() );

        // Set up the ViewPager with the sections adapter.
        /*
      The {@link ViewPager} that will host the section contents.
     */
        final ViewPager viewPager = (ViewPager) findViewById( R.id.container );
        viewPager.setAdapter( sectionsPagerAdapter );
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager( viewPager );

        viewPager.addOnPageChangeListener( new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (position == 1) {
                    launchYouTubeFragment();
                }
            }
            @Override
            public void onPageSelected(int position) {

            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * Fragment onResume() is skipped on subsequent pager position change
     * so explicity launch fragment here to auto launch video pager switch
     */
    private void launchYouTubeFragment() {
        YouTubeVideoFragment youTubeVideoFragment = new YouTubeVideoFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.youtube_fragment, youTubeVideoFragment);
        transaction.commit();
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private final int count = 2;

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Nullable
        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            //return PlaceholderFragment.newInstance(position + 1);
            switch (position) {
                case 0:
                    return new SieveGridViewFragment();
                case 1:
                    return new YouTubeVideoFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return count;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return GRIDVIEW;
                case 1:
                    return YOUTUBE_VIDEO;
            }
            return null;
        }
    }
}
