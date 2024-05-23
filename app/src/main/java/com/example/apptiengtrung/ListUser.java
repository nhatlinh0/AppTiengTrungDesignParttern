package com.example.apptiengtrung;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListUser {
    public static ArrayList<User> list;

    public static void init() {
        list = new ArrayList<>();
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list = new ArrayList<>();
                for(DataSnapshot snap : snapshot.getChildren()) {
                    User user = (User) snap.getValue(User.class);
                    list.add(user);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public boolean isExist(String user) {
        for (int i =0 ; i < list.size(); i++) {
            if(list.get(i).username.equals(user))
            {
                return false;
            }
        }
        return true;
    }

    public static int pos(String user) {
        for (int i =0 ; i < list.size(); i++) {
            if(list.get(i).username.equals(user))
            {
                return i+1;
            }
        }
        return -1;
    }

    public static User findUser(String user) {
        User us = new User();
        for (int i =0 ; i < list.size(); i++) {
            if(list.get(i).username.equals(user))
            {
                us = list.get(i);
            }
        }
        return us;
    }

    public static boolean checkTeacher(String username) {
        for (int i =0 ; i < list.size(); i++) {
            if(list.get(i).isTeacher == true && list.get(i).username.equals(username))
            {
                return true;
            }
        }
        return false;
    }



    public static boolean checkAdmin(String username) {
        for (int i =0 ; i < list.size(); i++) {
            if(list.get(i).isAdmin == true && list.get(i).username.equals(username))
            {
                return true;
            }
        }
        return false;
    }
}
