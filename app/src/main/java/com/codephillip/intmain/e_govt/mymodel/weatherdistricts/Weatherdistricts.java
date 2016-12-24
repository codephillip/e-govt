
package com.codephillip.intmain.e_govt.mymodel.weatherdistricts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Weatherdistricts {

    @SerializedName("cnt")
    @Expose
    private Integer cnt;
    @SerializedName("list")
    @Expose
    private java.util.List<ListWeather> listWeather = null;

    public Integer getCnt() {
        return cnt;
    }

    public void setCnt(Integer cnt) {
        this.cnt = cnt;
    }

    public java.util.List<ListWeather> getListWeather() {
        return listWeather;
    }

    public void setListWeather(java.util.List<ListWeather> listWeather) {
        this.listWeather = listWeather;
    }

}
