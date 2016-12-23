
package com.codephillip.intmain.e_govt.mymodel.districts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Districts {

    @SerializedName("districts")
    @Expose
    private List<District> districts = null;

    public List<District> getDistricts() {
        return districts;
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }

}
