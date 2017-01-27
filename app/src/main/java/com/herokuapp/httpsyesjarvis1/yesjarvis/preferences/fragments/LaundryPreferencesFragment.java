package com.herokuapp.httpsyesjarvis1.yesjarvis.preferences.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.herokuapp.httpsyesjarvis1.yesjarvis.R;

/**
 * Created by Martin on 2017-01-21 for YesJarvis.
 */

public class LaundryPreferencesFragment extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Load preferences in res/xml/laundry_pref
        addPreferencesFromResource(R.xml.laundry_pref);
    }
}
