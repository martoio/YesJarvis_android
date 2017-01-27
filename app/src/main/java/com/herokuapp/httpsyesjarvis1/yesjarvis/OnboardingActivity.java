package com.herokuapp.httpsyesjarvis1.yesjarvis;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.herokuapp.httpsyesjarvis1.yesjarvis.auth.AuthActivity;
import com.herokuapp.httpsyesjarvis1.yesjarvis.preferences.JarvisSharedPref;

import customfonts.JarvisTextView;

public class OnboardingActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    //Indicators
    private ImageView[] mIndicators = new ImageView[3];
    private int mCurrentIndicator;
    //Get A Jarvis Button
    private JarvisTextView mGetJarvis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);
        mIndicators[0] = (ImageView) findViewById(R.id.intro_indicator_0);
        mIndicators[1] = (ImageView) findViewById(R.id.intro_indicator_1);
        mIndicators[2] = (ImageView) findViewById(R.id.intro_indicator_2);
        mGetJarvis = (JarvisTextView) findViewById(R.id.textview_get_jarvis);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        //sets the indicator to page 0;
        mCurrentIndicator = 0;

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

            @Override
            //Override the indicators on the bottom of the page;
            public void onPageSelected(int position) {
                mIndicators[mCurrentIndicator].setBackgroundResource(R.drawable.indicator_unselected);
                mIndicators[position].setBackgroundResource(R.drawable.indicator_selected);
                mCurrentIndicator = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {}
        });

        mGetJarvis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JarvisSharedPref.putBooleanPref(OnboardingActivity.this, JarvisSharedPref.PREFS.FIRST_USE, true);
                Intent intent = new Intent(getApplicationContext(), AuthActivity.class);
                startActivity(intent);
            }
        });

    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private int mSectionNumber;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            mSectionNumber = getArguments().getInt(ARG_SECTION_NUMBER);
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            int infaterLayout;
            switch (mSectionNumber){
                case 0:
                    infaterLayout = R.layout.fragment_onboarding_screen_1;
                    break;
                case 1:
                    infaterLayout = R.layout.fragment_onboarding_screen_2;
                    break;
                case 2:
                    infaterLayout = R.layout.fragment_onboarding_screen_3;
                    break;
                default:
                    infaterLayout = R.layout.fragment_onboarding_screen_1;
                    break;
            }
            View rootView = inflater.inflate(infaterLayout, container, false);
//            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
//            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }
}
