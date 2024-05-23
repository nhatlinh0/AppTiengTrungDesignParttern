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

public class ChuDeViewModel extends ViewModel {
    private final MutableLiveData<List<ChuDe>> chuDeList;

    public ChuDeViewModel() {
        chuDeList = new MutableLiveData<>();
        loadChuDeData();
    }

    public LiveData<List<ChuDe>> getChuDeList() {
        return chuDeList;
    }

    private void loadChuDeData() {
        FirebaseUtils.getDatabaseReference().child("ChuDe")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        List<ChuDe> list = new ArrayList<>();
                        for (DataSnapshot snap : snapshot.getChildren()) {
                            ChuDe cd = snap.getValue(ChuDe.class);
                            list.add(cd);
                        }
                        chuDeList.setValue(list);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        // Xử lý lỗi nếu cần
                    }
                });
    }
}
