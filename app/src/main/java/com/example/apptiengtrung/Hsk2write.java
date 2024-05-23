package com.example.apptiengtrung;

public class Hsk2write {
    public int id;
    public int image;
    public String trung;
    public String phienAm;
    public String correctAnswer;

    public Hsk2write () {}

    public Hsk2write(int id, int image, String trung, String phienAm, String correctAnswer) {
        this.id = id;
        this.image = image;
        this.trung = trung;
        this.phienAm = phienAm;
        this.correctAnswer = correctAnswer;
    }
}
