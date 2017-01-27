package com.herokuapp.httpsyesjarvis1.yesjarvis.networking;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Martin on 2016-12-26 for YesJarvis.
 *
 * Error listener to handle the errors Volley throws. These are 400 and 409
 * response codes thrown by the server
 */

public class RegisterErrorListener implements Response.ErrorListener {
    @Override
    public void onErrorResponse(VolleyError error) {
        if (error.networkResponse != null && error.networkResponse.data != null){
            switch (error.networkResponse.statusCode){
                case 400:

                    break;
                case 409:
                    break;
            }
        }
    }
}
