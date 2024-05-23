package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThemTaiKhoanActivity extends AppCompatActivity {
    int count;
    boolean check;
    boolean check1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themtaikhoan_layout);

        count = 1;
        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        EditText edtDiem = findViewById(R.id.edtDiem);
        CheckBox cbGiaoVien = findViewById(R.id.cbGiaoVien);
        CheckBox cbAdmin = findViewById(R.id.cbAdmin);
        ImageView ivBack = findViewById(R.id.ivBack);
        Button btnThem = findViewById(R.id.btnThem);

        cbGiaoVien.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Perform action based on checkbox state
                if (isChecked) {
                    check = true;
                } else {
                    check = false;
                }
            }
        });

        cbAdmin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Perform action based on checkbox state
                if (isChecked) {
                    check1 = true;
                } else {
                    check1 = false;
                }
            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ThemTaiKhoanActivity.this, DanhSachTaiKhoanActivity.class);
                startActivity(intent);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us = edtUsername.getText().toString();
                String pw = edtPassword.getText().toString();
                int score = Integer.parseInt(edtDiem.getText().toString());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("User").child(count+"").setValue(new User(count,us,pw,score,check,check1));
                Intent intent = new Intent(ThemTaiKhoanActivity.this, DanhSachTaiKhoanActivity.class);
                startActivity(intent);
            }
        });
    }
}
