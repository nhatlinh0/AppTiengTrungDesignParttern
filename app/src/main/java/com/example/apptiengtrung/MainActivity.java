package com.example.apptiengtrung;

//import android.support.v7.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout llTuVung = findViewById(R.id.llTuVung);
        LinearLayout llKyNangGiaoTiep = findViewById(R.id.llKyNangGiaoTiep);
        LinearLayout llHsk = findViewById(R.id.llThiHsk);
        LinearLayout llCauThongDung = findViewById(R.id.llCauThongDung);
        LinearLayout llHskQuiz = findViewById(R.id.llHskQuiz);
        LinearLayout llChuDePhoBien = findViewById(R.id.llChuDePhoBien);
        LinearLayout llQuanLy = findViewById(R.id.llQuanLy);
        LinearLayout llBoThuHanThu = findViewById(R.id.llBoThuHanThu);

        ImageView ivSettings = findViewById(R.id.ivSettings);

        if(ListUser.checkTeacher(LoginActivity.username) || ListUser.checkAdmin(LoginActivity.username)) {
            llQuanLy.setVisibility(View.VISIBLE);
        }

        llCauThongDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), IntroActivity.class);
                startActivity(intent);
            }
        });

        llHskQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainOneActivity.class);
                startActivity(intent);
            }
        });

        llQuanLy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuanLyActivity.class);
                startActivity(intent);
            }
        });

        llTuVung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChuDeActivity.class);
                startActivity(intent);
            }
        });

        llKyNangGiaoTiep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KyNangGiaoTiepActivity.class);
                startActivity(intent);
            }
        });

        llHsk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Hsk1listenActivity.class);
                startActivity(intent);
            }
        });

        llChuDePhoBien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChuDeActivity.class);
                startActivity(intent);
            }
        });

        llBoThuHanThu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), BoThuHanThuActivity.class);
                startActivity(intent);
            }
        });

        ivSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), OtherActivity.class);
                startActivity(intent);
            }
        });
    }
}