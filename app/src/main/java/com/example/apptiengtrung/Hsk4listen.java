package com.example.apptiengtrung;

public class Hsk4listen {
    public int id;
    public int audio;
    public String answerTrungA;
    public String answerTrungB;
    public String answerTrungC;
    public String answerPhienAmA;
    public String answerPhienAmB;
    public String answerPhienAmC;
    public String correctAnswer;

    public Hsk4listen(){}

    public Hsk4listen(int id, int audio, String answerTrungA, String answerPhienAmA, String answerTrungB, String answerPhienAmB, String answerTrungC,  String answerPhienAmC, String correctAnswer) {
        this.id = id;
        this.audio = audio;
        this.answerTrungA = answerTrungA;
        this.answerPhienAmA = answerPhienAmA;
        this.answerTrungB = answerTrungB;
        this.answerPhienAmB = answerPhienAmB;
        this.answerTrungC = answerTrungC;
        this.answerPhienAmC = answerPhienAmC;
        this.correctAnswer = correctAnswer;
    }
}
