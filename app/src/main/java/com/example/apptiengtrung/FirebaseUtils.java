package com.example.apptiengtrung;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseUtils {
    private static DatabaseReference databaseReference;

    private FirebaseUtils() {}

    public static DatabaseReference getDatabaseReference() {
        if (databaseReference == null) {
            databaseReference = FirebaseDatabase.getInstance().getReference();
        }
        return databaseReference;
    }
}

