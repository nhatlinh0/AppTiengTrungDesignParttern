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
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;

public class DanhSachCauHoiActivity extends AppCompatActivity {

//    mvvm parttern

    private ListView lvCauHoi;
    private SapXepViewModel sapXepViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachcauhoi_layout);

        lvCauHoi = findViewById(R.id.lvCauhoi);
        ImageView ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachCauHoiActivity.this, QuanLyActivity.class);
                startActivity(intent);
            }
        });

        sapXepViewModel = new ViewModelProvider(this).get(SapXepViewModel.class);
        sapXepViewModel.getSapXepListLiveData().observe(this, new Observer<ArrayList<SapXep>>() {
            @Override
            public void onChanged(ArrayList<SapXep> sapXeps) {
                ArrayList<String> listName = new ArrayList<>();
                for (SapXep sapXep : sapXeps) {
                    listName.add(sapXep.Trung);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DanhSachCauHoiActivity.this, android.R.layout.simple_list_item_1, listName);
                lvCauHoi.setAdapter(adapter);
                lvCauHoi.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(DanhSachCauHoiActivity.this, CRUDSapXepActivity.class);
                        startActivity(intent);
                        CRUDSapXepActivity.id = sapXeps.get(i).id;
                        CRUDSapXepActivity.tieuDeTrung = sapXeps.get(i).Trung;
                        CRUDSapXepActivity.tieuDeViet = sapXeps.get(i).Viet;
                        CRUDSapXepActivity.phienAm = sapXeps.get(i).PhienAm;
                    }
                });
            }
        });
    }
}


