package com.example.alryumin.pizzapllugalryumin;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private static final String TEST_BROADCAST_ORDER =
            "TEST_BROADCAST_ORDER";

    private ImageView image;
    private TextView name, price, status, description, composition;
    private Button callButton, orderButton;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    @Override
    protected void onResume(){
        super.onResume();

        initValues();
//        startHandler();
        initListener();
        initReceivers();
    }

    @Override
    protected void onPause() {
        unregisterReceivers();
        super.onPause();
    }

    public void initView(){
        image = (ImageView) findViewById(R.id.main_image);

        name = (TextView) findViewById(R.id.name);
        price = (TextView) findViewById(R.id.price);
        description = (TextView) findViewById(R.id.description);
        composition = (TextView) findViewById(R.id.composition);
        status = (TextView) findViewById(R.id.status);

        callButton = (Button) findViewById(R.id.call_button);
        orderButton = (Button) findViewById(R.id.order_button);
    }

    BroadcastReceiver orderReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
//            if (status.getText().equals(getText(R.string.not_ordered))) {
                status.setText(getText(R.string.ordered));
//            }
        }
    };

    private void initReceivers() {
        IntentFilter orderIntentFiler = new IntentFilter(TEST_BROADCAST_ORDER);
        registerReceiver(orderReceiver, orderIntentFiler);
    }

    private void unregisterReceivers() {
        unregisterReceiver(orderReceiver);
    }

    private void initListener() {
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TEST_BROADCAST_ORDER);
                sendBroadcast(intent);
            }
        });
    }

    private void startHandler() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(TEST_BROADCAST_ORDER);
                sendBroadcast(intent);
            }
        }, 5000);
    }

    private void initValues(){
        name.setText(R.string.pizza_name_1);
        image.setImageDrawable(getResources().getDrawable(R.drawable.pizza));
        price.setText(R.string.pizza_price_1);
        description.setText(R.string.pizza_description_1);
        composition.setText(R.string.pizza_composition_1);

//        callButton.setOnClickListener(this);
//        orderButton.setOnClickListener(this);
    }
//    @Override
//    public void onClick(View v) {
//        switch (v.getId()){
//            case R.id.orderButton:

//                break;
//        }
//    }
}
