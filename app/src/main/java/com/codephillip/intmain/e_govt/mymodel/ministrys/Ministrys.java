
package com.codephillip.intmain.e_govt.mymodel.ministrys;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ministrys {

    @SerializedName("ministrys")
    @Expose
    private List<Ministry> ministrys = null;

    public List<Ministry> getMinistrys() {
        return ministrys;
    }

    public void setMinistrys(List<Ministry> ministrys) {
        this.ministrys = ministrys;
    }

}
