package com.example.lyondrydelivery.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.lyondrydelivery.Fragments.DeliveryDaterRangeFragment;
import com.example.lyondrydelivery.Fragments.PickupCompletedFragment;
import com.example.lyondrydelivery.Fragments.PickupDateRangeFragment;
import com.example.lyondrydelivery.Fragments.PickupPendingFragment;

public class Pickup_Date_Range  extends FragmentPagerAdapter {

    private Context myContext;
    int totalTabs;

    public Pickup_Date_Range(Context context, FragmentManager fm, int totalTabs) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;

    }
    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                PickupDateRangeFragment pickupDateRangeFragment = new PickupDateRangeFragment();
                return pickupDateRangeFragment;
            case 1:
                DeliveryDaterRangeFragment deliveryDaterRangeFragment = new DeliveryDaterRangeFragment();
                return deliveryDaterRangeFragment;
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
