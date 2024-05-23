package com.example.apptiengtrung;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DoiMatKhauActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doimatkhau_layout);

        EditText edtMkHienTai = findViewById(R.id.edtMKHienTai);
        EditText edtMkMoi = findViewById(R.id.edtMkMoi);
        EditText edtMkMoi1 = findViewById(R.id.edtMkMoi1);
        Button btnDongY = findViewById(R.id.btnDongY);

        ImageView ivBack = (ImageView) findViewById(R.id.ivBack);
        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                User user = ListUser.findUser(LoginActivity.username);
                if(user.password.equals(edtMkHienTai.getText().toString()) && edtMkMoi.getText().toString().equals( edtMkMoi1.getText().toString()) && !edtMkMoi.getText().toString().equals("") && !edtMkMoi1.getText().toString().equals("") ) {
                    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
                    databaseReference.child("User").child(user.id+"").setValue(new User(user.id,user.username,edtMkMoi.getText().toString(),user.score,user.isTeacher,user.isAdmin));
                    AlertDialog.Builder builder = new AlertDialog.Builder(DoiMatKhauActivity.this);
                    builder.setMessage("Đổi mật khẩu thành công thành công")
                            .setTitle("Thông báo")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent intent = new Intent(DoiMatKhauActivity.this, OtherActivity.class);
                                    startActivity(intent);
                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(DoiMatKhauActivity.this);
                    builder.setMessage("Mật khẩu hoặc nhập lại mật khẩu không chính xác")
                            .setTitle("Thông báo")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });
    }
}
