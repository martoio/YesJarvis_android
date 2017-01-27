package com.herokuapp.httpsyesjarvis1.yesjarvis.freebie;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.herokuapp.httpsyesjarvis1.yesjarvis.R;
import com.herokuapp.httpsyesjarvis1.yesjarvis.models.JarvisService;

import java.util.ArrayList;
import java.util.List;

/**
 * The FreebieListFragment shown in the FreebieActivity.
 *
 * Contains a RecyclerView hosting a vertical linear view of CardViews that contain the N-number of services YesJarvis offers.
 * The CardView UI is defined inside R.layout.fragment_freebie_list;
 *
 *
 */
//TODO: Fix icon sizes. First 2 are ok, but the others are larger than they should be for some reason.
public class FreebieListFragment extends Fragment {

    public static final String SERVICE_TAG = "FREEBIE_SERVICE";
    private RecyclerView mJarvisServiceRecyclerView;
    private JarvisServicesAdapter mAdapter;
    private int mServiceSelected;
    private boolean mIsSelected = false;

    private Button mNextButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_freebie_list, container, false);

        mNextButton = (Button) view.findViewById(R.id.nextButtonFreebie);
        mJarvisServiceRecyclerView = (RecyclerView) view.findViewById(R.id.fragment_freebie_recycler_list);
        //Creates a linear layout manager that doesn't allow vertical scrolling;
        LinearLayoutManager llm = new LinearLayoutManager(getActivity().getApplicationContext()){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        mJarvisServiceRecyclerView.setLayoutManager(llm);
        this.updateUI();

        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!mIsSelected)return;

                FreebieActivity parent = (FreebieActivity)getActivity();
                Bundle args = new Bundle();
                args.putInt(SERVICE_TAG, mServiceSelected);
                parent.switchToInfoFragment(args);
            }
        });
        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    /**
     * Creates and sets the adapter for the recycler view;
     */
    private void updateUI(){
        List<JarvisService> services = this.getServices();
        if (mAdapter == null){
            mAdapter = new JarvisServicesAdapter(services);
            mJarvisServiceRecyclerView.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

    }

    /**
     * Generates the list of services for the cards in the recycler view
     * @return
     */
    //TODO: implement some better way of doing this
    private List<JarvisService> getServices(){
        List<JarvisService> services = new ArrayList<>();
        services.add(new JarvisService("Home Cleaning", R.drawable.home_cleaning_light_green));
        services.add(new JarvisService("Grocery Shopping", R.drawable.shopping_light_green));
        services.add(new JarvisService("Laundry", R.drawable.laundry_light_green));
        services.add(new JarvisService("Dry Cleaning", R.drawable.dry_cleaning_light_green));
        services.add(new JarvisService("Tidy Up", R.drawable.bed_icon_light_green));

        return services;
    }

    /**
     * RecyclerView ViewHolder class.
     */
    private class JarvisServiceHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private JarvisService mJarvisService;
        private TextView mJarvisServiceName;
        private ImageView mJarvisServiceIcon;
        private ImageView mJarvisServiceCheckMark;
        private CardView mCardView;

        public JarvisServiceHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mCardView = (CardView) itemView;
            mJarvisServiceName = (TextView)itemView.findViewById(R.id.freebie_card_text);
            mJarvisServiceIcon = (ImageView)itemView.findViewById(R.id.freebie_card_icon);
            mJarvisServiceCheckMark = (ImageView)itemView.findViewById(R.id.freebie_card_check);
        }


        //TODO: Cleanup this onClick. Implement the card looking better and all the other great things.
        @Override
        public void onClick(View view) {
            /*if (mServiceSelected == getAdapterPosition()){
                mIsSelected = false;
            } else {
                mServiceSelected = getAdapterPosition();
                mIsSelected = true;
            }


            updateUI();*/
            JarvisServiceHolder v = (JarvisServiceHolder) mJarvisServiceRecyclerView.findViewHolderForAdapterPosition(mServiceSelected);
            v.mJarvisServiceCheckMark.setVisibility(View.INVISIBLE);

            mServiceSelected = getAdapterPosition();
            mJarvisServiceCheckMark.setVisibility(View.VISIBLE);
            mIsSelected = true;
            mNextButton.setEnabled(true);
            Toast.makeText(getActivity(), "Chosen: "+mJarvisService.getName(), Toast.LENGTH_LONG).show();
        }

        private void toggleCheckMarkVisibility(){
            if (mJarvisServiceCheckMark.getVisibility() == View.VISIBLE){
                mJarvisServiceCheckMark.setVisibility(View.INVISIBLE);
            } else {
                mJarvisServiceCheckMark.setVisibility(View.VISIBLE);
            }
        }

        public void bindJarvisService(JarvisService service){
            this.mJarvisService = service;
            this.mJarvisServiceName.setText(service.getName());
            /*if (getAdapterPosition() == mServiceSelected && mIsSelected){
                this.mJarvisServiceCheckMark.setVisibility(View.VISIBLE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    mCardView.setBackgroundColor(getActivity().getResources().getColor(R.color.jarvis_light_green, getActivity().getTheme()));
                } else {
                    mCardView.setBackgroundColor(getActivity().getResources().getColor(R.color.jarvis_light_green));
                }

            }*/
            //this.mJarvisServiceCheckMark.setVisibility(View.INVISIBLE);
            this.mJarvisServiceIcon.setImageResource(service.getPhoto());
        }
    }

    /**
     * The Adapter for the Recycler view
     */
    private class JarvisServicesAdapter extends RecyclerView.Adapter<JarvisServiceHolder>{
        private List<JarvisService> mJarvisServices;

        public JarvisServicesAdapter(List<JarvisService> jarvisServices){
            mJarvisServices = jarvisServices;
        }

        @Override
        public JarvisServiceHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(getActivity());
            View view = inflater.inflate(R.layout.cardview_item_freebie, parent, false);
            return new JarvisServiceHolder(view);
        }

        @Override
        public void onBindViewHolder(JarvisServiceHolder holder, int position) {
            JarvisService service = mJarvisServices.get(position);
            holder.bindJarvisService(service);
        }

        @Override
        public int getItemCount() {
            return mJarvisServices.size();
        }
    }



}
