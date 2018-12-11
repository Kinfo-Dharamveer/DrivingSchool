package com.drivingschool.android.response.schooldata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SchoolSuccess {

    @SerializedName("schools")
    @Expose
    private List<SchoolPayload> schools = null;

    public List<SchoolPayload> getSchools() {
        return schools;
    }

    public void setSchools(List<SchoolPayload> schools) {
        this.schools = schools;
    }


}
