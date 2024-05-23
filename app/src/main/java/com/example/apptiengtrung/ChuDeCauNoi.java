package com.example.apptiengtrung;

public class ChuDeCauNoi {
    private  int Id;
    private  String ImagePath;
    private  String Name;

    public ChuDeCauNoi() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}