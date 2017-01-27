package com.herokuapp.httpsyesjarvis1.yesjarvis.auth;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;

import com.herokuapp.httpsyesjarvis1.yesjarvis.R;
import com.herokuapp.httpsyesjarvis1.yesjarvis.SingleFragmentActivity;

public class AuthActivity extends SingleFragmentActivity {

    public static final String AUTH_MODE = "AUTH_MDOE", AUTH_REGISTER = "REGISTER", AUTH_LOGIN = "LOGIN";

    @Override
    protected Fragment createFragment() {
        //check if AuthActivity is started as a Login or as a Register activity;
        Intent incomingIntent = getIntent();
        if (incomingIntent.hasExtra(AUTH_MODE)){
            if (incomingIntent.getStringExtra(AUTH_MODE).equals(AUTH_LOGIN)) return LoginFragment.newInstance();
        }
        return RegisterFragment.newInstance();
    }

    public void switchFragments(){
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.single_fragment_container);
        FragmentTransaction transaction = fm.beginTransaction();
        Fragment newFragment;
        if(f != null && f instanceof RegisterFragment){
            Log.d("TAG", "TO LOGIN");
            newFragment = LoginFragment.newInstance();
            //animation for transaction
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        } else {
            newFragment = RegisterFragment.newInstance();
            //animation for transaction
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        }
        //Replace fragment
        transaction.replace(R.id.single_fragment_container, newFragment);
        //Commit transaction
        transaction.commit();

    }

}
