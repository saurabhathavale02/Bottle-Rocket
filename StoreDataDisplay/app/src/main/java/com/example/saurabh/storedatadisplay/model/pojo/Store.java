
package com.example.saurabh.storedatadisplay.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Store implements Serializable {

    @SerializedName("address")
    @Expose
    private String address;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("zipcode")
    @Expose
    private String zipcode;
    @SerializedName("storeLogoURL")
    @Expose
    private String storeLogoURL;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("storeID")
    @Expose
    private String storeID;
    @SerializedName("state")
    @Expose
    private String state;

    public Store(String state, String address, String city, String name, String latitude, String zipcode, String storeLogoURL, String phone, String longitude, String storeID) {
        this.state = state;
        this.address = address;
        this.city = city;
        this.name = name;
        this.latitude = latitude;
        this.zipcode = zipcode;
        this.storeLogoURL = storeLogoURL;
        this.phone = phone;
        this.longitude = longitude;
        this.storeID = storeID;
    }


    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getName() {
        return name;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getZipcode() {
        return zipcode;
    }

    public String getStoreLogoURL() {
        return storeLogoURL;
    }

    public String getPhone() {
        return phone;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getStoreID() {
        return storeID;
    }

    public String getState() {
        return state;
    }
}
