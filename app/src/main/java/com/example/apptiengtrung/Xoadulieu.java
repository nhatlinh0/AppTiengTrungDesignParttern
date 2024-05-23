package com.example.apptiengtrung;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Xoadulieu extends AppCompatActivity {

    public Spinner spinnerChude;
    public Spinner spinnerCaunoi; // Thêm Spinner cho câu nói
    public Button btnDelete;
    public ArrayAdapter<ChudeItem> spinnerAdapter;
    public ArrayAdapter<String> caunoiAdapter; // Adapter cho Spinner câu nói
    public List<ChudeItem> chudeItems = new ArrayList<>();
    public List<String> caunoiList = new ArrayList<>(); // Danh sách các câu nói

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.xoadulieu);

        spinnerChude = findViewById(R.id.spinnerChude);
        spinnerCaunoi = findViewById(R.id.spinnerCaunoi); // Ánh xạ Spinner câu nói
        btnDelete = findViewById(R.id.btnDelete);

        spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        spinnerChude.setAdapter(spinnerAdapter);

        caunoiAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item);
        spinnerCaunoi.setAdapter(caunoiAdapter); // Set Adapter cho Spinner câu nói

        Query query = FirebaseDatabase.getInstance().getReference("test1");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Long chudeId = snapshot.child("Id").getValue(Long.class);
                    String chudeName = snapshot.child("Name").getValue(String.class);
                    ChudeItem chudeItem = new ChudeItem(String.valueOf(chudeId), chudeName);
                    chudeItems.add(chudeItem);
                }
                spinnerAdapter.addAll(chudeItems);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(Xoadulieu.this, "Không thể đọc dữ liệu từ Firebase", Toast.LENGTH_SHORT).show();
            }
        });

        spinnerChude.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ChudeItem selectedChude = (ChudeItem) parent.getItemAtPosition(position);
                String selectedChudeId = selectedChude.getId();
                loadCaunoi(selectedChudeId); // Load danh sách câu nói khi chọn chủ đề
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedPosition = spinnerCaunoi.getSelectedItemPosition(); // Lấy vị trí câu nói được chọn
                if (selectedPosition != AdapterView.INVALID_POSITION) {
                    String selectedCaunoi = caunoiList.get(selectedPosition); // Lấy câu nói được chọn
                    deleteCaunoi(selectedCaunoi); // Xoá câu nói từ Firebase
                } else {
                    Toast.makeText(Xoadulieu.this, "Vui lòng chọn câu để xoá", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void loadCaunoi(String chudeId) {
        // Truy vấn vào Firebase để lấy danh sách các câu nói thuộc chủ đề được chọn
        FirebaseDatabase.getInstance().getReference("CaunoiId")
                .orderByChild("ChudeId")
                .equalTo(Long.parseLong(chudeId))
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        caunoiList.clear(); // Xóa danh sách câu nói cũ
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String caunoi = snapshot.child("Caunoi").getValue(String.class);
                            caunoiList.add(caunoi); // Thêm câu nói vào danh sách
                        }
                        caunoiAdapter.clear();
                        caunoiAdapter.addAll(caunoiList); // Cập nhật danh sách câu nói cho Spinner câu nói
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Xoadulieu.this, "Không thể đọc dữ liệu từ Firebase", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void deleteCaunoi(String caunoiToDelete) {
        // Xoá câu nói từ Firebase
        FirebaseDatabase.getInstance().getReference("CaunoiId")
                .orderByChild("Caunoi")
                .equalTo(caunoiToDelete)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            snapshot.getRef().removeValue();
                        }
                        Toast.makeText(Xoadulieu.this, "Đã xoá câu nói", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Toast.makeText(Xoadulieu.this, "Không thể xoá câu nói từ Firebase", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
