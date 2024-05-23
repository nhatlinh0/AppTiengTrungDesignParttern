package com.example.apptiengtrung;

public class Caunoi {
    private String Caunoi;
    private int ChudeId;
    private String Dichthuat;
    private int Id;
    private String Phienam;

    public Caunoi(String caunoi, int chudeId, String dichthuat, int id, String phienam) {
        this.Caunoi = caunoi;
        this.ChudeId = chudeId;
        this.Dichthuat = dichthuat;
        this.Id = id;
        this.Phienam = phienam;
    }

    // Getters and Setters
    public String getCaunoi() {
        return Caunoi;
    }

    public void setCaunoi(String caunoi) {
        this.Caunoi = caunoi;
    }

    public int getChudeId() {
        return ChudeId;
    }

    public void setChudeId(int chudeId) {
        this.ChudeId = chudeId;
    }

    public String getDichthuat() {
        return Dichthuat;
    }

    public void setDichthuat(String dichthuat) {
        this.Dichthuat = dichthuat;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getPhienam() {
        return Phienam;
    }

    public void setPhienam(String phienam) {
        this.Phienam = phienam;
    }
}
