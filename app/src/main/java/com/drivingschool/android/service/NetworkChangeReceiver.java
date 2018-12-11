package com.drivingschool.android.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.drivingschool.android.data.Constants;
import com.drivingschool.android.data.MessageEvent;
import com.drivingschool.android.ui.MainActivity;
import com.drivingschool.android.utils.NetworkUtil;

import org.greenrobot.eventbus.EventBus;


public class NetworkChangeReceiver extends BroadcastReceiver {
    Context mContext;


    @Override
    public void onReceive(Context context, Intent intent) {
        mContext = context;
        String status = NetworkUtil.getConnectivityStatusString(context);

        Log.e("Receiver ", "" + status);

        if (status.equals(Constants.NOT_CONNECT)) {
            Log.e("Receiver ", "not connection");// your code when internet lost
           // checkNetStatus(true);
            EventBus.getDefault().post(new MessageEvent(status));



        } else {
           // checkNetStatus(false);
            EventBus.getDefault().post(new MessageEvent(status));

            Log.e("Receiver ", "connected to internet");//your code when internet connection come back
        }
       // MainActivity.addLogText(status);

    }




}
