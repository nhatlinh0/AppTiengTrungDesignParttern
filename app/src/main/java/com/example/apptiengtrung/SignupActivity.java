package com.example.apptiengtrung;

//import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.List;

public class SignupActivity extends AppCompatActivity {
    public String username;
    public String password;
    public String passwordconfirm;
    public int count;
    public ListUser listUser = new ListUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_layout);

        count =1;
        listUser.init();
        EditText edtUsername = (EditText) findViewById(R.id.edtUsername);
        EditText edtPassword  = (EditText) findViewById(R.id.edtPassword);
        EditText edtPasswordConfirm = (EditText) findViewById(R.id.edtPasswordConfirm);
        Button btnDangKy = (Button) findViewById(R.id.btnDangKy);
        TextView tvError = (TextView) findViewById(R.id.tvError);

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    count++;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });


        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username = edtUsername.getText().toString();
                password = edtPassword.getText().toString();
                passwordconfirm = edtPasswordConfirm.getText().toString();

                if (!username.equals("") && !password.equals("")  && !passwordconfirm.equals("") && password.equals(passwordconfirm))
                {
                    User user = new User(count,username,password,0,false,false);
                    if(listUser.isExist(user.username))
                    {
                        ListUser.list.add(user);
                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("User");
                        databaseReference.child(count +"").setValue(user);
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignupActivity.this);
                        builder.setMessage("Bạn đã đăng ký thành công")
                                .setTitle("Thông báo")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                                        startActivity(intent);
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    } else {
                        tvError.setText("Tên đăng nhập đã tồn tại");
                    }

                } else if (!username.equals("") && !password.equals("")  && !passwordconfirm.equals("") && !password.equals(passwordconfirm)) {
                    tvError.setText("Mật khẩu xác nhận không khớp");
                }
                else {
                    tvError.setText("Vui lòng nhập đầy đủ thông tin");
                }
            }
        });


        TextView btnLogin = (TextView) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
