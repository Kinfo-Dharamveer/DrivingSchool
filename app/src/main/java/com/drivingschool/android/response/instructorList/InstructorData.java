package com.drivingschool.android.response.instructorList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public abstract class InstructorData {

    @Expose
    @SerializedName("instructors")
    private List<InstructorsModel> instructors;

    public List<InstructorsModel> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<InstructorsModel> instructors) {
        this.instructors = instructors;
    }
}

