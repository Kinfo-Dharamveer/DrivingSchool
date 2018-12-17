package com.drivingschool.android.response.schooldetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PlanPayload {

   private String name,price,desc;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
