package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.apptiengtrung.databinding.BothuhanthuLayoutBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class BoThuHanThuActivity extends AppCompatActivity {

    private BothuhanthuLayoutBinding binding;
    private BoThuHanThuViewModel viewModel;
    private BoThuHanThuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = BothuhanthuLayoutBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new ViewModelProvider(this).get(BoThuHanThuViewModel.class);

        adapter = new BoThuHanThuAdapter(this, new ArrayList<>());
        binding.gvBoThuHanThu.setAdapter(adapter);

        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BoThuHanThuActivity.this, MainActivity.class));
            }
        });

        viewModel.getBoThuHanThuList().observe(this, boThuHanThus -> {
            adapter.updateData(boThuHanThus);
        });
    }
}
