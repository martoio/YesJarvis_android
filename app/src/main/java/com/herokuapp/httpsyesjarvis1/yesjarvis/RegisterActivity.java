package com.herokuapp.httpsyesjarvis1.yesjarvis;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.herokuapp.httpsyesjarvis1.yesjarvis.auth.LoginActivity;
import com.herokuapp.httpsyesjarvis1.yesjarvis.freebie.FreebieActivity;
import com.herokuapp.httpsyesjarvis1.yesjarvis.networking.CustomJsonRequest;
import com.herokuapp.httpsyesjarvis1.yesjarvis.networking.YesJarvisVolley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
//TODO: implement network connectivity checks;
//TODO: implement progress loading;
//TODO: Redirect after registering;
//TODO: Break this into an AuthActivity with Register and Login fragments;
//Other fun stuff like that
public class RegisterActivity extends AppCompatActivity {

    private static final String REGISTER_ENDPOINT = "https://yesjarvis1.herokuapp.com/api/users/register";

    private YesJarvisVolley mVolley = YesJarvisVolley.getInstance();
    private EditText mEmailET, mPasswordET, mPasswordAgainET, mPhoneNumET;
    private Button mRegister;
    private TextView mLoginRedirect;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_register);

        mEmailET = (EditText) findViewById(R.id.register_email);
        mPasswordET = (EditText) findViewById(R.id.register_password);
        mPasswordAgainET = (EditText) findViewById(R.id.register_password_again);
        mPhoneNumET = (EditText) findViewById(R.id.register_phone_num);
        mRegister = (Button) findViewById(R.id.register_button);
        mLoginRedirect = (TextView) findViewById(R.id.register_login_redirect_text_view);

        mRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: validate input
                final String mEmail = mEmailET.getText().toString().trim();
                final String mPass = mPasswordET.getText().toString().trim();
                final String mPass2 = mPasswordAgainET.getText().toString().trim();
                final String mPhone = mPhoneNumET.getText().toString().trim();

                Log.d("DEBUGME", "Email: " + mEmail + ", \n Pass: " + mPass + " \n Phone: " + mPhone);
//                Intent intent = new Intent(RegisterActivity.this, FreebieActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(intent);
                register(mEmail, mPass, mPass2, mPhone); //TODO: export into separate class;
            }
        });

        mLoginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    private void register(String email, String pass, String pass2, String phone){
        //TODO: optimize this crap below;
        Map<String, String> requestParams = new HashMap<>();
        requestParams.put("email", email);
        requestParams.put("password", pass);
        requestParams.put("password2", pass2);
        requestParams.put("phone_num", phone);


        mRegister.setEnabled(false);
        CustomJsonRequest registerRequest =
                new CustomJsonRequest(Request.Method.POST, REGISTER_ENDPOINT, new JSONObject(requestParams), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("DEBUGME", String.valueOf(response));
                            //TODO: Successful register, do something with it;
                            Intent intent = new Intent(RegisterActivity.this, FreebieActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);
                        } catch (Exception e){
                            //TODO: Some exception has occured, do somehting;
                            Log.d("DEBUGME", "SHIT HAPPENED");
                        }

                    }
                }, new Response.ErrorListener() {
                    //TODO: implement a good error listener;
                    @Override
                    public void onErrorResponse(VolleyError error) {
//                        VolleyError er = Request.parseNetworkError(error);
//                        NetworkResponse networkResponse = error.networkResponse;
////
//                        byte[] resp = error.networkResponse.data;
//                        String mes = new String(resp);
                        if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                            Log.d("VOLLEY", "TIMEOUT");
                        } else if (error instanceof AuthFailureError) {
                            Log.d("VOLLEY", "AUTH");
                        } else if (error instanceof ServerError) {
                            Log.d("VOLLEY", "SERVER");
                        } else if (error instanceof NetworkError) {
                            Log.d("VOLLEY", "NET");
                        } else if (error instanceof ParseError) {
                            Log.d("VOLLEY", "PARSE");
                        }
//                        Log.d("DEBUGME", );
                    }
                });
        registerRequest.setRetryPolicy(new DefaultRetryPolicy(50 * 1000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        registerRequest.setPriority(Request.Priority.HIGH);
        mVolley.add(registerRequest, "REGO");

    }


}
