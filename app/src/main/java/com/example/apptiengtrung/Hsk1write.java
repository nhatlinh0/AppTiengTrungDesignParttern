package com.example.apptiengtrung;

public class Hsk1write {
    public int id;
    public String trung;
    public String phienAm;
    public int image;
    public String correctAnswer;

    public Hsk1write() {}

    public Hsk1write (int id, int image, String trung, String phienAm, String answer){
        this.id = id;
        this.trung = trung;
        this.phienAm = phienAm;
        this.image = image;
        this.correctAnswer = answer;
    }
}
