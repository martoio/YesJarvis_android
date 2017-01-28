package com.herokuapp.httpsyesjarvis1.yesjarvis.auth;

import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.herokuapp.httpsyesjarvis1.yesjarvis.R;
import com.herokuapp.httpsyesjarvis1.yesjarvis.networking.CustomJsonRequest;
import com.herokuapp.httpsyesjarvis1.yesjarvis.networking.YesJarvisVolley;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * A placeholder fragment containing a simple view.
 */
public class RegisterFragment extends Fragment implements Response.Listener, Response.ErrorListener {

    private static final String REGISTER_ENDPOINT = "https://yesjarvis1.herokuapp.com/api/users/register";
    private static final String REGISTER_TAG = "REGISTER";

    private TextView mRegisterView, mLoginRedirectView;
    private EditText mEmail, mPassword, mPasswordConfirm, mPhone;
    private ConstraintLayout mRoot;

    /**
     * Factory method
     *
     */
    public static RegisterFragment newInstance(){
        return new RegisterFragment();
    }

    /**
     * Empty constructor
     */
    public RegisterFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_register, container, false);
        bindViews(v);

        //attach Listeners
        mRegisterView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Things for registering here
                disableButton();

                register();
            }
        });

        mLoginRedirectView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //fragment transition

                ((AuthActivity)getActivity()).switchFragments();
            }
        });
        return v;
    }

    //Disables the Register "button" to avoid a flood of requests;
    private void disableButton(){
        mRegisterView.setEnabled(false);
        mRegisterView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray));
        mRegisterView.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
    }
    private void enableButton(){
        mRegisterView.setEnabled(true);
        mRegisterView.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mRegisterView.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
    }

    /**
     * Validates input
     */
    private void register(){
        resetErrors();
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();
        final String passwordConfirm = mPasswordConfirm.getText().toString();
        final String phone = mPhone.getText().toString();

        // read: if(cancelRequest)
        if(validate(email, password, passwordConfirm, phone)){
            enableButton();
            return;
        }

        JSONObject requestParams = new JSONObject();
        try{
            requestParams.put("email", email);
            requestParams.put("password", password);
            requestParams.put("password2", passwordConfirm);
            requestParams.put("phone", phone);
        }catch (JSONException e){
            Toast.makeText(getActivity().getApplicationContext(), "An error occurred", Toast.LENGTH_LONG).show();
        }
        CustomJsonRequest request = new CustomJsonRequest(Request.Method.POST, REGISTER_ENDPOINT, requestParams, this, this);
        request.setTag(REGISTER_TAG);
        request.setPriority(Request.Priority.HIGH);
        YesJarvisVolley.getInstance().add(request);
    }

    /**
     * Convenience method that binds the views from the layout
     *
     */
    private void bindViews(View v) {
        mRoot = (ConstraintLayout) v.findViewById(R.id.register_root);
        mRegisterView = (TextView) v.findViewById(R.id.register_text_view);
        mLoginRedirectView = (TextView) v.findViewById(R.id.register_login_redirect_text_view);
        mEmail = (EditText) v.findViewById(R.id.email_editText);
        mPassword = (EditText) v.findViewById(R.id.password_editText);
        mPassword.setTransformationMethod(new PasswordTransformationMethod());
        mPasswordConfirm = (EditText) v.findViewById(R.id.password_repeat_editText);
        mPasswordConfirm.setTransformationMethod(new PasswordTransformationMethod());
        mPhone = (EditText) v.findViewById(R.id.phone_editText);
    }

    private void resetErrors(){
        mEmail.setError(null);
        mPassword.setError(null);
        mPasswordConfirm.setError(null);
        mPhone.setError(null);
    }

    //ErrorResponse Interface for Volley Request
    @Override
    public void onErrorResponse(VolleyError error) {
        //TODO: Implement a better error response handler;


        if (error.networkResponse != null && error.networkResponse.data != null){
            int statusCode = error.networkResponse.statusCode;
            byte[] response = error.networkResponse.data;
            Snackbar snackbar;

            switch (statusCode){
                case 400:
                    snackbar = Snackbar.make(mRoot, "Error: please check the input", Snackbar.LENGTH_LONG);
                    break;
                case 409:
                    snackbar = Snackbar.make(mRoot, "Email already registered. Login or contact support", Snackbar.LENGTH_LONG);
                    break;
                default:
                    snackbar = Snackbar.make(mRoot, "An error occurred, please try again", Snackbar.LENGTH_LONG);
                    Log.d(REGISTER_TAG, new String(response));
                    break;
            }

            TextView tv = (TextView)snackbar.getView().findViewById(android.support.design.R.id.snackbar_text);
            tv.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
            snackbar.show();
        }

        enableButton();

//        Toast.makeText(getActivity().getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(Object response) {
        //Successful register
    }

    /**
     * Validates the input for the fields;
     * @param email             String
     * @param password          String
     * @param passwordConfirm   String
     * @param phone             String
     */
    private boolean validate(String email, String password, String passwordConfirm, String phone) {
        View focusView = null;
        boolean cancelRequest = false;
        //Validation done in reverse order to make the focus go from top to bottom;
        //Validate password confirmation
        if (!Validator.isValidPasswordConfirm(password, passwordConfirm)){
            focusView = mPasswordConfirm;
            mPasswordConfirm.setError(getString(R.string.error_password_mismatch));
            cancelRequest = true;
        }

        //Validate Password
        if (!Validator.isValidPassword(password)){
            focusView = mPassword;
            mPassword.setError(getString(R.string.error_invalid_password));
            cancelRequest = true;
        }

        // Validate Email
        if (TextUtils.isEmpty(email)){
            focusView = mEmail;
            mEmail.setError(getString(R.string.error_field_required));
            cancelRequest = true;
        } else if (!Validator.isValidEmail(email)){
            focusView = mEmail;
            mEmail.setError(getString(R.string.error_invalid_email));
            cancelRequest = true;
        }

        //Validate Phone
        //if (!Validator.isValidUKPhone(phone))


        if (focusView != null) focusView.requestFocus();

        return cancelRequest;
    }

    //Cancel any pending register requests
    @Override
    public void onStop() {
        super.onStop();
        if (YesJarvisVolley.getInstance().getRequestQueue() != null){
            YesJarvisVolley.getInstance().getRequestQueue().cancelAll(REGISTER_TAG);
        }
    }

    /**
     * Utility class for validating input
     */
    public static class Validator{

        //checks if an email is valid;
        static boolean isValidEmail(String email){
            return Patterns.EMAIL_ADDRESS.matcher(email).matches();
        }
        //checks if a password is valid;
        static boolean isValidPassword(String password){
            return (password.length() > 4 && password.length() < 20);
        }
        //checks if 2 passwords match;
        static boolean isValidPasswordConfirm(String p1, String p2){
            return p1.equals(p2) && isValidPassword(p2);
        }

        //check for valid phone number
        //TODO: maybe implement sth better here;
        private static boolean isValidUKPhone(String phone){
            return true;
        }

    }

}
