package com.example.apptiengtrung;

import java.io.Serializable;

public class ListChude implements Serializable {
    private String Caunoi;
    private int ChudeId;
    private String Dichthuat;
    private int Pd;
    private String Phienam;

    public String getCaunoi() {
        return Caunoi;
    }

    public void setCaunoi(String caunoi) {
        Caunoi = caunoi;
    }

    public int getChudeId() {
        return ChudeId;
    }

    public void setChudeId(int chudeId) {
        ChudeId = chudeId;
    }

    public String getDichthuat() {
        return Dichthuat;
    }

    public void setDichthuat(String dichthuat) {
        Dichthuat = dichthuat;
    }

    public int getId() {
        return Pd;
    }

    public void setId(int id) {
        Pd = id;
    }

    public String getPhienam() {
        return Phienam;
    }

    public void setPhienam(String phienam) {
        Phienam = phienam;
    }

    public ListChude() {
    }


}
