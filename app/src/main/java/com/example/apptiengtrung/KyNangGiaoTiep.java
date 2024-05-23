package com.example.apptiengtrung;

public class KyNangGiaoTiep {
    public int id;
    public String title;
    public int image;
    public String[] list;
    public KyNangGiaoTiep() {

    }

    public KyNangGiaoTiep (int id, String title, int image, String[] list) {
        this.id  = id;
        this.title = title;
        this.image = image;
        this.list = list;
    }

}
