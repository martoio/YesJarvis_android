package com.herokuapp.httpsyesjarvis1.yesjarvis.freebie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import com.herokuapp.httpsyesjarvis1.yesjarvis.R;
import com.herokuapp.httpsyesjarvis1.yesjarvis.dashboard.DashboardActivity;

/**
 * A simple {@link Fragment} subclass.
 *
 */
public class FreebieExtraInfoFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private int mContactMethod;
    private boolean mSelected;
    private Button mClaim;
    private Button mBack;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FreebieExtraInfoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param chosenService Parameter 1.
     * @return A new instance of fragment FreebieExtraInfoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FreebieExtraInfoFragment newInstance(int chosenService) {
        FreebieExtraInfoFragment fragment = new FreebieExtraInfoFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, chosenService);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_freebie_extra_info, container, false);

        v.findViewById(R.id.radio_email).setOnClickListener(this);
        v.findViewById(R.id.radio_sms).setOnClickListener(this);
        v.findViewById(R.id.radio_phone).setOnClickListener(this);

        mClaim = (Button)v.findViewById(R.id.claimButtonFreebie);
        mBack = (Button)v.findViewById(R.id.backButtonFreebie);

        mClaim.setOnClickListener(new View.OnClickListener() {
            //TODO: Add the Volley request to update the server for the freebie;
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "Congrats on choosing: "+mContactMethod, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getActivity(), DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //
            }
        });
        return v;
    }

    @Override
    public void onClick(View view) {
        boolean isChecked = ((RadioButton)view).isChecked();
        mSelected = true;
        mClaim.setEnabled(true);
        switch (view.getId()){
            case R.id.radio_sms:
                if (isChecked){
                    mContactMethod = 0;
                }
                break;
            case R.id.radio_phone:
                if (isChecked){
                    mContactMethod = 1;
                }
                break;
            case R.id.radio_email:
                if (isChecked){
                    mContactMethod = 2;
                }
                break;
        }

        Log.d("TAG", "Contact method is: " + mContactMethod);
    }

}
