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

public class ThemTuVungViewModel extends AndroidViewModel {
    private final DatabaseReference databaseReference;
    private final MutableLiveData<Integer> count = new MutableLiveData<>();
    private final MutableLiveData<Boolean> addSuccess = new MutableLiveData<>();

    public ThemTuVungViewModel(@NonNull Application application) {
        super(application);
        databaseReference = FirebaseDatabase.getInstance().getReference("TuVung").child(TuVungActivity.ChuDe);
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

    public void addTuVung(String tieuDeTrung, String tieuDeViet, String phienAm) {
        Integer currentCount = count.getValue();
        if (currentCount != null) {
            TuVung tuVung = new TuVung(currentCount, tieuDeTrung, tieuDeViet, phienAm);
            databaseReference.child(currentCount.toString()).setValue(tuVung)
                    .addOnCompleteListener(task -> addSuccess.setValue(task.isSuccessful()));
        }
    }
}
