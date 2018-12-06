package com.drivingschool.android.models;

import android.media.Image;


import java.util.List;

public class PlansList {

    String planName, type,description,price;

    public PlansList(String planName, String type, String description, String price) {
        this.planName = planName;
        this.type = type;
        this.description = description;
        this.price = price;
    }



    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
