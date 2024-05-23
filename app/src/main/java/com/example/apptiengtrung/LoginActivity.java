package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {
    public static String username;
    public String password;
    public boolean check = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);

        Button btnDangNhap = (Button) findViewById(R.id.btnDangNhap);
        Button btnDangKy = (Button) findViewById(R.id.btnDangKy);
        EditText edtUsername = (EditText) findViewById(R.id.edtUsername);
        EditText edtPassword = (EditText) findViewById(R.id.edtPassword);
        TextView tvError = (TextView) findViewById(R.id.tvError);

        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                databaseReference.child("User").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (!ResultActivity.isUpdating) {
                            for(DataSnapshot snap : snapshot.getChildren()) {
                                User user = (User) snap.getValue(User.class);
                                if(user.username.equals(username) && user.password.equals(password)) {
                                    ListUser.init();
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                    check = true;
                                }
                            }
                            if(check == false) {
                                tvError.setText("Tên đăng nhập hoặc mật khẩu không chính xác");
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }

}
