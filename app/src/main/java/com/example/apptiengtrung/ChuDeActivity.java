package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
//import android.support.v7.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChuDeActivity extends AppCompatActivity {
    public static ArrayList<ChuDe> list;
    public static int[] abc =  new int[] {R.drawable.ic_heart, R.drawable.ic_drink, R.drawable.ic_animal, R.drawable.ic_family, R.drawable.ic_vacation, R.drawable.ic_mountain, R.drawable.ic_sport, R.drawable.ic_time, R.drawable.ic_fruit, R.drawable.ic_office};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chude_layout);
        // tim gia tri int cua hinh anh icon
        list = new ArrayList<>();
        GridView gvChuDe = (GridView) findViewById(R.id.gvChuDe);
        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("ChuDe").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    ChuDe cd = snap.getValue(ChuDe.class);
                    list.add(cd);

                }
                ChuDeAdapter adapter = new ChuDeAdapter(ChuDeActivity.this, list);
                gvChuDe.setAdapter(adapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        list.add(new ChuDe(1, "感觉", "Cảm xúc", R.drawable.ic_heart));
//        list.add(new ChuDe(2, "饮料", "Đồ uống", R.drawable.ic_drink));
//        list.add(new ChuDe(3, "动物", "Động vật", R.drawable.ic_animal));
//        list.add(new ChuDe(4, "家庭", "Gia đình", R.drawable.ic_family));
//        list.add(new ChuDe(5, "娱乐", "Giải trí", R.drawable.ic_vacation));
//        list.add(new ChuDe(6, "风景", "Phong cảnh", R.drawable.ic_mountain));
//        list.add(new ChuDe(7, "运动", "Thể thao", R.drawable.ic_sport));
//        list.add(new ChuDe(8, "时间", "Thời gian", R.drawable.ic_time));
//        list.add(new ChuDe(9, "水果", "Trái cây", R.drawable.ic_fruit));
//        list.add(new ChuDe(10, "办公室", "Văn phòng", R.drawable.ic_office));
    }
}
