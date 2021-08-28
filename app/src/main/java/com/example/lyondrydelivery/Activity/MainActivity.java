package com.example.lyondrydelivery.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lyondrydelivery.Adapter.HomeAdapter;
import com.example.lyondrydelivery.Class.BottomNavigationFragment;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Fragments.PickupCompletedFragment;
import com.example.lyondrydelivery.Fragments.PickupPendingFragment;
import com.example.lyondrydelivery.Modal.HomeModal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_responce_modal;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.RetrofiltClient.RetrofitClient;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.lyondrydelivery.R.drawable.rectrangal_gray_bg;

public class MainActivity extends AppCompatActivity {
    BottomNavigationFragment bottomNavigationFragment;
    TextView Pickup_pending, Delivery_pending, Pickup_Done, Delivery_Done;
    Spinner Location;
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    LinearLayout Pickup, Delivery, Pickupdone, Deliverydone;
    SharedPrefManager sharedPrefManager;
    String token, UserName;
    int AllLocation=0;
    List<SelectPickupDelivery_data> selectPickupDeliveryData = new ArrayList<>();
    List<SelectPickupDelivery_data> selectPickupPendingData_tems = new ArrayList<>();
    List<SelectPickupDelivery_data> selectPickupDelivery_dataList;
    SelectPickupDelivery_data selectPickupDeliveryData1;
    String location;
    int pickuprequestId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        getSupportActionBar().setTitle("Dashboard");
        Pickup = findViewById(R.id.pickup);
        Delivery = findViewById(R.id.deliverypickup);
        Pickupdone = findViewById(R.id.pickupdone);
        Deliverydone = findViewById(R.id.deliverydone);

        Pickup_pending = findViewById(R.id.tv_pickup_pending);
        Delivery_pending = findViewById(R.id.tv_delivery_pending);
        Pickup_Done = findViewById(R.id.tv_pickup_done);
        Delivery_Done = findViewById(R.id.tv_devlivery_done);
        Location = findViewById(R.id.sp_location);
        recyclerView = findViewById(R.id.home_recycleView);
        String AllLocation="AllLocation";

        sharedPrefManager = new SharedPrefManager(getApplicationContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        UserName = sharedPrefManager.getLoginUser().getUserName();
        Log.i("token", "******token*******" + token);
        Log.i("name", "******name*******" + UserName);

        BottomNavigationBar();
        sp_location();
        pickupdepnding();
        pickupdone();
        deliverydepnding();
        deliverydone();
    }

