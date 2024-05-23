package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ThemTuVungActivity extends AppCompatActivity {
    int count;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.themtuvung_layout);
        count = 1;
        EditText edtTieuDeTrung = findViewById(R.id.edtTieuDeTrung);
        EditText edtTieuDeViet = findViewById(R.id.edtTieuDeViet);
        EditText edtPhienAm = findViewById(R.id.edtPhienAm);
        Button btnThem = findViewById(R.id.btnThem);
        ImageView ivBack = findViewById(R.id.ivBack);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("TuVung").child(TuVungActivity.ChuDe).addValueEventListener(new ValueEventListener() {
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
                Intent intent = new Intent(ThemTuVungActivity.this, ChuDeActivity.class);
                startActivity(intent);
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("TuVung").child(TuVungActivity.ChuDe).child(count+"").setValue(new TuVung(count,edtTieuDeTrung.getText().toString(),edtTieuDeViet.getText().toString(),edtPhienAm.getText().toString()));
                Intent intent = new Intent(ThemTuVungActivity.this, ChuDeActivity.class);
                startActivity(intent);
            }
        });
    }
}
