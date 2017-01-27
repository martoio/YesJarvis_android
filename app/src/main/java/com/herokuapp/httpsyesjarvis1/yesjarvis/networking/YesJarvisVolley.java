package com.herokuapp.httpsyesjarvis1.yesjarvis.networking;

import android.app.Application;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Martin on 2016-12-24 for YesJarvisVolley.
 */
public class YesJarvisVolley extends Application{

    public static final String TAG = YesJarvisVolley.class.getSimpleName();
    private static YesJarvisVolley mInstance;
    private RequestQueue mRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        mRequestQueue = Volley.newRequestQueue(getApplicationContext());
    }

    /**
     * Singleton main method. Provides the global static instance of the helper class.
     * @return YesJarvisVolley instance
     */
    public static synchronized YesJarvisVolley getInstance() {
        return mInstance;
    }

    /**
     * Provides the Volley request queue
     * @return RequestQueue
     */
    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    /**
     * Adds the request to the general queue
     * @param req The Request Object
     * @param <T> the type of the request result;
     */
    public <T> void add(Request<T> req){
        add(req, TAG);
    }

    public <T> void add(Request<T> req, String tag){
        req.setTag(tag);
        getRequestQueue().add(req);
    }

    /**
     * Cancels all pending requests
     */
    public void cancel(){
        cancel(TAG);
    }

    public void cancel(String tag){
        mRequestQueue.cancelAll(tag);
    }

}
