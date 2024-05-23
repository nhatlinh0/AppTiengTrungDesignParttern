package com.example.apptiengtrung;

public class Quiz {
    public String DapAnDung;
    public String DapAnA;
    public String DapAnB;
    public String DapAnC;
    public String DapAnD;
    public int audio;
    public Quiz() {

    }

    public Quiz (String dapAnDung, String a, String b, String c, String d, int audio) {
        this.DapAnDung = dapAnDung;
        this.DapAnA = a;
        this.DapAnB = b;
        this.DapAnC = c;
        this.DapAnD = d;
        this.audio = audio;
    }
}
