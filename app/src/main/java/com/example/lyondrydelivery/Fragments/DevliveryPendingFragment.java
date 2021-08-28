package com.example.lyondrydelivery.Fragments;

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
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.lyondrydelivery.Adapter.DeliveryAdapter;
import com.example.lyondrydelivery.Adapter.HomeAdapter;
import com.example.lyondrydelivery.Class.SharedPrefManager;
import com.example.lyondrydelivery.Modal.HomeModal;
import com.example.lyondrydelivery.Modal.ItemModal;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_data;
import com.example.lyondrydelivery.Modal.SelectPickupDelivery_responce_modal;
import com.example.lyondrydelivery.Modal.SelecteDelivery.DeliveryDetailsList_data;
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

import static android.content.Context.MODE_PRIVATE;

public class DevliveryPendingFragment extends Fragment {

    public DevliveryPendingFragment() {

    }
    RecyclerView recyclerView;
    DeliveryAdapter deliveryAdapter;
    SharedPrefManager sharedPrefManager;
    String token,UserName;
    String location;
    Spinner Location;
    List<DeliverySchedualedList_Data>deliverySchedualedListData = new ArrayList<>();
    List<DeliverySchedualedList_Data>deliverySchedualedListData_temp = new ArrayList<>();
    List<DeliverySchedualedList_Data> deliverySchedualedList_data;
    DeliverySchedualedList_Data deliverySchedualedList_data1;
    List<DeliveryDetailsList_data>deliveryDetailsList_data= new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_devlivery_pending,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.delivery_pending_recycleview1);
        Location = view.findViewById(R.id.sp_location);
        sharedPrefManager = new SharedPrefManager(getContext());
        token = sharedPrefManager.getTokenOtp().getHttpResponseHeader();
        UserName = sharedPrefManager.getLoginUser().getUserName();

        deliveryPendingSchedualeFragment();
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

                            deliverySchedualedListData = selectDeliveryScheduled_responce_modal.getDeliveryList();
                            for (DeliverySchedualedList_Data delivery_data : deliverySchedualedListData){

                                Log.e("Pickup Status" , ""+delivery_data.getDeliveryRequestId()+"\n"+delivery_data.getDeliveryRequestStatus());

                            }
                            //deliveryDetailsList_data = selectDeliveryScheduled_responce_modal.getDeliveryDetailsList();
//                            for (int j =0; j<deliveryDetailsList_data.size(); j++){
//                                DeliveryDetailsList_data deliveryDetailsList_data1 = (DeliveryDetailsList_data)deliveryDetailsList_data;
//                                Float itemName = deliveryDetailsList_data1.getOrderDetailsPrice();
//                                int ItemQuantity = deliveryDetailsList_data1.getOrderDetailsTotalNo();
//                                String ItemCode = deliveryDetailsList_data1.getOrderDetailsItemCode();
//
//                            }
                            //deliverySchedualedListData = selectDeliveryScheduled_responce_modal.getDeliveryList();
//                            deliveryAdapter = new DeliveryAdapter(deliverySchedualedListData, getContext());
//                            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//                            recyclerView.setAdapter(deliveryAdapter);
//                            deliveryAdapter.notifyDataSetChanged();

                            deliverySchedualedList_data = selectDeliveryScheduled_responce_modal.getDeliveryList();


                            String[] location1 = new String[deliverySchedualedList_data.size()];
                            for (int i = 0; i < deliverySchedualedList_data.size(); i++) {
                                deliverySchedualedList_data1 = (DeliverySchedualedList_Data) deliverySchedualedList_data.get(i);
                                location = deliverySchedualedList_data1.getLocation();
                                location1[i] = location;

                                Integer DeliveryRequestId =deliverySchedualedList_data1.getDeliveryRequestId();
                                String DeliveryRequestServiceType =deliverySchedualedList_data1.getDeliveryRequestServiceType();
                                String DeliveryRequestServiceName =deliverySchedualedList_data1.getDeliveryRequestServiceName();
                                String DeliveryRequestStoreName =deliverySchedualedList_data1.getDeliveryRequestStoreName();
                                String DeliveryRequestAddress =deliverySchedualedList_data1.getDeliveryRequestAddress();
                                String DeliveryRequestDate =deliverySchedualedList_data1.getDeliveryRequestDate();
                                String DeliveryRequestDueDate =deliverySchedualedList_data1.getDeliveryRequestDueDate();
                                String DeliveryRequestTime =deliverySchedualedList_data1.getDeliveryRequestTime();
                                String DeliveryRequestStatus =deliverySchedualedList_data1.getDeliveryRequestStatus();
                                String DeliveryLocation =deliverySchedualedList_data1.getLocation();
                                String DeliveryCustomerName =deliverySchedualedList_data1.getCustomerName();
                                int DeliveryCustomerId =deliverySchedualedList_data1.getCustomerId();
                                String DeliveryOtp =deliverySchedualedList_data1.getDeliveryOTP();
                                Integer deliveryRequestTotalAmount =deliverySchedualedList_data1.getDeliveryRequestTotalAmount();
                                float deliveryRequestTotalTax = deliverySchedualedList_data1.getDeliveryRequestTotalTax();
                                Integer deliveryRequestTotalCharge =deliverySchedualedList_data1.getDeliveryRequestTotalCharge();
                                Integer deliveryRequestTotalDiscount =deliverySchedualedList_data1.getDeliveryRequestTotalDiscount();
                                float deliveryRequestTotalRoundOff =deliverySchedualedList_data1.getDeliveryRequestTotalRoundOff();
                                Integer deliveryRequestNetAmount =deliverySchedualedList_data1.getDeliveryRequestNetAmount();

                                Log.e("Delivery Status","******Delivery Status********"+DeliveryRequestStatus);

                                if (DeliveryRequestStatus.equals("Delivery Scheduled")){
                                    deliverySchedualedListData_temp.add(new DeliverySchedualedList_Data(DeliveryRequestId,DeliveryRequestServiceType,DeliveryRequestServiceName,DeliveryRequestStoreName,DeliveryRequestAddress,DeliveryRequestDate,DeliveryRequestDueDate,DeliveryRequestTime,DeliveryRequestStatus,DeliveryLocation,DeliveryCustomerName,DeliveryCustomerId,DeliveryOtp, deliveryRequestTotalAmount,deliveryRequestTotalTax,deliveryRequestTotalCharge,deliveryRequestTotalDiscount,deliveryRequestTotalRoundOff,deliveryRequestNetAmount));
                                    deliveryAdapter = new DeliveryAdapter(deliverySchedualedListData_temp, getContext());
                                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                    recyclerView.setAdapter(deliveryAdapter);
                                    deliveryAdapter.notifyDataSetChanged();
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

                                        deliverySchedualedListData = selectDeliveryScheduled_responce_modal.getDeliveryList();
                                        deliveryAdapter = new DeliveryAdapter(deliverySchedualedListData_temp, getContext());
                                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                                        recyclerView.setAdapter(deliveryAdapter);
                                        deliveryAdapter.notifyDataSetChanged();

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

        for (DeliverySchedualedList_Data androidVersion : deliverySchedualedListData_temp) {

            if (androidVersion.getLocation().contains(text)) {

                filteredList.add(androidVersion);
            }
        }

        deliveryAdapter.filterList(filteredList);

    }

    //****************filter location****************************

}