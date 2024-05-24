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

import java.util.ArrayList;

public class TuVungViewModel extends AndroidViewModel {
    private final DatabaseReference databaseReference;
    private final MutableLiveData<ArrayList<TuVung>> tuVungList = new MutableLiveData<>();

    public TuVungViewModel(@NonNull Application application) {
        super(application);
        databaseReference = FirebaseDatabase.getInstance().getReference("TuVung").child(TuVungActivity.ChuDe);
        fetchTuVung();
    }

    private void fetchTuVung() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<TuVung> list = new ArrayList<>();
                for (DataSnapshot snap : snapshot.getChildren()) {
                    TuVung tuVung = snap.getValue(TuVung.class);
                    if (tuVung != null) {
                        list.add(tuVung);
                    }
                }
                tuVungList.setValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle error if needed
            }
        });
    }

    public LiveData<ArrayList<TuVung>> getTuVungList() {
        return tuVungList;
    }
}
