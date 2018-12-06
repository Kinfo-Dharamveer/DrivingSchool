package com.drivingschool.android.models;

public class PackageList {

    private String packageName, duation;


    public PackageList(String packageName, String duation) {
        this.packageName = packageName;
        this.duation = duation;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getDuation() {
        return duation;
    }

    public void setDuation(String duation) {
        this.duation = duation;
    }
}
