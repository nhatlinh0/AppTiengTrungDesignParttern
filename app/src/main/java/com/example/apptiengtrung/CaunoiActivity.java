package com.example.apptiengtrung;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.apptiengtrung.ChuDeCauNoiAdapter;
import com.example.apptiengtrung.ChuDeCauNoi;
import com.example.apptiengtrung.databinding.ActivityMain1Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CaunoiActivity extends BaseActivity {
    ActivityMain1Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMain1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        
        innitChude();
    }

    private void innitChude() {
        DatabaseReference myRef=database.getReference("test1");
        binding.ProgressBar3.setVisibility(View.VISIBLE);
        ArrayList<ChuDeCauNoi> list=new ArrayList<>();

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(ChuDeCauNoi.class));
                    }
                    if(list.size()>0){
                        binding.chudelistView.setLayoutManager(new GridLayoutManager(CaunoiActivity.this, 3));
                        binding.chudelistView.setAdapter(new ChuDeCauNoiAdapter(list));
                    }
                    binding.ProgressBar3.setVisibility(View.GONE);
                    binding.imageView.setOnClickListener(v -> finish());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}