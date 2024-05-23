package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MauCauGiaoTiepActivity extends AppCompatActivity {
    public static String title;
    public static String[] arr;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maucaugiaotiep_layout);

        TextView tvTitle = findViewById(R.id.tvTitle);
        tvTitle.setText(title);

        ListView lvMauCau = (ListView) findViewById(R.id.lvMauCau);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arr);
        lvMauCau.setAdapter(adapter);

        ImageView ivBack = findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KyNangGiaoTiepActivity.class);
                startActivity(intent);
            }
        });
    }
}
