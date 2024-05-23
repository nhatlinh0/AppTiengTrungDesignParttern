package com.example.apptiengtrung;

public class Hsk3listen {
    public int id;
    public int image;
    public int audio;
    public String correctAnswer;

    public Hsk3listen () {}

    public Hsk3listen(int id, int image, int audio, String correctAnswer) {
        this.id = id;
        this.image = image;
        this.audio = audio;
        this.correctAnswer = correctAnswer;
    }
}
