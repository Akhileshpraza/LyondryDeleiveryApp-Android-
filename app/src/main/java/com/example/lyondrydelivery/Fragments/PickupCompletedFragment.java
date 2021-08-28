package com.example.lyondrydelivery.Fragments;

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

import com.example.lyondrydelivery.Activity.PickupDeliveryActivity;
import com.example.lyondrydelivery.Adapter.DeliveryAdapter;
import com.example.lyondrydelivery.Adapter.HomeAdapter;
import com.example.lyondrydelivery.Adapter.ItemAdapter;
import com.example.lyondrydelivery.Adapter.PickupCompleteMainAdapter;
import com.example.lyondrydelivery.Adapter.PickupCompletedAdapter;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.HomeModal;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.Modal.PickupItemModal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_responce_modal;
import com.example.lyondrydelivery.Modal.SelecteDelivery.DeliverySchedualedList_Data;
import com.example.lyondrydelivery.Modal.SelecteDelivery.SelectDeliveryScheduled_responce_modal;
import com.example.lyondrydelivery.R;
import com.example.lyondrydelivery.RetrofiltClient.RetrofitClient;
import com.example.lyondrydelivery.SQLiteDB.SqliteDatabase;

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

public class PickupCompletedFragment extends Fragment {

    public PickupCompletedFragment() {
        // Required empty public constructor
    }

//    RecyclerView recyclerView;
    PickupCompletedAdapter pickupCompletedAdapter;
//    SharedPrefManager sharedPrefManager;
//    String token,UserName;
//    String location;
//    Spinner Location;
//    int PickupId;
//    SqliteDatabase database;
//    List<PickupItemModal> pickupItemModals=new ArrayList<>();

    RecyclerView recyclerView;
    PickupCompleteMainAdapter pickupCompleteMainAdapter;
    SharedPrefManager sharedPrefManager;
    String token,UserName;
    String location;
    Spinner Location;
    List<SelectPickupDelivery_data>selectPickupDeliveryData = new ArrayList<>();
    List<SelectPickupDelivery_data>selectPickupCompliteData_tems = new ArrayList<>();
    List<SelectPickupDelivery_data> selectPickupDelivery_dataList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_pickup_completed,container,false);
        recyclerView = view.findViewById(R.id.pickup_complited_recycleview);
        Location = view.findViewById(R.id.sp_location);
        sharedPrefManager = new SharedPrefManager(getContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        UserName = sharedPrefManager.getLoginUser().getUserName();
        //PickupId = sharedPrefManager.getSelectPickupDetails().getPickupRequestId();
        //Log.i("PickupId","**************PickupIdid**************"+PickupId);

        pickupCompletedFragment();
        //complite();
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

//                        selectPickupDeliveryData = selectPickupDelivery_responce_modal.getPickupRequestList();
//                        for (int i=0; i<selectPickupDeliveryData.size(); i++){
//                            SelectPickupDelivery_data selectPickupDeliveryData1 = (SelectPickupDelivery_data)selectPickupDeliveryData.get(i);
//                            String Status = selectPickupDeliveryData1.getPickupRequestStatus();
//
//                            Log.e("StatusComplite","*************StatusComplite****************"+Status);
//
//                            if (Status.equals("Pickup Completed")){
//                                homeAdapter = new HomeAdapter(selectPickupDeliveryData, getContext());
//                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                                recyclerView.setAdapter(homeAdapter);
//                                homeAdapter.notifyDataSetChanged();
//                            }
//                        }




//                        selectPickupDeliveryData = selectPickupDelivery_responce_modal.getPickupRequestList();
//
//                        for (SelectPickupDelivery_data delivery_data : selectPickupDeliveryData){
//
//                            Log.e("Pickup Status" , ""+delivery_data.getPickupRequestId()+"\n"+delivery_data.getPickupRequestStatus());
//
//
//                        }

//                        pickupCompleteMainAdapter = new PickupCompleteMainAdapter(selectPickupCompliteData_tems, getContext());
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                        recyclerView.setAdapter(pickupCompleteMainAdapter);
//                        pickupCompleteMainAdapter.notifyDataSetChanged();

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

                            if (Status.equals("Pickup Completed")){
                                selectPickupCompliteData_tems.add(new SelectPickupDelivery_data(PickupRequestId,PickupRequestAddress,PickupRequestTime,Location,CustomerName));
                                pickupCompleteMainAdapter = new PickupCompleteMainAdapter(selectPickupCompliteData_tems, getContext());
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerView.setAdapter(pickupCompleteMainAdapter);
                                pickupCompleteMainAdapter.notifyDataSetChanged();
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
                                    pickupCompleteMainAdapter = new PickupCompleteMainAdapter(selectPickupCompliteData_tems, getContext());
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerView.setAdapter(pickupCompleteMainAdapter);
                                    pickupCompleteMainAdapter.notifyDataSetChanged();

                                }
                                else {
                                    filter(Location.getSelectedItem().toString());

                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });

                        //filter(Location.getSelectedItem().toString());

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

        for (SelectPickupDelivery_data androidVersion : selectPickupCompliteData_tems) {

            if (androidVersion.getLocation().contains(text)) {

                filteredList.add(androidVersion);
            }
        }

        pickupCompleteMainAdapter.filterList(filteredList);
    }


//    private void filters(String text) {
//        ArrayList<SelectPickupDelivery_data> filteredList = new ArrayList<>();
//
//        for (SelectPickupDelivery_data androidVersion : selectPickupDeliveryData) {
//
//            if (androidVersion.getPickupRequestStatus().contains(text)) {
//
//                filteredList.add(androidVersion);
//            }
//        }
//
//        pickupCompleteMainAdapter.filterList(filteredList);
//    }

    //****************filter location****************************

//    private void complite(){
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//
//        database = new SqliteDatabase(getContext());
//        pickupItemModals =database.getRequestPickup();
//        pickupCompletedAdapter = new PickupCompletedAdapter(pickupItemModals, getActivity());
//        recyclerView.setAdapter(pickupCompletedAdapter);
//
//    }



}