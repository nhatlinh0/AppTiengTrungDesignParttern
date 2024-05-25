package com.example.apptiengtrung;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SapXepViewModel extends ViewModel {
    private MutableLiveData<ArrayList<SapXep>> sapXepListLiveData = new MutableLiveData<>();
    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("SapXep");

    public LiveData<ArrayList<SapXep>> getSapXepListLiveData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<SapXep> sapXepList = new ArrayList<>();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    SapXep sapXep = snap.getValue(SapXep.class);
                    sapXepList.add(sapXep);
                }
                sapXepListLiveData.setValue(sapXepList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return sapXepListLiveData;
    }
}

