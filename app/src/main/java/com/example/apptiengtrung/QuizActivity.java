package com.example.apptiengtrung;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity {
    public static String title;
    public static ArrayList<Quiz> list;
    public static ArrayList<User> listUser;
    int listCount;
    int score;
    int bestScore;
    int posUser;
    public static boolean isUpdating = false;
    MediaPlayer mediaPlayer;
    String dapAnDung;
    ImageView ivBack;
    TextView tvTitle ;
    ImageView ivSound;
    TextView tvDapAnA;
    TextView tvDapAnB;
    TextView tvDapAnC;
    TextView tvDapAnD;
    TextView tvScore;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);

        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.title);
        ivSound = findViewById(R.id.ivSound);
        tvDapAnA = findViewById(R.id.tvDapAnA);
        tvDapAnB = findViewById(R.id.tvDapAnB);
        tvDapAnC = findViewById(R.id.tvDapAnC);
        tvDapAnD = findViewById(R.id.tvDapAnD);
        tvScore = findViewById(R.id.tvScore);



        listCount = 0;
        score = 0;
        list = new ArrayList<>();


        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuizActivity.this, KyNangGiaoTiepActivity.class);
                startActivity(intent);
            }
        });

        tvTitle.setText(title);

        tvDapAnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvDapAnA.getText().equals(dapAnDung)) {
                    int colorGreen = getResources().getColor(R.color.green);
                    tvDapAnA.setTextColor(colorGreen);
                    score+=5;
                    Next();
                } else {
                    int colorRed = getResources().getColor(R.color.red);
                    tvDapAnA.setTextColor(colorRed);
                    score-=5;
                    tvScore.setText(score+"");
                }
            }
        });

        tvDapAnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvDapAnB.getText().equals(dapAnDung)) {
                    int colorGreen = getResources().getColor(R.color.green);
                    tvDapAnB.setTextColor(colorGreen);
                    score+=5;
                    Next();
                } else {
                    int colorRed = getResources().getColor(R.color.red);
                    tvDapAnB.setTextColor(colorRed);
                    score-=5;
                    tvScore.setText(score+"");
                }
            }
        });

        tvDapAnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvDapAnC.getText().equals(dapAnDung)) {
                    int colorGreen = getResources().getColor(R.color.green);
                    tvDapAnC.setTextColor(colorGreen);
                    score+=5;
                    Next();
                } else {
                    int colorRed = getResources().getColor(R.color.red);
                    tvDapAnC.setTextColor(colorRed);
                    score-=5;
                    tvScore.setText(score+"");
                }
            }
        });

        tvDapAnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(tvDapAnD.getText().equals(dapAnDung)) {
                    int colorGreen = getResources().getColor(R.color.green);
                    tvDapAnD.setTextColor(colorGreen);
                    score+=5;
                    Next();
                } else {
                    int colorRed = getResources().getColor(R.color.red);
                    tvDapAnD.setTextColor(colorRed);
                    score-=5;
                    tvScore.setText(score+"");
                }
            }
        });

        ListAdd();
        init();
    }

    private void ListAdd() {
        list.add(new Quiz("Xin chào","Xin chào","Ai kia?","Bạn là ai?","Bạn khỏe không?", getResources().getIdentifier("hello", "raw", getPackageName())));
        list.add(new Quiz("Chờ tôi","Vui mừng được gặp các bạn","Xin mời vào","Đi với tôi","Chờ tôi", getResources().getIdentifier("wait", "raw", getPackageName())));
        list.add(new Quiz("Bạn đến từ đâu","Cậu nói quá nhanh","Bạn đến từ đâu","Hãy nói chậm hơn","Có ai ở đây nói Tiếng Trung không? ",getResources().getIdentifier("whereyoufrom", "raw", getPackageName())));
        list.add(new Quiz("Xin mời vào","Bạn muốn gì?","Tôi muốn gặp bạn","Xin mời vào","Bạn khỏe không?",getResources().getIdentifier("welcome", "raw", getPackageName())));
        list.add(new Quiz("Hãy nhắc lại","Hãy nhắc lại","Tôi muốn gặp bạn","Xin mời vào","Đi với tôi",getResources().getIdentifier("repeat", "raw", getPackageName())));
        list.add(new Quiz("Tôi nghĩ vậy","Tôi nghĩ vậy","Tôi biết","Dường như với tôi","Tôi đã quên mất",getResources().getIdentifier("ithinkso", "raw", getPackageName())));
        list.add(new Quiz("Hẹn gặp lại sau nhé","Tôi không có thời gian","Chồng tôi không có ở nhà","Ngồi đây.","Hẹn gặp lại sau nhé",getResources().getIdentifier("bye", "raw", getPackageName())));
        list.add(new Quiz("Tôi đang vội"," Hẹn gặp lại bạn","Tôi đang vội","Chúc may mắn.","Xin lỗi",getResources().getIdentifier("imbusy", "raw", getPackageName())));
        list.add(new Quiz("Cảm ơn","Xin lỗi, tôi nghe không rõ","Cảm ơn","Mời uống nước","Tôi hiểu rồi",getResources().getIdentifier("thanks", "raw", getPackageName())));
        list.add(new Quiz("Bạn bao nhiêu tuổi?","Thật tuyệt","Bạn làm rất tốt đấy","Bạn bao nhiêu tuổi?","Tôi muốn gặp bạn",getResources().getIdentifier("howold", "raw", getPackageName())));
    }

    private void init() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        int colorWhite = getResources().getColor(R.color.white);
        tvScore.setText(score+"");
        tvDapAnA.setTextColor(colorWhite);
        tvDapAnB.setTextColor(colorWhite);
        tvDapAnC.setTextColor(colorWhite);
        tvDapAnD.setTextColor(colorWhite);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), list.get(listCount).audio);;
        mediaPlayer.setVolume(1.0f, 1.0f);
        ivSound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        dapAnDung = list.get(listCount).DapAnDung;
        tvDapAnA.setText(list.get(listCount).DapAnA);
        tvDapAnB.setText(list.get(listCount).DapAnB);
        tvDapAnC.setText(list.get(listCount).DapAnC);
        tvDapAnD.setText(list.get(listCount).DapAnD);
    }

    private void Next() {
        if(listCount < list.size()-1) {
            listCount++;
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(QuizActivity.this);
            builder.setMessage("Điểm của bạn là: "+score)
                    .setTitle("Chúc mừng")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(QuizActivity.this, KyNangGiaoTiepActivity.class);
                            startActivity(intent);
                        }
                    });
            AlertDialog dialog = builder.create();
            dialog.show();

        }
        init();
    }
}
