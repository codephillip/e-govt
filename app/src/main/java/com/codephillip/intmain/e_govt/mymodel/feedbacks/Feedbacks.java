
package com.codephillip.intmain.e_govt.mymodel.feedbacks;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Feedbacks {

    @SerializedName("feedback")
    @Expose
    private List<Feedback> feedback = null;

    public List<Feedback> getFeedback() {
        return feedback;
    }

    public void setFeedback(List<Feedback> feedback) {
        this.feedback = feedback;
    }

}
