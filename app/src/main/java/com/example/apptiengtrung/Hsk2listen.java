package com.example.apptiengtrung;

public class Hsk2listen {
    public int id;
    public int audio;
    public int image1;
    public int image2;
    public int image3;
    public String correctAnswer;

    public  Hsk2listen() {}
    public Hsk2listen(int id, int audio, int image1, int image2, int image3, String correctAnswer){
        this.id = id;
        this.audio = audio;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.correctAnswer = correctAnswer;
    }
}
