package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TuVungActivity extends AppCompatActivity {
    public static ArrayList<TuVung> list;
    public static String ChuDe;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tuvung_layout);

        list = new ArrayList<>();
        GridView gvTuVung = findViewById(R.id.gvTuVung);
        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        TextView tvTuVung = (TextView) findViewById(R.id.tvTuVung);

        tvTuVung.setText(ChuDe);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChuDeActivity.class);
                startActivity(intent);
            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("TuVung").child(ChuDe).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    TuVung tv = snap.getValue(TuVung.class);
                    list.add(tv);
                }
                TuVungAdapter adapter = new TuVungAdapter(TuVungActivity.this, list);
                gvTuVung.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}
