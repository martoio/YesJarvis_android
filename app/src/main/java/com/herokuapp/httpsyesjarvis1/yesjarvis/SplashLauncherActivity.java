package com.herokuapp.httpsyesjarvis1.yesjarvis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.herokuapp.httpsyesjarvis1.yesjarvis.auth.AuthActivity;
import com.herokuapp.httpsyesjarvis1.yesjarvis.preferences.JarvisSharedPref;

import static com.herokuapp.httpsyesjarvis1.yesjarvis.preferences.JarvisSharedPref.PREFS;

public class SplashLauncherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent;
        //TODO: insert logic to send to either Intro or to Dash

        boolean isFirstUse = !JarvisSharedPref.prefExists(this, PREFS.FIRST_USE) && JarvisSharedPref.getBooleanPrefT(this, PREFS.FIRST_USE);

        if (isFirstUse){
            intent = new Intent(this, OnboardingActivity.class);
        } else {
            intent = new Intent(this, AuthActivity.class);
        }
        //TODO: REMOVE THIS. FOR TESTING ONLY
//        intent = new Intent(this, OnboardingActivity.class);

        startActivity(intent);
        finish();

    }

}
