package com.herokuapp.httpsyesjarvis1.yesjarvis.freebie;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.herokuapp.httpsyesjarvis1.yesjarvis.R;
import com.herokuapp.httpsyesjarvis1.yesjarvis.SingleFragmentActivity;

public class FreebieActivity extends SingleFragmentActivity {
    @Override
    protected Fragment createFragment() {
        return new FreebieListFragment();
    }

    protected void switchToInfoFragment(Bundle args){
        FreebieExtraInfoFragment newFragment = FreebieExtraInfoFragment.newInstance(args.getInt(FreebieListFragment.SERVICE_TAG));

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //animation for transaction
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
        //Replace List Fragment with Info fragment
        transaction.replace(R.id.single_fragment_container, newFragment);
        //Commit transaction
        transaction.commit();
    }
}
