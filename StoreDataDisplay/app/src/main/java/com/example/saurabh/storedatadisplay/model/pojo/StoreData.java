
package com.example.saurabh.storedatadisplay.model.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class StoreData {

    @SerializedName("stores")
    @Expose
    private List<Store> stores = new ArrayList<Store>();


    public List<Store> getStores() {
        return stores;
    }


    public void setStores(List<Store> stores) {
        this.stores = stores;
    }

}
