package com.example.apptiengtrung;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.apptiengtrung.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Nhapdulieu extends AppCompatActivity {
    private EditText editTextChudeId, editTextPhienAm, editTextCauNoi, editTextDichThuat ;
    private EditText editTextChude, editTextphienam, editTexttrung, editTextviet;
    private Button buttonAddSentence, buttonAddWord;

    private DatabaseReference cauNoiRef, tuVungRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhapdulieu);

        editTextChudeId = findViewById(R.id.editTextChudeId);
        editTextPhienAm = findViewById(R.id.editTextPhienAm);
        editTextCauNoi = findViewById(R.id.editTextCauNoi);
        editTextDichThuat = findViewById(R.id.editTextDichThuat);

        buttonAddSentence = findViewById(R.id.buttonAddSentence);


        cauNoiRef = FirebaseDatabase.getInstance().getReference().child("CaunoiId");
        tuVungRef = FirebaseDatabase.getInstance().getReference().child("TuVung");

        buttonAddSentence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCauNoiToFirebase();
            }
        });

        buttonAddWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTuVungToFirebase();
            }
        });
    }

    private void addCauNoiToFirebase() {
        String caunoi = editTextCauNoi.getText().toString();
        int chudeId = Integer.parseInt(editTextChudeId.getText().toString());
        String dichthuat = editTextDichThuat.getText().toString();
        String phienam = editTextPhienAm.getText().toString();

        DatabaseReference newDataRef = cauNoiRef.push(); // Tạo một key tự động
        String key = newDataRef.getKey(); // Lấy key tự động được tạo

        // Sử dụng key dạng số và tăng dần theo trong Firebase
        long timeStamp = System.currentTimeMillis();
        newDataRef = cauNoiRef.child(String.valueOf(timeStamp));
        newDataRef.child("Caunoi").setValue(caunoi);
        newDataRef.child("ChudeId").setValue(chudeId);
        newDataRef.child("Dichthuat").setValue(dichthuat);
        newDataRef.child("Phienam").setValue(phienam)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Nhapdulieu.this, "Dữ liệu câu nói đã được thêm vào Firebase!", Toast.LENGTH_SHORT).show();
                        // Xóa dữ liệu sau khi đã thêm thành công (nếu cần)
                        editTextChudeId.setText("");
                        editTextPhienAm.setText("");
                        editTextCauNoi.setText("");
                        editTextDichThuat.setText("");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Nhapdulieu.this, "Không thể thêm dữ liệu câu nói vào Firebase: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void addTuVungToFirebase() {
        String chude = editTextChude.getText().toString();
        String phienam = editTextphienam.getText().toString();
        String trung = editTexttrung.getText().toString();
        String viet = editTextviet.getText().toString();

        DatabaseReference chudeRef = tuVungRef.child(chude); // Tạo một Node con cho chủ đề trong Firebase

        DatabaseReference newTuVungRef = chudeRef.push(); // Tạo một key tự động
        String key = newTuVungRef.getKey(); // Lấy key tự động được tạo

        // Sử dụng key dạng số và tăng dần theo trong Firebase
        long timeStamp = System.currentTimeMillis();
        newTuVungRef = chudeRef.child(String.valueOf(timeStamp));

        // Đặt giá trị cho từng trường của từ vựng và thêm chúng vào Firebase
        newTuVungRef.child("PhienAm").setValue(phienam);
        newTuVungRef.child("Trung").setValue(trung);
        newTuVungRef.child("Viet").setValue(viet)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Nhapdulieu.this, "Từ vựng đã được thêm vào Firebase!", Toast.LENGTH_SHORT).show();
                        // Xóa dữ liệu sau khi đã thêm thành công (nếu cần)
                        editTextChude.setText("");
                        editTextphienam.setText("");
                        editTexttrung.setText("");
                        editTextviet.setText("");

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Nhapdulieu.this, "Không thể thêm từ vựng vào Firebase: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
