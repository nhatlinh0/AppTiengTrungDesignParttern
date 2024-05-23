package com.example.apptiengtrung;

import android.content.Intent;
import android.content.SharedPreferences;
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

public class Hsk2listenActivity extends AppCompatActivity {
    public static ArrayList<Hsk2listen> listenList;
    public static ArrayList<String> answerList;
    String answer;
    public static TextView tvTime;
    ImageView ivSpeaker;
    ImageView ivImage1;
    ImageView ivImage2;
    ImageView ivImage3;
    TextView tvCount;
    TextView tvNop;
    Button btnPrev;
    Button btnNext;
    ImageView ivBack;
    MediaPlayer mediaPlayer;
    public static int listCount;
    public static String timeLeftFormatted;
    public static int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hsklisten2_layout);

        if(Hsk3listenActivity.listCount != 0){
            listCount = Hsk3listenActivity.listCount;
        } else {
            listCount = Hsk1listenActivity.listCount;
        }

        if (Hsk3listenActivity.answerList != null) {
            if(!Hsk3listenActivity.answerList.isEmpty()) {
                answerList = Hsk3listenActivity.answerList;
            } else {
                answerList = Hsk1listenActivity.answerList;
            }
        } else {
            answerList = Hsk1listenActivity.answerList;
        }
        answer = "";

        score = 0;

        ivBack = findViewById(R.id.ivBack);
        tvTime = findViewById(R.id.tvTime);
        ivSpeaker = findViewById(R.id.ivSpeaker);
        ivImage1 = findViewById(R.id.ivImage1);
        ivImage2 = findViewById(R.id.ivImage2);
        ivImage3 = findViewById(R.id.ivImage3);
        tvCount = findViewById(R.id.tvCount);
        tvNop = findViewById(R.id.tvNop);
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
                Intent intent = new Intent(Hsk2listenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listCount += 1;
                if(answerList.size() <10 ){
                    answerList.add(answer);
                }
                if(listCount < 10) {
                    init(listCount);
                }
                if(listCount == 10){
                    checkScore();
                    listCount = 10;
                    Toast toast1 = Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT);
                    toast1.show();
                    Intent intent = new Intent(Hsk2listenActivity.this, Hsk3listenActivity.class);
                    startActivity(intent);
                }
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
                if(listCount < 5){
                    timeLeftFormatted = Hsk1listenActivity.timeLeftFormatted;
                    Hsk1listenActivity.tvTime.setText(timeLeftFormatted);
                    Intent intent = new Intent(Hsk2listenActivity.this, Hsk1listenActivity.class);
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

                Intent intent = new Intent(Hsk2listenActivity.this, ResultActivity.class);
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
        listenList.add(new Hsk2listen(1, getResources().getIdentifier("book", "raw", getPackageName()), getResources().getIdentifier("book", "drawable", getPackageName()), getResources().getIdentifier("telephone", "drawable", getPackageName()), getResources().getIdentifier("apple", "drawable", getPackageName()), "A"));
        listenList.add(new Hsk2listen(2, getResources().getIdentifier("flower", "raw", getPackageName()), getResources().getIdentifier("dontknow", "drawable", getPackageName()), getResources().getIdentifier("shoes", "drawable", getPackageName()), getResources().getIdentifier("flower", "drawable", getPackageName()), "C"));
        listenList.add(new Hsk2listen(3, getResources().getIdentifier("singing", "raw", getPackageName()), getResources().getIdentifier("dancing", "drawable", getPackageName()), getResources().getIdentifier("singing", "drawable", getPackageName()), getResources().getIdentifier("working", "drawable", getPackageName()), "B"));
        listenList.add(new Hsk2listen(4, getResources().getIdentifier("takeaphoto", "raw", getPackageName()), getResources().getIdentifier("staff", "drawable", getPackageName()), getResources().getIdentifier("learning", "drawable", getPackageName()), getResources().getIdentifier("takeaphoto", "drawable", getPackageName()), "C"));
        listenList.add(new Hsk2listen(5, getResources().getIdentifier("listening", "raw", getPackageName()), getResources().getIdentifier("laugh", "drawable", getPackageName()), getResources().getIdentifier("listening", "drawable", getPackageName()), getResources().getIdentifier("writing", "drawable", getPackageName()), "B"));
    }

    void init (int listCount) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }
        if(listCount == 0 ){
            btnPrev.setActivated(false);
        }
        answer = "";
        Hsk2listen hsk2listen = new Hsk2listen();
        if(listCount > 4) {
            hsk2listen = listenList.get(listCount-5);
        } else {
            hsk2listen = listenList.get(0);
        }

        mediaPlayer = MediaPlayer.create(getApplicationContext(), hsk2listen.audio);;
        mediaPlayer.setVolume(1.0f, 1.0f);
        tvCount.setText(listCount+1+"/40");
        ivSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });

        ivImage1.setImageResource(hsk2listen.image1);
        ivImage2.setImageResource(hsk2listen.image2);
        ivImage3.setImageResource(hsk2listen.image3);

        ivImage1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "A";
            }
        });
        ivImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "B";
            }
        });
        ivImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "C";
            }
        });
    }

    void checkScore (){
        for(int i = 0; i< listCount-5;i++) {
            if(answerList.get(i+5).equals(listenList.get(i).correctAnswer)) {
                score +=5;
            }
        }
    }
}
