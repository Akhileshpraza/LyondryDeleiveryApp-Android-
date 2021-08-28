package com.example.lyondrydelivery.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lyondrydelivery.Fragments.DeliveryComplitedFragment;
import com.example.lyondrydelivery.Fragments.DevliveryPendingFragment;


public class Delivery_Tab_Adapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public Delivery_Tab_Adapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;

    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                DevliveryPendingFragment devliveryPendingFragment = new DevliveryPendingFragment();
                return devliveryPendingFragment;
            case 1:
                DeliveryComplitedFragment deliveryComplitedFragment = new DeliveryComplitedFragment();
                return deliveryComplitedFragment;
            case 2:

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return totalTabs;
    }
}
