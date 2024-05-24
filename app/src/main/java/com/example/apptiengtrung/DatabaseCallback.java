package com.example.apptiengtrung;

public interface DatabaseCallback<T> {
    void onSuccess(T result);
    void onError(Exception e);
}
