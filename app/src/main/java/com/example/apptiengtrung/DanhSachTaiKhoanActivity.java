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

public class DanhSachTaiKhoanActivity extends AppCompatActivity {

//    mvvm parttern

    private ListView lvTaiKhoan;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danhsachtaikhoan_layout);

        lvTaiKhoan = findViewById(R.id.lvTaiKhoan);
        ImageView ivBack = findViewById(R.id.ivBack);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DanhSachTaiKhoanActivity.this, QuanLyActivity.class);
                startActivity(intent);
            }
        });

        observeUserList();
    }

    private void observeUserList() {
        userViewModel.getUserList().observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                if (users != null) {
                    setupListView(users);
                }
            }
        });
    }

    private void setupListView(ArrayList<User> userList) {
        ArrayList<String> listName = new ArrayList<>();
        for (User user : userList) {
            if (!user.username.equals(LoginActivity.username)) {
                listName.add(user.username);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listName);
        lvTaiKhoan.setAdapter(adapter);
        lvTaiKhoan.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DanhSachTaiKhoanActivity.this, CRUDTaiKhoanActivity.class);
                startActivity(intent);
                CRUDTaiKhoanActivity.id = userList.get(i).id;
                CRUDTaiKhoanActivity.username = userList.get(i).username;
                CRUDTaiKhoanActivity.password = userList.get(i).password;
                CRUDTaiKhoanActivity.Diem = userList.get(i).score;
                CRUDTaiKhoanActivity.isTeacher = userList.get(i).isTeacher;
                CRUDTaiKhoanActivity.isAdmin = userList.get(i).isAdmin;
            }
        });
    }
}
