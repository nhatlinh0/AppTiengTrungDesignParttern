package com.example.apptiengtrung;

public class KyNangGiaoTiepFactory {
    public static KyNangGiaoTiep createKyNangGiaoTiep(int id, String title, int image, String[] list) {
        return new KyNangGiaoTiep(id, title, image, list);
    }
}
