package com.herokuapp.httpsyesjarvis1.yesjarvis.auth;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Request.Method;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.herokuapp.httpsyesjarvis1.yesjarvis.networking.CustomJsonRequest;
import com.herokuapp.httpsyesjarvis1.yesjarvis.networking.YesJarvisVolley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Martin on 2017-01-22 for YesJarvis.
 *
 * LoginRequest class to handle the login implementation
 */

public class LoginRequest  {
    private static final String LOGIN_ENDPOINT = "https://yesjarvis1.herokuapp.com/api/users/login";
    private static final String LOGIN_VOLLEY_TAG = "LOGIN_REQUEST";

    public LoginRequest(String email, String password) {
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("email", email);
            requestBody.put("password", password);
        } catch (JSONException e){
            Log.e("TAG", "JSON error");
        }
        CustomJsonRequest request = new CustomJsonRequest(Method.POST, LOGIN_ENDPOINT, requestBody, new LoginSuccessListener<JSONObject>(), new LoginErrorListener());
        request.setPriority(Request.Priority.HIGH);
        YesJarvisVolley.getInstance().add(request, LOGIN_VOLLEY_TAG);
    }

    private static class LoginSuccessListener<JSONObject> implements Response.Listener<JSONObject>{

        @Override
        public void onResponse(JSONObject response) {
            //TODO: Successful login, handle JWT, etc. here
        }

    }

    private static class LoginErrorListener implements Response.ErrorListener{
        @Override
        public void onErrorResponse(VolleyError error) {
            //TODO: Handle error response here;
        }
    }
}
