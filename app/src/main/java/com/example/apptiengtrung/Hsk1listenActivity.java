package com.example.apptiengtrung;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

public class Hsk1listenActivity extends AppCompatActivity {
    public static ArrayList<Hsk1listen> listenList;
    public static ArrayList<String> answerList;
    String answer;
    public static TextView tvTime;
    ImageView ivSpeaker;
    ImageView ivImage;
    TextView tvCount;
    TextView tvNop;
    Button btnDung;
    Button btnSai;
    Button btnPrev;
    Button btnNext;
    ImageView ivBack;
    public static CountDownTimer countDownTimer;
    MediaPlayer mediaPlayer;
    public static int listCount;
    public static int score;
    public static String timeLeftFormatted;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hsklisten1_layout);

        if(Hsk2listenActivity.listCount != 0){
            listCount = Hsk2listenActivity.listCount;
        } else {
            listCount = 0;
        }

        if (Hsk2listenActivity.answerList != null) {
            if(!Hsk2listenActivity.answerList.isEmpty()) {
                answerList = Hsk2listenActivity.answerList;
            }
        } else {
            answerList = new ArrayList<>();
        }

        score = 0;
        answer = "";
        ivBack = findViewById(R.id.ivBack);
        tvTime = findViewById(R.id.tvTime);
        ivSpeaker = findViewById(R.id.ivSpeaker);
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
                listCount = 0;
                answerList = new ArrayList<>();
                score = 0;
                if(countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                }
                Intent intent = new Intent(Hsk1listenActivity.this, MainActivity.class);
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
                if(listCount < 0){
                    listCount = 0;
                }
                if(answerList.size() > 0){
                    answerList.remove(answerList.size() - 1);
                }
                init(listCount);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listCount += 1;
                if(answerList.size() <5 ){
                    answerList.add(answer);
                }
//                Toast toast = Toast.makeText(getApplicationContext(),answerList.toString(),Toast.LENGTH_SHORT);
//                toast.show();
                if(listCount < 5) {
                    init(listCount);

                }
                if(listCount == 5){
                    if(answerList.size() > 0) {
                        checkScore();
                    }
                    listCount = 5;
                    Toast toast1 = Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT);
                    toast1.show();
                    Intent intent = new Intent(Hsk1listenActivity.this, Hsk2listenActivity.class);
                    startActivity(intent);
                }
            }
        });

        tvNop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkScore();
                if(Hsk2listenActivity.score != 0) {
                    score +=Hsk2listenActivity.score;
                }
                if(Hsk3listenActivity.score != 0) {
                    score +=Hsk3listenActivity.score;
                }
                if(Hsk4listenActivity.score != 0) {
                    score +=Hsk4listenActivity.score;
                }
                if(Hsk1writeActivity.score != 0) {
                    score +=Hsk1writeActivity.score;
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

                Intent intent = new Intent(Hsk1listenActivity.this, ResultActivity.class);
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
                if(countDownTimer != null) {
                    countDownTimer.cancel();
                    countDownTimer = null;
                }
            }
        });
        listAdd();
        init(listCount);
    }

    void listAdd() {
        listenList = new ArrayList<>();
        listenList.add(new Hsk1listen(1, getResources().getIdentifier("calling", "raw", getPackageName()), getResources().getIdentifier("calling", "drawable", getPackageName()), "Đúng"));
        listenList.add(new Hsk1listen(2, getResources().getIdentifier("rideabike", "raw", getPackageName()), getResources().getIdentifier("rideabike", "drawable", getPackageName()), "Đúng"));
        listenList.add(new Hsk1listen(3, getResources().getIdentifier("greeting", "raw", getPackageName()), getResources().getIdentifier("greeting", "drawable", getPackageName()), "Đúng"));
        listenList.add(new Hsk1listen(4, getResources().getIdentifier("clock", "raw", getPackageName()), getResources().getIdentifier("clock", "drawable", getPackageName()), "Sai"));
        listenList.add(new Hsk1listen(5, getResources().getIdentifier("eating", "raw", getPackageName()), getResources().getIdentifier("eating", "drawable", getPackageName()), "Sai"));
    }

    void init(int listCount) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        if (countDownTimer == null) {
            startCountdown();
        }

        answer = "";
        Hsk1listen hsk1listen = listenList.get(listCount);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), hsk1listen.audio);;
        mediaPlayer.setVolume(1.0f, 1.0f);
        tvCount.setText(listCount+1+"/40");
        ivSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        ivImage.setImageResource(hsk1listen.image);
    }

    private void startCountdown() {
        countDownTimer = new CountDownTimer(35 * 60 * 1000, 1000) { // 35 seconds countdown
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftFormatted = new SimpleDateFormat("mm:ss", Locale.getDefault()).format(millisUntilFinished);
                if(Hsk2listenActivity.tvTime != null) {
                    Hsk2listenActivity.tvTime.setText(""+timeLeftFormatted);
                }
                if(Hsk3listenActivity.tvTime != null) {
                    Hsk3listenActivity.tvTime.setText(""+timeLeftFormatted);
                }
                if(Hsk4listenActivity.tvTime != null) {
                    Hsk4listenActivity.tvTime.setText(""+timeLeftFormatted);
                }
                if(Hsk1writeActivity.tvTime != null) {
                    Hsk1writeActivity.tvTime.setText(""+timeLeftFormatted);
                }
                if(Hsk2writeActivity.tvTime != null) {
                    Hsk2writeActivity.tvTime.setText(""+timeLeftFormatted);
                }
                if(Hsk3writeActivity.tvTime != null) {
                    Hsk3writeActivity.tvTime.setText(""+timeLeftFormatted);
                }
                if(Hsk4writeActivity.tvTime != null) {
                    Hsk4writeActivity.tvTime.setText(""+timeLeftFormatted);
                }
                tvTime.setText(""+timeLeftFormatted);
            }

            @Override
            public void onFinish() {
                tvTime.setText("Countdown finished!");
            }
        }.start();
    }

    void checkScore (){
        for(int i = 0; i< listCount;i++) {
            if(answerList.get(i).equals(listenList.get(i).correctAnswer)) {
                this.score +=5;
            }
        }
    }

}
