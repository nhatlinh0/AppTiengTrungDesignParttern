package com.example.apptiengtrung;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Hsk1writeActivity extends AppCompatActivity {
    public static ArrayList<Hsk1write> listenList;
    public static ArrayList<String> answerList;
    String answer;
    public static TextView tvTime;
    TextView tvTrung;
    TextView tvPhienAm;
    ImageView ivImage;
    TextView tvCount;
    TextView tvNop;
    Button btnDung;
    Button btnSai;
    Button btnPrev;
    Button btnNext;
    ImageView ivBack;
    public static int listCount;
    public static int score;
    public static String timeLeftFormatted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hskwrite1_layout);


        if(Hsk2writeActivity.listCount != 0){
            listCount = Hsk2writeActivity.listCount;
        } else {
            listCount = Hsk4listenActivity.listCount;
        }

        if (Hsk2writeActivity.answerList != null) {
            if(!Hsk2writeActivity.answerList.isEmpty())
            {
                answerList = Hsk2writeActivity.answerList;
            } else {
                answerList = Hsk4listenActivity.answerList;
            }
        } else {
            answerList = Hsk4listenActivity.answerList;
        }

        score = 0;
        answer = "";
        ivBack = findViewById(R.id.ivBack);
        tvTime = findViewById(R.id.tvTime);
        tvTrung = findViewById(R.id.tvTrung);
        tvPhienAm = findViewById(R.id.tvPhienAm);
        ivImage = findViewById(R.id.ivImage);
        tvCount = findViewById(R.id.tvCount);
        tvNop = findViewById(R.id.tvNop);
        btnDung = findViewById(R.id.btnDung);
        btnSai = findViewById(R.id.btnSai);
        btnPrev = findViewById(R.id.btnPrev);
        btnNext = findViewById(R.id.btnNext);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hsk1listenActivity.listCount = 0;
                Hsk1listenActivity.answerList = new ArrayList<>();
                Hsk1listenActivity.score = 0;
                Hsk2listenActivity.listCount = 0;
                Hsk2listenActivity.answerList = new ArrayList<>();
                Hsk2listenActivity.score = 0;
                Hsk3listenActivity.listCount = 0;
                Hsk3listenActivity.answerList = new ArrayList<>();
                Hsk3listenActivity.score = 0;
                Hsk4listenActivity.listCount = 0;
                Hsk4listenActivity.answerList = new ArrayList<>();
                Hsk4listenActivity.score = 0;
                Hsk1writeActivity.listCount = 0;
                Hsk1writeActivity.answerList = new ArrayList<>();
                Hsk1writeActivity.score = 0;
                Hsk2writeActivity.listCount = 0;
                Hsk2writeActivity.answerList = new ArrayList<>();
                Hsk2writeActivity.score = 0;
                Hsk3writeActivity.listCount = 0;
                Hsk3writeActivity.answerList = new ArrayList<>();
                Hsk3writeActivity.score = 0;
                Hsk4writeActivity.listCount = 0;
                Hsk4writeActivity.answerList = new ArrayList<>();
                Hsk4writeActivity.score = 0;
                listCount = 0;
                answerList = new ArrayList<>();
                score = 0;
                if(Hsk1listenActivity.countDownTimer != null) {
                    Hsk1listenActivity.countDownTimer.cancel();
                    Hsk1listenActivity.countDownTimer = null;
                }
                Intent intent = new Intent(Hsk1writeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnDung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "Đúng";
            }
        });

        btnSai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "Sai";
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listCount -= 1;
                if(answerList.size() > 0){
                    answerList.remove(answerList.size() - 1);
                }
                init(listCount);
                if(listCount < 20){
                    timeLeftFormatted = Hsk4listenActivity.timeLeftFormatted;
                    Hsk4listenActivity.tvTime.setText(timeLeftFormatted);
                    Intent intent = new Intent(Hsk1writeActivity.this, Hsk4listenActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listCount += 1;
                if(answerList.size() < 25 ){
                    answerList.add(answer);
                }
                if(listCount < 25) {
                    init(listCount);
                }
                if(listCount == 25){
                    checkScore();
                    listCount = 25;
                    Toast toast1 = Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT);
                    toast1.show();
                    Intent intent = new Intent(Hsk1writeActivity.this, Hsk2writeActivity.class);
                    startActivity(intent);
                }
            }
        });

        tvNop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkScore();
                if(Hsk1listenActivity.score != 0) {
                    score +=Hsk1listenActivity.score;
                }
                if(Hsk3listenActivity.score != 0) {
                    score +=Hsk3listenActivity.score;
                }
                if(Hsk4listenActivity.score != 0) {
                    score +=Hsk4listenActivity.score;
                }
                if(Hsk2listenActivity.score != 0) {
                    score +=Hsk2listenActivity.score;
                }
                if(Hsk2writeActivity.score != 0) {
                    score +=Hsk2writeActivity.score;
                }
                if(Hsk3writeActivity.score != 0) {
                    score +=Hsk3writeActivity.score;
                }
                if(Hsk4writeActivity.score != 0) {
                    score +=Hsk4writeActivity.score;
                }

                Intent intent = new Intent(Hsk1writeActivity.this, ResultActivity.class);
                intent.putExtra("score", score);
                startActivity(intent);
                Toast toast1 = Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT);
                toast1.show();
                Hsk1listenActivity.listCount = 0;
                Hsk1listenActivity.answerList = new ArrayList<>();
                Hsk1listenActivity.score = 0;
                Hsk2listenActivity.listCount = 0;
                Hsk2listenActivity.answerList = new ArrayList<>();
                Hsk2listenActivity.score = 0;
                Hsk3listenActivity.listCount = 0;
                Hsk3listenActivity.answerList = new ArrayList<>();
                Hsk3listenActivity.score = 0;
                Hsk4listenActivity.listCount = 0;
                Hsk4listenActivity.answerList = new ArrayList<>();
                Hsk4listenActivity.score = 0;
                Hsk1writeActivity.listCount = 0;
                Hsk1writeActivity.answerList = new ArrayList<>();
                Hsk1writeActivity.score = 0;
                Hsk2writeActivity.listCount = 0;
                Hsk2writeActivity.answerList = new ArrayList<>();
                Hsk2writeActivity.score = 0;
                Hsk3writeActivity.listCount = 0;
                Hsk3writeActivity.answerList = new ArrayList<>();
                Hsk3writeActivity.score = 0;
                Hsk4writeActivity.listCount = 0;
                Hsk4writeActivity.answerList = new ArrayList<>();
                Hsk4writeActivity.score = 0;
                listCount = 0;
                answerList = new ArrayList<>();
                score = 0;
                if(Hsk1listenActivity.countDownTimer != null) {
                    Hsk1listenActivity.countDownTimer.cancel();
                    Hsk1listenActivity.countDownTimer = null;
                }
            }
        });

        listAdd();
        init(listCount);
    }

    void listAdd() {
        listenList = new ArrayList<>();
        listenList.add(new Hsk1write(1, getResources().getIdentifier("writing", "drawable", getPackageName()), "写","xiě", "Đúng"));
        listenList.add(new Hsk1write(2, getResources().getIdentifier("listening", "drawable", getPackageName()), "听","tīng", "Đúng"));
        listenList.add(new Hsk1write(3, getResources().getIdentifier("tea", "drawable", getPackageName()), "菜","cài", "Sai"));
        listenList.add(new Hsk1write(4, getResources().getIdentifier("woman", "drawable", getPackageName()), "他","tā", "Sai"));
        listenList.add(new Hsk1write(5, getResources().getIdentifier("dog", "drawable", getPackageName()), "狗","gǒu", "Đúng"));
    }

    void init(int listCount) {
        answer = "";
        Hsk1write hsk1write = new Hsk1write();
        if(listCount > 19) {
            hsk1write = listenList.get(listCount-20);
        } else {
            hsk1write = listenList.get(0);
        }
        tvCount.setText(listCount+1+"/40");
        tvTrung.setText(hsk1write.trung);
        tvPhienAm.setText(hsk1write.phienAm);
        ivImage.setImageResource(hsk1write.image);
    }

    void checkScore (){
        for(int i = 0; i< listCount-20;i++) {
            if(answerList.get(i+20).equals(listenList.get(i).correctAnswer)) {
                this.score +=5;
            }
        }
    }
}
