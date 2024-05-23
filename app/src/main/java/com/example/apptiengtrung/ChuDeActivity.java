package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;

public class ChuDeActivity extends AppCompatActivity {
    //    mvvm parttern
    //    observe
    private ChuDeViewModel viewModel;
    private ChuDeAdapter adapter;
    public static int[] abc =  new int[] {R.drawable.ic_heart, R.drawable.ic_drink, R.drawable.ic_animal, R.drawable.ic_family, R.drawable.ic_vacation, R.drawable.ic_mountain, R.drawable.ic_sport, R.drawable.ic_time, R.drawable.ic_fruit, R.drawable.ic_office};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chude_layout);

        GridView gvChuDe = findViewById(R.id.gvChuDe);
        ImageView ivBack = findViewById(R.id.ivBack);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        adapter = new ChuDeAdapter(this, new ArrayList<>());
        gvChuDe.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(ChuDeViewModel.class);
        viewModel.getChuDeList().observe(this, chuDes -> {
            adapter.updateData(chuDes);
        });
    }
}
