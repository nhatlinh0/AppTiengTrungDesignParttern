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

public class DanhSachHocSinhActivity extends AppCompatActivity {
    public static ArrayList<User> list;
    public static String[] listName = new String[] {};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachhocsinh_layout);

        list = new ArrayList<>();
        listName = new String[]{};
        ListView lvHocSinh = findViewById(R.id.lvHocSinh);
        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachHocSinhActivity.this, QuanLyActivity.class);
                startActivity(intent);
            }
        });

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    User user = (User) snap.getValue(User.class);
                    if(!user.isTeacher && !user.isAdmin) {
                        list.add(user);
                    }
                }

                for (int i = 0; i<list.size(); i++) {
                    listName = addElementToArray(listName, list.get(i).username);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DanhSachHocSinhActivity.this, android.R.layout.simple_list_item_1, listName);
                lvHocSinh.setAdapter(adapter);
                lvHocSinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(DanhSachHocSinhActivity.this, CRUDDiemActivity.class);
                        startActivity(intent);
                        CRUDDiemActivity.id = list.get(i).id;
                        CRUDDiemActivity.username = list.get(i).username;
                        CRUDDiemActivity.password = list.get(i).password;
                        CRUDDiemActivity.Diem = list.get(i).score;
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
