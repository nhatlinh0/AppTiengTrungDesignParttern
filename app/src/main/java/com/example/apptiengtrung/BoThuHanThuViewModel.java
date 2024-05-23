package com.example.apptiengtrung;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class BoThuHanThuViewModel extends ViewModel {

    private final MutableLiveData<List<BoThuHanThu>> boThuHanThuList;

    public BoThuHanThuViewModel() {
        boThuHanThuList = new MutableLiveData<>();
        loadBoThuHanThuData();
    }

    public LiveData<List<BoThuHanThu>> getBoThuHanThuList() {
        return boThuHanThuList;
    }

    private void loadBoThuHanThuData() {
        FirebaseUtils.getDatabaseReference().child("BoThuHanThu").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                List<BoThuHanThu> list = new ArrayList<>();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    BoThuHanThu bo = snap.getValue(BoThuHanThu.class);
                    list.add(bo);
                }
                boThuHanThuList.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error if needed
            }
        });
    }
}

