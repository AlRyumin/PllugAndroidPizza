package com.example.alryumin.pizzapllugalryumin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ItemFragment extends Fragment {
    private View root;
    private static final String TEST_BROADCAST_ORDER =
            "TEST_BROADCAST_ORDER";

    private ImageView image;
    private TextView name, price, status, description, composition;
    private Button callButton, orderButton;
    private Handler handler;
    private boolean ordered = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        root = inflater.inflate(R.layout.fragment_item, container, false);
        return root;
    }


    @Override
    public void onResume(){
        super.onResume();

        initValues();
//        startHandler();
        initListener();
        initReceivers();
    }

    @Override
    public void onPause() {
        unregisterReceivers();
        super.onPause();
    }

    public void initView(){
        image = (ImageView) root.findViewById(R.id.main_image);

        name = (TextView) root.findViewById(R.id.name);
        price = (TextView) root.findViewById(R.id.price);
        description = (TextView) root.findViewById(R.id.description);
        composition = (TextView) root.findViewById(R.id.composition);
        status = (TextView) root.findViewById(R.id.status);

        callButton = (Button) root.findViewById(R.id.call_button);
        orderButton = (Button) root.findViewById(R.id.order_button);
    }

    BroadcastReceiver orderReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ordered = !ordered;

            if(ordered){
                status.setText(R.string.ordered);
            } else {
                status.setText(R.string.not_ordered);
            }
        }
    };

    private void initReceivers() {
        IntentFilter orderIntentFiler = new IntentFilter(TEST_BROADCAST_ORDER);
        getActivity().registerReceiver(orderReceiver, orderIntentFiler);
    }

    private void unregisterReceivers() {
        getActivity().unregisterReceiver(orderReceiver);
    }

    private void initListener() {
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TEST_BROADCAST_ORDER);
                getActivity().sendBroadcast(intent);
            }
        });
    }

    private void startHandler() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TEST_BROADCAST_ORDER);
                getActivity().sendBroadcast(intent);
            }
        }, 5000);
    }

    private void initValues(){
        name.setText(R.string.pizza_name_1);
        image.setImageResource(R.drawable.pizza);
        price.setText(R.string.pizza_price_1);
        description.setText(R.string.pizza_description_1);
        composition.setText(R.string.pizza_composition_1);
    }

}
