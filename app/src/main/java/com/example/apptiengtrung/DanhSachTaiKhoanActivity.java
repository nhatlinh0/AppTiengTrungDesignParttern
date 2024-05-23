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

public class DanhSachTaiKhoanActivity extends AppCompatActivity {
    public static ArrayList<User> list;
    public static String[] listName = new String[] {};
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachtaikhoan_layout);

        list = new ArrayList<>();
        listName = new String[]{};
        ListView lvTaiKhoan = findViewById(R.id.lvTaiKhoan);
        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachTaiKhoanActivity.this, QuanLyActivity.class);
                startActivity(intent);
            }
        });
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    User user = (User) snap.getValue(User.class);
                    if(!user.username.equals(LoginActivity.username)) {
                        list.add(user);
                    }
                }

                for (int i = 0; i<list.size(); i++) {
                    listName = addElementToArray(listName, list.get(i).username);
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<>(DanhSachTaiKhoanActivity.this, android.R.layout.simple_list_item_1, listName);
                lvTaiKhoan.setAdapter(adapter);
                lvTaiKhoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        Intent intent = new Intent(DanhSachTaiKhoanActivity.this, CRUDTaiKhoanActivity.class);
                        startActivity(intent);
                        CRUDTaiKhoanActivity.id = list.get(i).id;
                        CRUDTaiKhoanActivity.username = list.get(i).username;
                        CRUDTaiKhoanActivity.password = list.get(i).password;
                        CRUDTaiKhoanActivity.Diem = list.get(i).score;
                        CRUDTaiKhoanActivity.isTeacher = list.get(i).isTeacher;
                        CRUDTaiKhoanActivity.isAdmin = list.get(i).isAdmin;
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
