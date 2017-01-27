package com.herokuapp.httpsyesjarvis1.yesjarvis.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Switch;
import android.widget.TextView;

import com.herokuapp.httpsyesjarvis1.yesjarvis.R;

import java.util.ArrayList;

/**
 * Created by Martin on 2017-01-21 for YesJarvis.
 */

public class JarviceServiceCardAdapter extends BaseAdapter {
    private ArrayList<JarviceServiceItem> mListData;
    private LayoutInflater mInflater;

    public JarviceServiceCardAdapter(ArrayList<JarviceServiceItem> listData, Context context) {
        mListData = listData;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int i) {
        return mListData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View card = mInflater.inflate(R.layout.cardview_item_dashboard, viewGroup, false);
        //TODO: implement the rest of the view binding calls here;
        Switch cardSwitch = (Switch) card.findViewById(R.id.card_view_dash_service_enabled);
        cardSwitch.setChecked(mListData.get(i).isEnabled());
        TextView cardName = (TextView) card.findViewById(R.id.card_view_dash_service_name);
        cardName.setText(mListData.get(i).getName());
        return card;
    }
}
