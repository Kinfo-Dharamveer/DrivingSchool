package com.drivingschool.android.response.schooldata;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SchoolPayload {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("owner_id")
    @Expose
    private Integer ownerId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("google_map_link")
    @Expose
    private Object googleMapLink;
    @SerializedName("facebook")
    @Expose
    private Object facebook;
    @SerializedName("twitter")
    @Expose
    private Object twitter;
    @SerializedName("google_plus")
    @Expose
    private Object googlePlus;
    @SerializedName("linkedin")
    @Expose
    private Object linkedin;
    @SerializedName("youtube")
    @Expose
    private Object youtube;
    @SerializedName("about_text")
    @Expose
    private Object aboutText;
    @SerializedName("instructors")
    @Expose
    private Object instructors;
    @SerializedName("vehicles")
    @Expose
    private Object vehicles;
    @SerializedName("upload")
    @Expose
    private Object upload;
    @SerializedName("created_at")
    @Expose
    private String createdAt;
    @SerializedName("updated_at")
    @Expose
    private String updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Integer ownerId) {
        this.ownerId = ownerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Object getGoogleMapLink() {
        return googleMapLink;
    }

    public void setGoogleMapLink(Object googleMapLink) {
        this.googleMapLink = googleMapLink;
    }

    public Object getFacebook() {
        return facebook;
    }

    public void setFacebook(Object facebook) {
        this.facebook = facebook;
    }

    public Object getTwitter() {
        return twitter;
    }

    public void setTwitter(Object twitter) {
        this.twitter = twitter;
    }

    public Object getGooglePlus() {
        return googlePlus;
    }

    public void setGooglePlus(Object googlePlus) {
        this.googlePlus = googlePlus;
    }

    public Object getLinkedin() {
        return linkedin;
    }

    public void setLinkedin(Object linkedin) {
        this.linkedin = linkedin;
    }

    public Object getYoutube() {
        return youtube;
    }

    public void setYoutube(Object youtube) {
        this.youtube = youtube;
    }

    public Object getAboutText() {
        return aboutText;
    }

    public void setAboutText(Object aboutText) {
        this.aboutText = aboutText;
    }

    public Object getInstructors() {
        return instructors;
    }

    public void setInstructors(Object instructors) {
        this.instructors = instructors;
    }

    public Object getVehicles() {
        return vehicles;
    }

    public void setVehicles(Object vehicles) {
        this.vehicles = vehicles;
    }

    public Object getUpload() {
        return upload;
    }

    public void setUpload(Object upload) {
        this.upload = upload;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }


}
