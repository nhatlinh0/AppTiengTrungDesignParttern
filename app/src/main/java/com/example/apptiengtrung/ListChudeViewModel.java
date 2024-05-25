package com.example.apptiengtrung;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListChudeViewModel extends ViewModel {

    private final MutableLiveData<ArrayList<ListChude>> listChude = new MutableLiveData<>();

    public LiveData<ArrayList<ListChude>> getListChude() {
        return listChude;
    }

    public void fetchListChude(int chudeId) {
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("CaunoiId");
        Query query = myRef.orderByChild("ChudeId").equalTo(chudeId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<ListChude> list = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot issue : snapshot.getChildren()) {
                        list.add(issue.getValue(ListChude.class));
                    }
                }
                listChude.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error
            }
        });
    }
}
