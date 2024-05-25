package com.example.apptiengtrung;
import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThemTaiKhoanViewModel extends AndroidViewModel {
    private final DatabaseReference databaseReference;
    private final MutableLiveData<Integer> count = new MutableLiveData<>();
    private final MutableLiveData<Boolean> addSuccess = new MutableLiveData<>();

    public ThemTaiKhoanViewModel(@NonNull Application application) {
        super(application);
        databaseReference = FirebaseDatabase.getInstance().getReference("User");
        fetchCount();
    }

    private void fetchCount() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                count.setValue((int) snapshot.getChildrenCount() + 1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                count.setValue(1); // default value
            }
        });
    }

    public LiveData<Integer> getCount() {
        return count;
    }

    public LiveData<Boolean> getAddSuccess() {
        return addSuccess;
    }

    public void addUser(String username, String password, int score, boolean isTeacher, boolean isAdmin) {
        Integer currentCount = count.getValue();
        if (currentCount != null) {
            User user = new User(currentCount, username, password, score, isTeacher, isAdmin);
            databaseReference.child(currentCount.toString()).setValue(user)
                    .addOnCompleteListener(task -> addSuccess.setValue(task.isSuccessful()));
        }
    }
}
