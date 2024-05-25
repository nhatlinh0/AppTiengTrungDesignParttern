package com.example.apptiengtrung;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class ThemTaiKhoanActivity extends AppCompatActivity {
    //    mvvm parttern
    //    observe
    private ThemTaiKhoanViewModel viewModel;
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtDiem;
    private boolean check;
    private boolean check1;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themtaikhoan_layout);

        viewModel = new ViewModelProvider(this).get(ThemTaiKhoanViewModel.class);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtDiem = findViewById(R.id.edtDiem);
        CheckBox cbGiaoVien = findViewById(R.id.cbGiaoVien);
        CheckBox cbAdmin = findViewById(R.id.cbAdmin);
        ImageView ivBack = findViewById(R.id.ivBack);
        Button btnThem = findViewById(R.id.btnThem);

        cbGiaoVien.setOnCheckedChangeListener((buttonView, isChecked) -> check = isChecked);

        cbAdmin.setOnCheckedChangeListener((buttonView, isChecked) -> check1 = isChecked);

        viewModel.getCount().observe(this, newCount -> count = newCount);

        viewModel.getAddSuccess().observe(this, success -> {
            if (success) {
                Intent intent = new Intent(ThemTaiKhoanActivity.this, DanhSachTaiKhoanActivity.class);
                startActivity(intent);
            }
        });

        ivBack.setOnClickListener(view -> {
            Intent intent = new Intent(ThemTaiKhoanActivity.this, DanhSachTaiKhoanActivity.class);
            startActivity(intent);
        });

        btnThem.setOnClickListener(view -> {
            String us = edtUsername.getText().toString();
            String pw = edtPassword.getText().toString();
            int score = Integer.parseInt(edtDiem.getText().toString());
            viewModel.addUser(us, pw, score, check, check1);
        });
    }
}
