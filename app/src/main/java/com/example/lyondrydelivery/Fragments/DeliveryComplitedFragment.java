package com.example.lyondrydelivery.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.lyondrydelivery.Adapter.DeliveryAdapter;
import com.example.lyondrydelivery.Adapter.DeliveryCompliteAdapter;
import com.example.lyondrydelivery.Adapter.PickupCompletedAdapter;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.DeliveryRequestComplited_Modal;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.Modal.PickupItemModal;
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


public class DeliveryComplitedFragment extends Fragment {

    public DeliveryComplitedFragment() {
        // Required empty public constructor
    }
    RecyclerView recyclerView;
    DeliveryCompliteAdapter deliveryCompliteAdapter;
    SharedPrefManager sharedPrefManager;
    String token,UserName;
    String location;
    Spinner Location;
    int PickupId;
    List<DeliverySchedualedList_Data>deliveryCompleteListData = new ArrayList<>();
    List<DeliverySchedualedList_Data>deliveryCompleteListData_temp = new ArrayList<>();
    List<DeliverySchedualedList_Data> deliveryCompleteList_data;
    DeliverySchedualedList_Data deliveryCompleteList_data1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_delivery_complited,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.delivery_Complited_recycleview1);
        Location = view.findViewById(R.id.sp_location);
        sharedPrefManager = new SharedPrefManager(getContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        UserName = sharedPrefManager.getLoginUser().getUserName();
        Log.i("token", "******token*******" + token);
        Log.i("name", "******name*******" + UserName);
        deliveryPendingSchedualeFragment();
        //Deliverycomplited();
        return  view;
    }


    private void deliveryPendingSchedualeFragment() {

        Call<SelectDeliveryScheduled_responce_modal> call = RetrofitClient
                .getInstance()
                .getApi()
                .SelectDeliveryScheduale(token, UserName, 0);

        call.enqueue(new Callback<SelectDeliveryScheduled_responce_modal>() {
            @Override
            public void onResponse(@NotNull Call<SelectDeliveryScheduled_responce_modal> call, Response<SelectDeliveryScheduled_responce_modal> response) {

                if (response.isSuccessful()) {
                    SelectDeliveryScheduled_responce_modal selectDeliveryScheduled_responce_modal = response.body();

                    if (selectDeliveryScheduled_responce_modal.getSuccess()) {

                        deliveryCompleteListData = selectDeliveryScheduled_responce_modal.getDeliveryList();
//                        deliveryAdapter = new DeliveryAdapter(deliverySchedualedListData, getContext());
//                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                        recyclerView.setAdapter(deliveryAdapter);
//                        deliveryAdapter.notifyDataSetChanged();

                        deliveryCompleteList_data = selectDeliveryScheduled_responce_modal.getDeliveryList();


                        String[] location1 = new String[deliveryCompleteList_data.size()];
                        for (int i = 0; i < deliveryCompleteList_data.size(); i++) {
                            deliveryCompleteList_data1 = (DeliverySchedualedList_Data) deliveryCompleteList_data.get(i);
                            location = deliveryCompleteList_data1.getLocation();
                            location1[i] = location;

                            int DeliveryRequestId =deliveryCompleteList_data1.getDeliveryRequestId();
                            String DeliveryRequestServiceType =deliveryCompleteList_data1.getDeliveryRequestServiceType();
                            String DeliveryRequestServiceName =deliveryCompleteList_data1.getDeliveryRequestServiceName();
                            String DeliveryRequestStoreName =deliveryCompleteList_data1.getDeliveryRequestStoreName();
                            String DeliveryRequestAddress =deliveryCompleteList_data1.getDeliveryRequestAddress();
                            String DeliveryRequestDate =deliveryCompleteList_data1.getDeliveryRequestDate();
                            String DeliveryRequestDueDate =deliveryCompleteList_data1.getDeliveryRequestDueDate();
                            String DeliveryRequestTime =deliveryCompleteList_data1.getDeliveryRequestTime();
                            String DeliveryRequestStatus =deliveryCompleteList_data1.getDeliveryRequestStatus();
                            String DeliveryLocation =deliveryCompleteList_data1.getLocation();
                            String DeliveryCustomerName =deliveryCompleteList_data1.getCustomerName();
                            int DeliveryCustomerId =deliveryCompleteList_data1.getCustomerId();
                            String DeliveryOtp =deliveryCompleteList_data1.getDeliveryOTP();
                            Integer deliveryRequestTotalAmount =deliveryCompleteList_data1.getDeliveryRequestTotalAmount();
                            float deliveryRequestTotalTax = deliveryCompleteList_data1.getDeliveryRequestTotalTax();
                            Integer deliveryRequestTotalCharge =deliveryCompleteList_data1.getDeliveryRequestTotalCharge();
                            Integer deliveryRequestTotalDiscount =deliveryCompleteList_data1.getDeliveryRequestTotalDiscount();
                            float deliveryRequestTotalRoundOff =deliveryCompleteList_data1.getDeliveryRequestTotalRoundOff();
                            Integer deliveryRequestNetAmount =deliveryCompleteList_data1.getDeliveryRequestNetAmount();

                            Log.e("Delivery Status","******Delivery Status********"+DeliveryRequestStatus);

                            if (DeliveryRequestStatus.equals("Delivered")){
                                deliveryCompleteListData_temp.add(new DeliverySchedualedList_Data(DeliveryRequestId,DeliveryRequestServiceType,DeliveryRequestServiceName,DeliveryRequestStoreName,DeliveryRequestAddress,DeliveryRequestDate,DeliveryRequestDueDate,DeliveryRequestTime,DeliveryRequestStatus,DeliveryLocation,DeliveryCustomerName,DeliveryCustomerId,DeliveryOtp, deliveryRequestTotalAmount,deliveryRequestTotalTax,deliveryRequestTotalCharge,deliveryRequestTotalDiscount,deliveryRequestTotalRoundOff,deliveryRequestNetAmount));
                                deliveryCompliteAdapter = new DeliveryCompliteAdapter(deliveryCompleteListData_temp, getContext());
                                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                recyclerView.setAdapter(deliveryCompliteAdapter);
                                deliveryCompliteAdapter.notifyDataSetChanged();
                            }else {
                                //Toast.makeText(getContext(), "Pickup Not Completed", Toast.LENGTH_SHORT).show();
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

                                if (Location.getSelectedItem().toString().equalsIgnoreCase("All Location")) {

                                    deliveryCompleteListData = selectDeliveryScheduled_responce_modal.getDeliveryList();
                                    deliveryCompliteAdapter = new DeliveryCompliteAdapter(deliveryCompleteListData_temp, getContext());
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerView.setAdapter(deliveryCompliteAdapter);
                                    deliveryCompliteAdapter.notifyDataSetChanged();

                                } else {
                                    filter(Location.getSelectedItem().toString());

                                }

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> parent) {
                            }
                        });


                    } else {
                        Toast.makeText(getContext(), selectDeliveryScheduled_responce_modal.getErrorMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SelectDeliveryScheduled_responce_modal> call, Throwable t) {
                Toast.makeText(getContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    //****************filter location****************************
    private void filter(String text) {
        ArrayList<DeliverySchedualedList_Data> filteredList = new ArrayList<>();

        for (DeliverySchedualedList_Data androidVersion : deliveryCompleteListData_temp) {

            if (androidVersion.getLocation().contains(text)) {

                filteredList.add(androidVersion);
            }
        }

        deliveryCompliteAdapter.filterList(filteredList);

    }

    //****************filter location****************************

    private void Deliverycomplited(){
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

//        database = new SqliteDatabase(getContext());
//        deliveryRequestComplitedModals =database.getRequestPickup();
//        pickupCompletedAdapter = new (deliveryRequestComplitedModals, getActivity());
//        recyclerView.setAdapter(pickupCompletedAdapter);

    }
}