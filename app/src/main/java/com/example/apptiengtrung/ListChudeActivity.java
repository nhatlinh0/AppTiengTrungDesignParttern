package com.example.apptiengtrung;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.apptiengtrung.databinding.ActivityListChudeBinding;

import java.util.ArrayList;

public class ListChudeActivity extends BaseActivity {

//    mvvm parttern

    private ActivityListChudeBinding binding;
    private ListChudeViewModel viewModel;
    private int chudeId;
    private String categoryName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityListChudeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getIntentUltra666();
        initViewModel();
        setupObservers();
        viewModel.fetchListChude(chudeId);
    }

    private void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ListChudeViewModel.class);
    }

    private void setupObservers() {
        viewModel.getListChude().observe(this, new Observer<ArrayList<ListChude>>() {
            @Override
            public void onChanged(ArrayList<ListChude> listChudes) {
                if (listChudes != null) {
                    setupRecyclerView(listChudes);
                }
                binding.progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setupRecyclerView(ArrayList<ListChude> list) {
        binding.chudelistView.setLayoutManager(new GridLayoutManager(this, LinearLayoutManager.VERTICAL));
        binding.chudelistView.setAdapter(new ListChudeAdapter(list));
    }

    private void getIntentUltra666() {
        chudeId = getIntent().getIntExtra("ChudeId", 1);
        categoryName = getIntent().getStringExtra("CategoryName");

        binding.titleTxt.setText(categoryName);
        binding.Backbtn.setOnClickListener(v -> finish());
    }
}
