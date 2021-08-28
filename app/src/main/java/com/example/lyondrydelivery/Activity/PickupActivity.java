package com.example.lyondrydelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lyondrydelivery.Adapter.Pickup_Tab_Adapter;
import com.example.lyondrydelivery.Class.BottomNavigationFragment;
import com.example.lyondrydelivery.R;
import com.google.android.material.tabs.TabLayout;

public class PickupActivity extends AppCompatActivity {
    BottomNavigationFragment bottomNavigationFragment;
    TabLayout tabLayout;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Pickup");

        //************************************BottomNavigationBar***************************************
        Fragment bottom_fragment = getSupportFragmentManager().findFragmentById(R.id.bottom_navigation_id);
        if (bottom_fragment instanceof Fragment){
            bottomNavigationFragment = (BottomNavigationFragment)bottom_fragment;
            bottomNavigationFragment.initailizeComponets();
        }
        //************************************BottomNavigationBar***************************************


        Tablayout();
    }

    private void Tablayout(){
        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);

        tabLayout.addTab(tabLayout.newTab().setText("Pickup Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Pickup Completed"));
      //  tabLayout.addTab(tabLayout.newTab().setText("Purchase offer"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final Pickup_Tab_Adapter adapter = new Pickup_Tab_Adapter(this,getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.order_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.notification:
                Toast.makeText(getApplicationContext(), "Notification", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.more_vertical:
                Toast.makeText(getApplicationContext(), "More_vertical", Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.invite:
//                Toast.makeText(getApplicationContext(), "Invite", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}