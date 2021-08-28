package com.example.lyondrydelivery.Fragments;

import android.location.Location;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lyondrydelivery.Activity.MainActivity;
import com.example.lyondrydelivery.Adapter.HomeAdapter;
import com.example.lyondrydelivery.Adapter.PickupCompleteMainAdapter;
import com.example.lyondrydelivery.Class.SharedPrefManager;
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


public class PickupPendingFragment extends Fragment {

    public PickupPendingFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    HomeAdapter homeAdapter;
    SharedPrefManager sharedPrefManager;
    String token,UserName;
    String location;
    Spinner Location;
    List<SelectPickupDelivery_data>selectPickupDeliveryData = new ArrayList<>();
    List<SelectPickupDelivery_data>selectPickupPendingData_tems = new ArrayList<>();
    List<SelectPickupDelivery_data> selectPickupDelivery_dataList;


//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser) {
//            pickupCompletedFragment();
//        }
//        else {
//        }
//    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pickup_pending,container,false);
        recyclerView = view.findViewById(R.id.pickup_pending_recycleview);
        Location = view.findViewById(R.id.sp_location);
        sharedPrefManager = new SharedPrefManager(getContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        UserName = sharedPrefManager.getLoginUser().getUserName();
        Log.i("token", "******token*******" + token);
        Log.i("name", "******name*******" + UserName);
        pickupCompletedFragment();
        return  view;
    }

    private void pickupCompletedFragment() {
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

                        selectPickupDeliveryData = selectPickupDelivery_responce_modal.getPickupRequestList();
                        for (SelectPickupDelivery_data delivery_data : selectPickupDeliveryData){

                          // Log.e("Pickup Status" , ""+delivery_data.getPickupRequestId()+"\n"+delivery_data.getPickupRequestStatus());

                        }

//                        homeAdapter = new HomeAdapter(selectPickupDeliveryData, getContext());
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                        recyclerView.setAdapter(homeAdapter);
//                        homeAdapter.notifyDataSetChanged();

                        selectPickupDelivery_dataList = selectPickupDelivery_responce_modal.getPickupRequestList();
                        SelectPickupDelivery_data selectPickupDeliveryData1;

                        String[] location1 = new String[selectPickupDelivery_dataList.size()];
                        for (int i = 0; i < selectPickupDelivery_dataList.size(); i++) {
                            selectPickupDeliveryData1 = (SelectPickupDelivery_data) selectPickupDelivery_dataList.get(i);
                            location = selectPickupDeliveryData1.getLocation();
                            location1[i] = location;

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
                                homeAdapter = new HomeAdapter(selectPickupPendingData_tems, getContext());
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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

                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, removedDuplicates);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        Location.setAdapter(dataAdapter);

                        Location.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                if (Location.getSelectedItem().toString().equals("All Location")){
                                    selectPickupDeliveryData = selectPickupDelivery_responce_modal.getPickupRequestList();
                                    homeAdapter = new HomeAdapter(selectPickupPendingData_tems, getContext());
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
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
                        Toast.makeText(getContext(), selectPickupDelivery_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SelectPickupDelivery_responce_modal> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    //****************filter location****************************
    private void filter(String text) {
        ArrayList<SelectPickupDelivery_data> filteredList = new ArrayList<>();

        for (SelectPickupDelivery_data androidVersion : selectPickupPendingData_tems) {

            if (androidVersion.getLocation().contains(text)) {

                filteredList.add(androidVersion);
            }
        }

        homeAdapter.filterList(filteredList);
    }

    //****************filter location****************************
}