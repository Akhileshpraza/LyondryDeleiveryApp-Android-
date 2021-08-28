package com.example.lyondrydelivery.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lyondrydelivery.Fragments.PickupCompletedFragment;
import com.example.lyondrydelivery.Fragments.PickupPendingFragment;

public class Pickup_Tab_Adapter extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public Pickup_Tab_Adapter(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PickupPendingFragment pickupPendingFragment = new PickupPendingFragment();
                return pickupPendingFragment;
            case 1:
                PickupCompletedFragment pickupCompletedFragment = new PickupCompletedFragment();
                return pickupCompletedFragment;
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
