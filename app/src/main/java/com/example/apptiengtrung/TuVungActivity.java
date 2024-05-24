package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class TuVungActivity extends AppCompatActivity {
    //    mvvm parttern
    //    observe
    public static String ChuDe;
    private TuVungViewModel viewModel;
    private TuVungAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuvung_layout);

        GridView gvTuVung = findViewById(R.id.gvTuVung);
        ImageView ivBack = findViewById(R.id.ivBack);
        TextView tvTuVung = findViewById(R.id.tvTuVung);

        tvTuVung.setText(ChuDe);

        ivBack.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), ChuDeActivity.class);
            startActivity(intent);
        });

        viewModel = new ViewModelProvider(this).get(TuVungViewModel.class);
        adapter = new TuVungAdapter(this, new ArrayList<>());
        gvTuVung.setAdapter(adapter);

        viewModel.getTuVungList().observe(this, tuVungs -> {
            adapter.setTuVungList(tuVungs);
        });
    }
}

