package com.example.apptiengtrung;


import android.content.Intent;
import android.os.Bundle;

import com.example.apptiengtrung.databinding.ActivityIntroBinding;

public class IntroActivity extends BaseActivity {
    ActivityIntroBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= ActivityIntroBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.gobtn.setOnClickListener(view -> startActivity(new Intent(IntroActivity.this, CaunoiActivity.class)));
    }
}