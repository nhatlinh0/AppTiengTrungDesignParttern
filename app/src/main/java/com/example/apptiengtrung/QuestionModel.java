package com.example.apptiengtrung;

import java.util.ArrayList;
import java.util.List;

public class QuestionModel {
    private String question;
    private List<String> options;
    private String correct;

    private String ImagePath;

    public String getImagePath() {
        return ImagePath;
    }

    public void setImagePath(String imagePath) {
        ImagePath = imagePath;
    }

    public QuestionModel(String question, List<String> options, String correct) {
        this.question = question;
        this.options = options;
        this.correct = correct;
    }

    public QuestionModel() {
        this("", new ArrayList<>(), "");
    }

    // Getters and Setters
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }

    public String getCorrect() {
        return correct;
    }

    public void setCorrect(String correct) {
        this.correct = correct;
    }
}
