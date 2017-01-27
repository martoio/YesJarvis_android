package com.herokuapp.httpsyesjarvis1.yesjarvis.dashboard;

/**
 * Created by Martin on 2017-01-21 for YesJarvis.
 */

public class JarviceServiceItem {
    private String mName;
    private boolean mEnabled;

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public JarviceServiceItem(String name, boolean enabled) {
        mName = name;
        mEnabled = enabled;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean enabled) {
        mEnabled = enabled;
    }
}
