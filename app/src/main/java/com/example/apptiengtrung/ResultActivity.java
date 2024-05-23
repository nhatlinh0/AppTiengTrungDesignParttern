package com.example.apptiengtrung;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ResultActivity extends AppCompatActivity {
    public static TextView tvTotalScore;
    public static boolean isUpdating = false;
    TextView tvBackToMenu;
    int score;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_layout);

        score = 0;
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            score = extras.getInt("score");
        }
        tvTotalScore = findViewById(R.id.tvTotalScore);
        tvTotalScore.setText(score+"/200");
        tvBackToMenu = findViewById(R.id.tvBackToMenu);
        tvBackToMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        isUpdating = true;
        User user = ListUser.findUser(LoginActivity.username);
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("User").child(user.id+"").setValue(new User(user.id,user.username,user.password,score,user.isTeacher,user.isAdmin))
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                isUpdating = false;
            }
        });

    }
}
