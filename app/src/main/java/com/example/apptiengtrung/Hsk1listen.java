package com.example.apptiengtrung;

public class Hsk1listen {
    public int id;
    public int audio;
    public int image;
    public String correctAnswer;

    public Hsk1listen (int id, int audio, int image, String answer){
        this.id = id;
        this.audio = audio;
        this.image = image;
        this.correctAnswer = answer;
    }
}
