package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CRUDDiemActivity extends AppCompatActivity {
    public static int id;
    public static String username;
    public static String password;
    public static int Diem;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cruddiem_layout);

        EditText edtDiem = findViewById(R.id.edtDiem);
        Button btnCapNhat = findViewById(R.id.btnCapNhat);
        ImageView ivBack = findViewById(R.id.ivBack);

        edtDiem.setText(Diem+"");

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CRUDDiemActivity.this, DanhSachHocSinhActivity.class);
                startActivity(intent);
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int score = Integer.parseInt(edtDiem.getText().toString());
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("User").child(id+"").setValue(new User(id,username,password,score,false,false));
                Intent intent = new Intent(CRUDDiemActivity.this, DanhSachHocSinhActivity.class);
                startActivity(intent);
            }
        });
    }
}
