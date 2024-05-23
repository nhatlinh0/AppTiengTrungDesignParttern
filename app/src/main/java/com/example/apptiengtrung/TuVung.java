package com.example.apptiengtrung;

public class TuVung {
    public int id;
    public String Trung;
    public String Viet;
    public String PhienAm;

    public TuVung() {}

    public TuVung(int id, String tieuDeTrung, String tieuDeViet, String phienAm) {
        this.id = id;
        this.Trung = tieuDeTrung;
        this.Viet = tieuDeViet;
        this.PhienAm = phienAm;
    }
}
