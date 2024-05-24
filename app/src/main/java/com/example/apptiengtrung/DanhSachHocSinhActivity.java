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

public class DanhSachHocSinhActivity extends AppCompatActivity {
    //    mvvm parttern

    private ListView lvHocSinh;
    private ImageView ivBack;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachhocsinh_layout);

        lvHocSinh = findViewById(R.id.lvHocSinh);
        ivBack = findViewById(R.id.ivBack);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachHocSinhActivity.this, QuanLyActivity.class);
                startActivity(intent);
            }
        });

        observeUserList();
    }

    private void observeUserList() {
        userViewModel.getUserList1().observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                if (users != null) {
                    setupListView(users);
                }
            }
        });
    }

    private void setupListView(ArrayList<User> userList) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getUserNames(userList));
        lvHocSinh.setAdapter(adapter);
        lvHocSinh.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DanhSachHocSinhActivity.this, CRUDDiemActivity.class);
                startActivity(intent);
                CRUDDiemActivity.id = userList.get(i).id;
                CRUDDiemActivity.username = userList.get(i).username;
                CRUDDiemActivity.password = userList.get(i).password;
                CRUDDiemActivity.Diem = userList.get(i).score;
            }
        });
    }

    private ArrayList<String> getUserNames(ArrayList<User> users) {
        ArrayList<String> userNames = new ArrayList<>();
        for (User user : users) {
            userNames.add(user.username);
        }
        return userNames;
    }
}
