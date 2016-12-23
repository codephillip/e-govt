
package com.codephillip.intmain.e_govt.mymodel.chapters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Chapters {

    @SerializedName("chapters")
    @Expose
    private List<Chapter> chapters = null;

    public List<Chapter> getChapters() {
        return chapters;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

}
