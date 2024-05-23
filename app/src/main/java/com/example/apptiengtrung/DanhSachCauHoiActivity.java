package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class DanhSachCauHoiActivity extends AppCompatActivity {
    public static ArrayList<SapXep> list;
    public static String[] listName = new String[] {};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachcauhoi_layout);

        list = new ArrayList<>();
        listName = new String[]{};
        ListView lvCauHoi = findViewById(R.id.lvCauhoi);
        ImageView ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachCauHoiActivity.this, QuanLyActivity.class);
                startActivity(intent);
            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("SapXep").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    SapXep sx = (SapXep) snap.getValue(SapXep.class);
                    list.add(sx);
                }

                for (int i = 0; i<list.size(); i++) {
                    listName = addElementToArray(listName, list.get(i).Trung);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DanhSachCauHoiActivity.this, android.R.layout.simple_list_item_1, listName);
                lvCauHoi.setAdapter(adapter);
                lvCauHoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(DanhSachCauHoiActivity.this, CRUDSapXepActivity.class);
                        startActivity(intent);
                        CRUDSapXepActivity.id = list.get(i).id;
                        CRUDSapXepActivity.tieuDeTrung = list.get(i).Trung;
                        CRUDSapXepActivity.tieuDeViet = list.get(i).Viet;
                        CRUDSapXepActivity.phienAm = list.get(i).PhienAm;
                    }
                });
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public String[] addElementToArray(String[] array, String newElement) {
        String[] newArray = Arrays.copyOf(array, array.length + 1);
        newArray[array.length] = newElement;
        return newArray;
    }
}
