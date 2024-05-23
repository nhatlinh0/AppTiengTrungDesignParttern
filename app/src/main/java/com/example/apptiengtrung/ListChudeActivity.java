package com.example.apptiengtrung;

import android.os.Bundle;
import android.view.View;

//import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.apptiengtrung.databinding.ActivityListChudeBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListChudeActivity extends BaseActivity {
    ActivityListChudeBinding binding;
    private int chudeId;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityListChudeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentUltra666();
        innitList();

    }

    private void innitList() {
        DatabaseReference myRef=database.getReference("CaunoiId");
        binding.progressBar.setVisibility(View.VISIBLE);
        ArrayList<ListChude>list=new ArrayList<>();
        Query query=myRef.orderByChild("ChudeId").equalTo(chudeId);

        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren()){
                        list.add(issue.getValue(ListChude.class));
                    }
                    if(list.size()>0){
                        binding.chudelistView.setLayoutManager(new GridLayoutManager(ListChudeActivity.this, LinearLayoutManager.VERTICAL));
                        binding.chudelistView.setAdapter(new ListChudeAdapter(list));
                    }
                    binding.progressBar.setVisibility(View.GONE);
                }
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getIntentUltra666() {
        chudeId=getIntent().getIntExtra("ChudeId",1);
        categoryName=getIntent().getStringExtra("CategoryName");

        binding.titleTxt.setText(categoryName);
        binding.Backbtn.setOnClickListener(v -> finish());
    }
}