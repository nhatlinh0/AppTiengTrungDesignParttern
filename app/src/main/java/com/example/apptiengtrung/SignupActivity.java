package com.example.apptiengtrung;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class SignupActivity extends AppCompatActivity {
//    mvvm parttern
//    observe

    private SignupViewModel viewModel;
    private EditText edtUsername;
    private EditText edtPassword;
    private EditText edtPasswordConfirm;
    private TextView tvError;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        viewModel = new ViewModelProvider(this).get(SignupViewModel.class);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        edtPasswordConfirm = findViewById(R.id.edtPasswordConfirm);
        Button btnDangKy = findViewById(R.id.btnDangKy);
        tvError = findViewById(R.id.tvError);

        btnDangKy.setOnClickListener(view -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            String passwordConfirm = edtPasswordConfirm.getText().toString();
            viewModel.registerUser(username, password, passwordConfirm);
        });

        viewModel.getErrorMessage().observe(this, error -> tvError.setText(error));

        viewModel.getRegistrationSuccess().observe(this, success -> {
            if (success) {
                new AlertDialog.Builder(SignupActivity.this)
                        .setMessage("Bạn đã đăng ký thành công")
                        .setTitle("Thông báo")
                        .setPositiveButton("OK", (dialog, id) -> {
                            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                            startActivity(intent);
                        })
                        .show();
            }
        });

        TextView btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(view -> {
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}
