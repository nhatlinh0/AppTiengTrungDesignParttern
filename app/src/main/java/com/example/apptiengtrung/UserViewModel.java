package com.example.apptiengtrung;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserViewModel extends ViewModel {

//    Repository Pattern

    private UserRepository userRepository;
    private MutableLiveData<ArrayList<User>> userList;

    public UserViewModel() {
        this.userRepository = new FirebaseUserRepository();
        //this.userList = new MutableLiveData<>();
    }
    public LiveData<ArrayList<User>> getUserList() {
        if (userList == null) {
            userList = new MutableLiveData<>();
            //loadUserList();
            loadAccountList();
        }
        return userList;
    }

    public LiveData<ArrayList<User>> getUserList1() {
        if (userList == null) {
            userList = new MutableLiveData<>();
            loadUserList();
            //loadAccountList();
        }
        return userList;
    }

    private void loadUserList() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<User> users = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (user != null && !user.isTeacher && !user.isAdmin) {
                        users.add(user);
                    }
                }
                userList.setValue(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    private void loadAccountList() {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<User> users = new ArrayList<>();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    if (!user.username.equals(LoginActivity.username)) {
                        users.add(user);
                    }
                }
                userList.setValue(users);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle error
            }
        });
    }
}
