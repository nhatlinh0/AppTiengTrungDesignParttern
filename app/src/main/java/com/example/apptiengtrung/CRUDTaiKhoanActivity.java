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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CRUDTaiKhoanActivity extends AppCompatActivity {
    public static int id;
    public static String username;
    public static String password;
    public static boolean isTeacher;
    public static boolean isAdmin;
    public static int Diem;
    boolean check;
    boolean check1;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crudtaikhoan_layout);

        EditText edtUsername = findViewById(R.id.edtUsername);
        EditText edtPassword = findViewById(R.id.edtPassword);
        EditText edtDiem = findViewById(R.id.edtDiem);
        CheckBox cbGiaoVien = findViewById(R.id.cbGiaoVien);
        CheckBox cbAdmin = findViewById(R.id.cbAdmin);
        ImageView ivBack = findViewById(R.id.ivBack);
        Button btnXoa = findViewById(R.id.btnXoa);
        Button btnSua = findViewById(R.id.btnSua);
        Button btnThem = findViewById(R.id.btnThem);

        edtUsername.setText(username);
        edtPassword.setText(password);
        edtDiem.setText(Diem+"");
        cbGiaoVien.setChecked(isTeacher);
        cbAdmin.setChecked(isAdmin);

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

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CRUDTaiKhoanActivity.this, DanhSachTaiKhoanActivity.class);
                startActivity(intent);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("User").child(id+"").removeValue();
                Intent intent = new Intent(CRUDTaiKhoanActivity.this, DanhSachTaiKhoanActivity.class);
                startActivity(intent);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us = edtUsername.getText().toString();
                String pw = edtPassword.getText().toString();
                int score = Integer.parseInt(edtDiem.getText().toString());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("User").child(id+"").setValue(new User(id,us,pw,score,check,check1));
                Intent intent = new Intent(CRUDTaiKhoanActivity.this, DanhSachTaiKhoanActivity.class);
                startActivity(intent);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CRUDTaiKhoanActivity.this, ThemTaiKhoanActivity.class);
                startActivity(intent);
            }
        });
    }
}
