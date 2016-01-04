package com.codephillip.intmain.e_govt;

/**
 * Created by codephillip on 1/4/16.
 */
public class DataObject {
    private int a;
    private String b;

    public DataObject(int a, String b) {
        this.a = a;
        this.b = b;
    }

    public String toString() {
        return "a = " +a+ ", b = " +b;
    }
}
