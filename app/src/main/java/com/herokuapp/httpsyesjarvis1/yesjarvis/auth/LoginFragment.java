package com.herokuapp.httpsyesjarvis1.yesjarvis.auth;


import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.herokuapp.httpsyesjarvis1.yesjarvis.R;
import com.herokuapp.httpsyesjarvis1.yesjarvis.dashboard.DashboardActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment implements Response.Listener, Response.ErrorListener{
    private static final String LOGIN_ENDPOINT = "https://yesjarvis1.herokuapp.com/api/users/login";
    private static final String LOGIN_TAG = "LOGIN";

    private EditText mEmail, mPassword;
    private TextView mLoginButton, mRegisterRedirect;
    private ConstraintLayout mRoot;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_login, container, false);
        bindViews(v);

        mRegisterRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AuthActivity)getActivity()).switchFragments();
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                disableButton();
                login();
            }
        });
        return v;
    }

    private void login(){
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
        /*
        resetErrors();

        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        if (validate(email, password)){
            enableButton();
            return;
        }
        JSONObject requestParams = new JSONObject();
        try{
            requestParams.put("email", email);
            requestParams.put("password", password);
        }catch (JSONException e){
            Toast.makeText(getActivity().getApplicationContext(), "An error occurred", Toast.LENGTH_LONG).show();
        }

        CustomJsonRequest request = new CustomJsonRequest(Request.Method.POST, LOGIN_ENDPOINT, requestParams, this, this);
        */
    }
    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
        Intent intent = new Intent(getActivity(), DashboardActivity.class);
        startActivity(intent);
    }
    //Disables the Login "button" to avoid a flood of requests;
    private void disableButton(){
        mLoginButton.setEnabled(false);
        mLoginButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.gray));
        mLoginButton.setTextColor(ContextCompat.getColor(getContext(), R.color.black));
    }

    private void enableButton(){
        mLoginButton.setEnabled(true);
        mLoginButton.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        mLoginButton.setTextColor(ContextCompat.getColor(getContext(), R.color.white));
    }
    private void resetErrors(){
        mEmail.setError(null);
        mPassword.setError(null);
    }

    /**
     * Convenience method that binds the views from the layout
     *
     */
    private void bindViews(View v) {
        mRoot = (ConstraintLayout) v.findViewById(R.id.login_root);
        mLoginButton = (TextView) v.findViewById(R.id.login_text_view);
        mRegisterRedirect = (TextView) v.findViewById(R.id.register_redirect_text_view);
        mEmail = (EditText) v.findViewById(R.id.email_editText);
        mPassword = (EditText) v.findViewById(R.id.password_editText);
        mPassword.setTransformationMethod(new PasswordTransformationMethod());
    }

    private boolean validate(String email, String password) {
        View focusView = null;
        boolean cancelRequest = false;
        //Validation done in reverse order to make the focus go from top to bottom;
        //Validate password confirmation

        //Validate Password
        if (!RegisterFragment.Validator.isValidPassword(password)){
            focusView = mPassword;
            mPassword.setError(getString(R.string.error_invalid_password));
            cancelRequest = true;
        }

        // Validate Email
        if (TextUtils.isEmpty(email)){
            focusView = mEmail;
            mEmail.setError(getString(R.string.error_field_required));
            cancelRequest = true;
        } else if (!RegisterFragment.Validator.isValidEmail(email)){
            focusView = mEmail;
            mEmail.setError(getString(R.string.error_invalid_email));
            cancelRequest = true;
        }
        if (focusView != null) focusView.requestFocus();

        return cancelRequest;
    }
}
