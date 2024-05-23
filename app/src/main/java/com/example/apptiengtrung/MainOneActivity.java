package com.example.apptiengtrung;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.example.apptiengtrung.databinding.ActivityMainOneBinding;

import java.util.ArrayList;
import java.util.List;

public class MainOneActivity extends AppCompatActivity {
    private ActivityMainOneBinding binding;
    private List<QuizModel> quizModelList;
    private QuizListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainOneBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        quizModelList = new ArrayList<>();
        getDataFromFirebase();
    }

    private void setupRecyclerView() {
        binding.progressBar.setVisibility(View.GONE);
        adapter = new QuizListAdapter(quizModelList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    private void getDataFromFirebase() {
        binding.progressBar.setVisibility(View.VISIBLE);
        FirebaseDatabase.getInstance().getReference("test")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                QuizModel quizModel = snapshot.getValue(QuizModel.class);
                                if (quizModel != null) {
                                    quizModelList.add(quizModel);
                                }
                            }
                            setupRecyclerView();
                        }
                        binding.progressBar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        binding.progressBar.setVisibility(View.GONE);
                        // Xử lý nếu có lỗi xảy ra khi truy xuất dữ liệu từ Firebase
                    }
                });
    }
}
