package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ThemTuVungActivity extends AppCompatActivity {
    //    mvvm parttern
    //    observe
    private ThemTuVungViewModel viewModel;
    private EditText edtTieuDeTrung;
    private EditText edtTieuDeViet;
    private EditText edtPhienAm;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themtuvung_layout);

        viewModel = new ViewModelProvider(this).get(ThemTuVungViewModel.class);

        edtTieuDeTrung = findViewById(R.id.edtTieuDeTrung);
        edtTieuDeViet = findViewById(R.id.edtTieuDeViet);
        edtPhienAm = findViewById(R.id.edtPhienAm);
        Button btnThem = findViewById(R.id.btnThem);
        ImageView ivBack = findViewById(R.id.ivBack);

        viewModel.getCount().observe(this, newCount -> count = newCount);

        viewModel.getAddSuccess().observe(this, success -> {
            if (success) {
                Intent intent = new Intent(ThemTuVungActivity.this, ChuDeActivity.class);
                startActivity(intent);
            }
        });

        ivBack.setOnClickListener(view -> {
            Intent intent = new Intent(ThemTuVungActivity.this, ChuDeActivity.class);
            startActivity(intent);
        });

        btnThem.setOnClickListener(view -> {
            String tieuDeTrung = edtTieuDeTrung.getText().toString();
            String tieuDeViet = edtTieuDeViet.getText().toString();
            String phienAm = edtPhienAm.getText().toString();
            viewModel.addTuVung(tieuDeTrung, tieuDeViet, phienAm);
        });
    }
}
