package com.example.lyondrydelivery.Class;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.lyondrydelivery.Activity.DeliveryActivity;
import com.example.lyondrydelivery.Activity.MainActivity;
import com.example.lyondrydelivery.Activity.PickupActivity;
import com.example.lyondrydelivery.Activity.ReportsActivity;
import com.example.lyondrydelivery.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BottomNavigationFragment extends Fragment implements View.OnClickListener {

    View view;
    RelativeLayout Home_rl,Pickup_rl,Delivery_rl,Reports_rl;
    TextView Tv1,Tv2,Tv3,Tv4;
    ImageView IV1,Iv2,Iv3,Iv4;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getChildFragmentManager();
        view =inflater.inflate(R.layout.bottom_layout,container,false);
        return view;

    }

    @Nullable
    @Override
    public View getView() {
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initailizeComponets();
    }

    public void initailizeComponets() {

        if (getView() == null)return;
        Home_rl = getView().findViewById(R.id.home_rl);
        Pickup_rl = getView().findViewById(R.id.pickup_rl);
        Delivery_rl = getView().findViewById(R.id.delivery_rl);
        Reports_rl = getView().findViewById(R.id.reports_rl);

        Tv1 = getView().findViewById(R.id.tv1);
        Tv2 = getView().findViewById(R.id.tv2);
        Tv3 = getView().findViewById(R.id.tv3);
        Tv4 = getView().findViewById(R.id.tv4);

        IV1 = getView().findViewById(R.id.iv_1);
        Iv2 = getView().findViewById(R.id.iv_2);
        Iv3 = getView().findViewById(R.id.iv_3);
        Iv4 = getView().findViewById(R.id.iv_4);

        Home_rl.setOnClickListener(this);
        Pickup_rl.setOnClickListener(this);
        Delivery_rl.setOnClickListener(this);
        Reports_rl.setOnClickListener(this);

        setBottomNavigationView();

    }

    private void setBottomNavigationView() {

        int cyan_color = getActivity().getResources().getColor(R.color.cyan_light);
        int black_color = getActivity().getResources().getColor(R.color.bottom_menu);

        if (getActivity() !=null && getActivity() instanceof MainActivity){
            IV1.setColorFilter(cyan_color);
            Iv2.setColorFilter(black_color);
            Iv3.setColorFilter(black_color);
            Iv4.setColorFilter(black_color);

            Tv1.setTextColor(cyan_color);
            Tv2.setTextColor(black_color);
            Tv3.setTextColor(black_color);
            Tv4.setTextColor(black_color);
        }
        else if (getActivity() !=null && getActivity() instanceof PickupActivity){
            IV1.setColorFilter(black_color);
            Iv2.setColorFilter(cyan_color);
            Iv3.setColorFilter(black_color);
            Iv4.setColorFilter(black_color);

            Tv1.setTextColor(black_color);
            Tv2.setTextColor(cyan_color);
            Tv3.setTextColor(black_color);
            Tv4.setTextColor(black_color);
        }

        else if (getActivity() !=null && getActivity() instanceof DeliveryActivity){
            IV1.setColorFilter(black_color);
            Iv2.setColorFilter(black_color);
            Iv3.setColorFilter(cyan_color);
            Iv4.setColorFilter(black_color);

            Tv1.setTextColor(black_color);
            Tv2.setTextColor(black_color);
            Tv3.setTextColor(cyan_color);
            Tv4.setTextColor(black_color);
        }
        else if (getActivity() !=null && getActivity() instanceof ReportsActivity){
            IV1.setColorFilter(black_color);
            Iv2.setColorFilter(black_color);
            Iv3.setColorFilter(black_color);
            Iv4.setColorFilter(cyan_color);

            Tv1.setTextColor(black_color);
            Tv2.setTextColor(black_color);
            Tv3.setTextColor(black_color);
            Tv4.setTextColor(cyan_color);
        }
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        getChildFragmentManager();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case   R.id.home_rl:
                Intent intent =new Intent(getActivity(),MainActivity.class);
                getActivity().startActivity(intent);
                break;
            case   R.id.pickup_rl:
                Intent intent2 =new Intent(getActivity(), PickupActivity.class);
                getActivity().startActivity(intent2);
                break;

            case   R.id.delivery_rl:
                Intent intent4 =new Intent(getActivity(), DeliveryActivity.class);
                getActivity().startActivity(intent4);
                break;
            case   R.id.reports_rl:
                Intent intent5 =new Intent(getActivity(), ReportsActivity.class);
                getActivity().startActivity(intent5);
                break;
        }

    }
}
