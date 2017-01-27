package com.herokuapp.httpsyesjarvis1.yesjarvis.preferences;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;

import com.herokuapp.httpsyesjarvis1.yesjarvis.R;
import com.herokuapp.httpsyesjarvis1.yesjarvis.preferences.fragments.LaundryPreferencesFragment;

/**
 * Created by Martin on 2017-01-21 for YesJarvis.
 */
//TODO: Add appbar with <- button in this class and the XML;
public class BasePreferenceActivity extends FragmentActivity {


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_fragment);

        FragmentManager fm = getSupportFragmentManager();

        //TODO: implement logic here
        String s = getIntent().getStringExtra("CHOSEN_PREFERENCE");
        PreferenceFragment fragment = createFragment(s);
        //fm.beginTransaction().replace(R.id.single_fragment_container, fragment).commit();

        // Display the fragment as the main content.
        getFragmentManager().beginTransaction()
                .replace(android.R.id.content, createFragment(s))
                .commit();

    }

    private PreferenceFragment createFragment(String s){
        return new LaundryPreferencesFragment();
    }
}
