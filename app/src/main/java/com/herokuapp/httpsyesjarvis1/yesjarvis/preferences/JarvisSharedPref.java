package com.herokuapp.httpsyesjarvis1.yesjarvis.preferences;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Martin on 2016-12-28 for YesJarvis.
 */

public class JarvisSharedPref {

    private static final String PREFS = "com.herokuapp.httpsyesjarvis1.yesjarvis.PREFS_FILE";

    /*
    Below methods are for putting values to prefereces
     */
    public static boolean putStringPref(Context context, String K, String V){
        return getPrefEditor(context).putString(K, V).commit(); //write directly to sharedprefs instead of background. maybe optimize;
    }

    public static boolean putBooleanPref(Context context, String K, boolean V){
        return getPrefEditor(context).putBoolean(K, V).commit(); //write directly to sharedprefs instead of background. maybe optimize;
    }

    public static boolean putIntPref(Context context, String K, int V){
        return getPrefEditor(context).putInt(K, V).commit(); //write directly to sharedprefs instead of background. maybe optimize;
    }

    /*
    Below methods are for getting preferences
     */

    public static String getStringPref(Context context, String K){
        return getPrefs(context).getString(K, null);
    }

    public static boolean getBooleanPrefF(Context context, String K){
        return getPrefs(context).getBoolean(K, false);
    }

    public static boolean getBooleanPrefT(Context context, String K){
        return getPrefs(context).getBoolean(K, true);
    }

    public static int getIntPref(Context context, String K){
        return getPrefs(context).getInt(K, 0);
    }

    public static boolean prefExists(Context context, String K){
        return getPrefs(context).contains(K);
    }

    /*
    Below methods are convenience methods that remove common code from putting/getting;
     */
    private static SharedPreferences getPrefs(Context context){
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    private static SharedPreferences.Editor getPrefEditor(Context context){
        return getPrefs(context).edit();
    }
    /*
    Below class contains all of the constants related to shared preferences
     */
    public static class PREFS{
        public static final String FIRST_USE = "yj_first_app_use";
    }
}
