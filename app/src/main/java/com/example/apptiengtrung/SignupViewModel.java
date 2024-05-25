package com.example.apptiengtrung;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignupViewModel extends AndroidViewModel {
    private final DatabaseReference databaseReference;
    private final MutableLiveData<String> errorMessage = new MutableLiveData<>();
    private final MutableLiveData<Boolean> registrationSuccess = new MutableLiveData<>();
    private int count;

    public SignupViewModel(@NonNull Application application) {
        super(application);
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        count = 1;

        // Fetch the current user count
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count = (int) snapshot.getChildrenCount() + 1;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                errorMessage.setValue("Failed to get user count");
            }
        });
    }

    public LiveData<String> getErrorMessage() {
        return errorMessage;
    }

    public LiveData<Boolean> getRegistrationSuccess() {
        return registrationSuccess;
    }

    public void registerUser(String username, String password, String passwordConfirm) {
        if (username.isEmpty() || password.isEmpty() || passwordConfirm.isEmpty()) {
            errorMessage.setValue("Please fill in all fields");
            return;
        }

        if (!password.equals(passwordConfirm)) {
            errorMessage.setValue("Password confirmation does not match");
            return;
        }

        User user = new User(count, username, password, 0, false, false);
        databaseReference.orderByChild("username").equalTo(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    errorMessage.setValue("Username already exists");
                } else {
                    databaseReference.child(String.valueOf(count)).setValue(user).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            registrationSuccess.setValue(true);
                        } else {
                            errorMessage.setValue("Registration failed");
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                errorMessage.setValue("Database error");
            }
        });
    }
}
