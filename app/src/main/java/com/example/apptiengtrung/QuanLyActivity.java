package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class QuanLyActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quanly_layout);

        ImageView ivBack = findViewById(R.id.ivBack);
        LinearLayout llQuanLyTuVung = findViewById(R.id.llQuanLyTuVung);
        LinearLayout llQuanLyCauHoi = findViewById(R.id.llQuanLyCauHoi);
        LinearLayout llQuanLyCauThongDung = findViewById(R.id.llQuanLyCauThongDung);
        LinearLayout llXoaCauThongDung = findViewById(R.id.llXoaCauThongDung);
        LinearLayout llQuanLyDiem = findViewById(R.id.llQuanLyDiem);
        LinearLayout llQuanLyTaiKhoan = findViewById(R.id.llQuanLyTaiKhoan);

        if(ListUser.checkAdmin(LoginActivity.username)) {
            llQuanLyTaiKhoan.setVisibility(View.VISIBLE);
        }

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        llXoaCauThongDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Xoadulieu.class);
                startActivity(intent);
            }
        });

        llQuanLyCauThongDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Nhapdulieu.class);
                startActivity(intent);
            }
        });

        llQuanLyTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChuDeActivity.class);
                startActivity(intent);
            }
        });
        llQuanLyCauHoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DanhSachCauHoiActivity.class);
                startActivity(intent);
            }
        });
        llQuanLyDiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DanhSachHocSinhActivity.class);
                startActivity(intent);
            }
        });
        llQuanLyTaiKhoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DanhSachTaiKhoanActivity.class);
                startActivity(intent);
            }
        });
    }
}