    public void pickupdepnding() {
        Pickup.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Pickup_pending.setTextColor(Color.parseColor("#FFFFFF"));
                Pickup.setBackgroundResource(rectrangal_gray_bg);

                Delivery_pending.setTextColor(Color.parseColor("#31396B"));
                Delivery.setBackgroundResource(R.drawable.banner_slider_bg);

                Pickup_Done.setTextColor(Color.parseColor("#31396B"));
                Pickupdone.setBackgroundResource(R.drawable.banner_slider_bg);

                Delivery_Done.setTextColor(Color.parseColor("#31396B"));
                Deliverydone.setBackgroundResource(R.drawable.banner_slider_bg);

                Intent intent = new Intent(MainActivity.this,PickupActivity.class);
                v.getContext().startActivity(intent);

            }
        });

    }

    public void deliverydepnding() {
        Delivery.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                Pickup_pending.setTextColor(Color.parseColor("#31396B"));
                Pickup.setBackgroundResource(R.drawable.banner_slider_bg);

                Delivery_pending.setTextColor(Color.parseColor("#FFFFFF"));
                Delivery.setBackgroundResource(rectrangal_gray_bg);

                Pickup_Done.setTextColor(Color.parseColor("#31396B"));
                Pickupdone.setBackgroundResource(R.drawable.banner_slider_bg);

                Delivery_Done.setTextColor(Color.parseColor("#31396B"));
                Deliverydone.setBackgroundResource(R.drawable.banner_slider_bg);
                Intent intent = new Intent(MainActivity.this,DeliveryActivity.class);
                v.getContext().startActivity(intent);

            }
        });
    }

    public void pickupdone() {
        Pickupdone.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                Delivery_pending.setTextColor(Color.parseColor("#31396B"));
                Delivery.setBackgroundResource(R.drawable.banner_slider_bg);

                Pickup_pending.setTextColor(Color.parseColor("#31396B"));
                Pickup.setBackgroundResource(R.drawable.banner_slider_bg);

                Pickup_Done.setTextColor(Color.parseColor("#FFFFFF"));
                Pickupdone.setBackgroundResource(R.drawable.rectrangal_gray_bg);

                Delivery_Done.setTextColor(Color.parseColor("#31396B"));
                Deliverydone.setBackgroundResource(R.drawable.banner_slider_bg);

                Intent intent = new Intent(MainActivity.this,PickupActivity.class);
                v.getContext().startActivity(intent);

            }
        });
    }

    public void deliverydone() {
        Deliverydone.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {

                Pickup_Done.setTextColor(Color.parseColor("#31396B"));
                Pickup.setBackgroundResource(R.drawable.banner_slider_bg);

                Pickup_pending.setTextColor(Color.parseColor("#31396B"));
                Delivery.setBackgroundResource(R.drawable.banner_slider_bg);

                Pickup_Done.setTextColor(Color.parseColor("#31396B"));
                Pickupdone.setBackgroundResource(R.drawable.banner_slider_bg);

                Delivery_Done.setTextColor(Color.parseColor("#FFFFFF"));
                Deliverydone.setBackgroundResource(rectrangal_gray_bg);

                Intent intent = new Intent(MainActivity.this,DeliveryActivity.class);
                v.getContext().startActivity(intent);

            }
        });
    }

    private void sp_location() {
        Call<SelectPickupDelivery_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectPickupDelivery(token, UserName);

        call.enqueue(new Callback<SelectPickupDelivery_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<SelectPickupDelivery_responce_modal> call, Response<SelectPickupDelivery_responce_modal> response) {

                if (response.isSuccessful()) {
                    SelectPickupDelivery_responce_modal selectPickupDelivery_responce_modal = response.body();

                    if (selectPickupDelivery_responce_modal.getSuccess()) {

                        //selectPickupDeliveryData = selectPickupDelivery_responce_modal.getPickupRequestList();
//                        homeAdapter = new HomeAdapter(selectPickupDeliveryData, getApplicationContext());
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                        recyclerView.setAdapter(homeAdapter);
//                        homeAdapter.notifyDataSetChanged();

                        selectPickupDelivery_dataList = selectPickupDelivery_responce_modal.getPickupRequestList();


                        String[] location1 = new String[selectPickupDelivery_dataList.size()];
                        String[] pickupid1 = new String[selectPickupDelivery_dataList.size()];
                        for (int i = 0; i < selectPickupDelivery_dataList.size(); i++) {

                            selectPickupDeliveryData1 = (SelectPickupDelivery_data) selectPickupDelivery_dataList.get(i);
                            location = selectPickupDeliveryData1.getLocation();
                            location1[i] = location;
                            pickuprequestId = selectPickupDeliveryData1.getPickupRequestId();
                            pickupid1[i] = String.valueOf(pickuprequestId);

                            String Status = selectPickupDeliveryData1.getPickupRequestStatus();
                            int PickupRequestId = selectPickupDeliveryData1.getPickupRequestId();
                            String PickupRequestServiceType = selectPickupDeliveryData1.getPickupRequestServiceType();
                            String PickupRequestServiceName = selectPickupDeliveryData1.getPickupRequestServiceName();
                            String PickupRequestStoreName = selectPickupDeliveryData1.getPickupRequestStoreName();
                            String PickupRequestAddress = selectPickupDeliveryData1.getPickupRequestAddress();
                            String PickupRequestDate = selectPickupDeliveryData1.getPickupRequestDate();
                            String PickupRequestTime = selectPickupDeliveryData1.getPickupRequestTime();
                            String PickupRequestStatus = selectPickupDeliveryData1.getPickupRequestStatus();
                            String Location = selectPickupDeliveryData1.getLocation();
                            String CustomerName =selectPickupDeliveryData1.getCustomerName();
                            int CustomerId = selectPickupDeliveryData1.getCustomerId();
                            String DuaDate = selectPickupDeliveryData1.getPickupRequestDueDate();
                            if (Status.equals("Pickup")){
                                selectPickupPendingData_tems.add(new SelectPickupDelivery_data(PickupRequestId,PickupRequestServiceType,PickupRequestServiceName,PickupRequestStoreName,PickupRequestAddress,PickupRequestDate,PickupRequestTime,PickupRequestStatus,Location,CustomerName,CustomerId,DuaDate));
                                homeAdapter = new HomeAdapter(selectPickupPendingData_tems, getApplicationContext());
                                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                recyclerView.setAdapter(homeAdapter);
                                homeAdapter.notifyDataSetChanged();
                            }else {
                                //Toast.makeText(getContext(), "Pickup Not Complited", Toast.LENGTH_SHORT).show();
                            }


                        }

                        //***********************removedDuplicates*******************
                        Set<String> hashSet = new LinkedHashSet(Arrays.asList(location1));
                        ArrayList<String> removedDuplicates = new ArrayList(hashSet);
                        removedDuplicates.add("All Location");
                        Collections.reverse(removedDuplicates);
                        //***********************removedDuplicates*******************

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, removedDuplicates);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Location.setAdapter(dataAdapter);

                            Location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                @Override
                                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                    if (Location.getSelectedItem().toString().equalsIgnoreCase("All Location")){

                                        selectPickupDeliveryData = selectPickupDelivery_responce_modal.getPickupRequestList();
                                        homeAdapter = new HomeAdapter(selectPickupPendingData_tems, getApplicationContext());
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                                        recyclerView.setAdapter(homeAdapter);
                                        homeAdapter.notifyDataSetChanged();

                                    }
                                    else {
                                        filter(Location.getSelectedItem().toString());

                                    }
                                }

                                @Override
                                public void onNothingSelected(AdapterView<?> parent) {
                                }
                            });

                    } else {
                        Toast.makeText(MainActivity.this, selectPickupDelivery_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SelectPickupDelivery_responce_modal> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //****************filter location****************************
    private void filter(String text) {
        ArrayList<SelectPickupDelivery_data> filteredList = new ArrayList<>();

        for (SelectPickupDelivery_data location : selectPickupPendingData_tems) {

            if (location.getLocation().contains(text)) {

                filteredList.add(location);
            }
        }

        homeAdapter.filterList(filteredList);

    }

    //****************filter location****************************

    private void BottomNavigationBar() {
        //************************************BottomNavigationBar***************************************
        Fragment bottom_fragment = getSupportFragmentManager().findFragmentById(R.id.bottom_navigation_id);
        if (bottom_fragment instanceof Fragment) {
            bottomNavigationFragment = (BottomNavigationFragment) bottom_fragment;
            bottomNavigationFragment.initailizeComponets();
        }
        //************************************BottomNavigationBar***************************************
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
                //Toast.makeText(getApplicationContext(), "Notification", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.more_vertical:
                //Toast.makeText(getApplicationContext(), "More_vertical", Toast.LENGTH_SHORT).show();
                return true;
//            case R.id.invite:
//                Toast.makeText(getApplicationContext(), "Invite", Toast.LENGTH_SHORT).show();
//                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}