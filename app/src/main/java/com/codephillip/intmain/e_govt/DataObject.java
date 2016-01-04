package com.codephillip.intmain.e_govt;

/**
 * Created by codephillip on 1/4/16.
 */
public class DataObject {
    private String day;
    private String lunch;

    public DataObject(String a, String b) {
        this.day = a;
        this.lunch = b;
    }

//    public String toString() {
//        return "day = " + day + ", lunch = " + lunch;
//    }

    public static String returnJson(){
        return "{\"day\":\"thursdaycode\",\"lunch\":\"food\"}";
    }
}
