package com.drivingschool.android.data;

public class MessageEvent {

    public String mStatus;

    public MessageEvent(String status){
        mStatus = status;
    }

    public String getStatus(){
        return mStatus;
    }

}
