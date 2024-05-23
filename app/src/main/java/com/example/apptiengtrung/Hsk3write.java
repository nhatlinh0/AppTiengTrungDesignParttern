package com.example.apptiengtrung;

public class Hsk3write {
    public int id;
    public int image;
    public String answerTrung;
    public String answerPhienAm;
    public String trung;
    public String phienAm;
    public String correctAnswer;

    public Hsk3write () {}

    public Hsk3write(int id, String answerTrung, String answerPhienAm, String trung, String phienAm, String correctAnswer) {
        this.id = id;
        this.answerTrung = answerTrung;
        this.answerPhienAm = answerPhienAm;
        this.trung = trung;
        this.phienAm = phienAm;
        this.correctAnswer = correctAnswer;
    }
}
