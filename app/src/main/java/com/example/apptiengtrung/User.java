package com.example.apptiengtrung;

import java.security.PublicKey;

public class User {
    public int id;
    public boolean isAdmin;
    public boolean isTeacher;
    public String username;
    public String password;
    public int score;

    public User() {

    }

    public User(int id, String username, String password, int score, boolean isTeacher, boolean isAdmin) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.score = score;
        this.isTeacher = isTeacher;
        this.isAdmin = isAdmin;
    }
}
