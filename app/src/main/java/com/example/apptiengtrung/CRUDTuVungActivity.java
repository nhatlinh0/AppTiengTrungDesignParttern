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

import java.util.ArrayList;

public class CRUDTuVungActivity extends AppCompatActivity {
    public static int id;
    public static String tieuDeTrung;
    public static String tieuDeViet;
    public static String phienAm;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crudtuvung_layout);

        EditText edtTieuDeTrung = findViewById(R.id.edtTieuDeTrung);
        EditText edtTieuDeViet = findViewById(R.id.edtTieuDeViet);
        EditText edtPhienAm = findViewById(R.id.edtPhienAm);
        Button btnXoa = findViewById(R.id.btnXoa);
        Button btnSua = findViewById(R.id.btnSua);
        Button btnThem = findViewById(R.id.btnThem);
        ImageView ivBack = findViewById(R.id.ivBack);

        edtTieuDeTrung.setText(tieuDeTrung);
        edtPhienAm.setText(phienAm);
        edtTieuDeViet.setText(tieuDeViet);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CRUDTuVungActivity.this, ChuDeActivity.class);
                startActivity(intent);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CRUDTuVungActivity.this, ThemTuVungActivity.class);
                startActivity(intent);
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("TuVung").child(TuVungActivity.ChuDe).child(id+"").removeValue();
                Intent intent = new Intent(CRUDTuVungActivity.this, ChuDeActivity.class);
                startActivity(intent);
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("TuVung").child(TuVungActivity.ChuDe).child(id+"").setValue(new TuVung(id,edtTieuDeTrung.getText().toString(),edtTieuDeViet.getText().toString(),edtPhienAm.getText().toString()));
                Intent intent = new Intent(CRUDTuVungActivity.this, ChuDeActivity.class);
                startActivity(intent);
            }
        });

    }
}
