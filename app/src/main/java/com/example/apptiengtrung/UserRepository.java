package com.example.apptiengtrung;

public interface UserRepository {
    void addUser(User user, DatabaseCallback callback);
    void getUsers(DatabaseCallback callback);
    void getUser(int id, DatabaseCallback callback);
}
