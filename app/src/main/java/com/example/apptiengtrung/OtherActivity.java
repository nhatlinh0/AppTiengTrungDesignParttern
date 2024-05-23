package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class OtherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.other_layout);

        LinearLayout btnDangXuat = (LinearLayout) findViewById(R.id.btnDangXuat);
        LinearLayout llDoiMatKhau = (LinearLayout) findViewById(R.id.llDoiMatKhau);

        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        llDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DoiMatKhauActivity.class);
                startActivity(intent);
            }
        });
    }
}
