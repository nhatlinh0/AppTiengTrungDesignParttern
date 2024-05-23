package com.example.apptiengtrung;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Hsk4listenActivity extends AppCompatActivity {
    public static ArrayList<Hsk4listen> listenList;
    public static ArrayList<String> answerList;
    String answer;
    public static TextView tvTime;
    ImageView ivSpeaker;
    LinearLayout answerA;
    LinearLayout answerB;
    LinearLayout answerC;
    TextView tvTrungA;
    TextView tvPhienAmA;
    TextView tvTrungB;
    TextView tvPhienAmB;
    TextView tvTrungC;
    TextView tvPhienAmC;
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
        setContentView(R.layout.hsklisten4_layout);

        if(Hsk1writeActivity.listCount != 0){
            listCount = Hsk1writeActivity.listCount;
        } else {
            listCount = Hsk3listenActivity.listCount;
        }

        if (Hsk1writeActivity.answerList != null) {
            if(!Hsk1writeActivity.answerList.isEmpty()) {
                answerList = Hsk1writeActivity.answerList;
            } else {
                answerList = Hsk3listenActivity.answerList;
            }
        } else {
            answerList = Hsk3listenActivity.answerList;
        }
        answer = "";

        score = 0;

        ivBack = findViewById(R.id.ivBack);
        tvTime = findViewById(R.id.tvTime);
        ivSpeaker = findViewById(R.id.ivSpeaker);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        tvTrungA = findViewById(R.id.tvTrungA);
        tvPhienAmA = findViewById(R.id.tvPhienAmA);
        tvTrungB = findViewById(R.id.tvTrungB);
        tvPhienAmB = findViewById(R.id.tvPhienAmB);
        tvTrungC = findViewById(R.id.tvTrungC);
        tvPhienAmC = findViewById(R.id.tvPhienAmC);
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
                Intent intent = new Intent(Hsk4listenActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listCount += 1;
                if(answerList.size() <20 ){
                    answerList.add(answer);
                }
                if(listCount < 20) {
                    init(listCount);
                }
                if(listCount == 20){
                    checkScore();
                    listCount = 20;
                    Toast toast1 = Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT);
                    toast1.show();
                    Intent intent = new Intent(Hsk4listenActivity.this, Hsk1writeActivity.class);
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
                if(listCount < 15){
                    timeLeftFormatted = Hsk3listenActivity.timeLeftFormatted;
                    Hsk3listenActivity.tvTime.setText(timeLeftFormatted);
                    Intent intent = new Intent(Hsk4listenActivity.this, Hsk3listenActivity.class);
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
                if(Hsk2listenActivity.score != 0) {
                    score +=Hsk2listenActivity.score;
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

                Intent intent = new Intent(Hsk4listenActivity.this, ResultActivity.class);
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
        listenList.add(new Hsk4listen(1, getResources().getIdentifier("mine", "raw", getPackageName()), "他的", "tā de", "我的", "wǒ de", "同学 的", "tóngxué de", "B"));
        listenList.add(new Hsk4listen(2, getResources().getIdentifier("saturday", "raw", getPackageName()), "星期三", "xīngqīsān", "星期五", "xīngqīwǔ", "星期六", "xīngqīliù", "C"));
        listenList.add(new Hsk4listen(3, getResources().getIdentifier("so5", "raw", getPackageName()), "5", "", "15", "", "50", "", "A"));
        listenList.add(new Hsk4listen(4, getResources().getIdentifier("tea", "raw", getPackageName()), "茶", "chá", "苹果", "píngguǒ", "杯子", "bēizi", "A"));
        listenList.add(new Hsk4listen(5, getResources().getIdentifier("gohome", "raw", getPackageName()), "爱 学习", "ài xuéxí", "很 漂亮", "hěn piàoliang", "想回家", "xiǎng huí jiā", "C"));
    }

    void init (int listCount) {
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

        answer = "";
        Hsk4listen hsk4listen = new Hsk4listen();
        if(listCount > 14) {
            hsk4listen = listenList.get(listCount-15);
        } else {
            hsk4listen = listenList.get(0);
        }
        tvTrungA.setText(hsk4listen.answerTrungA);
        tvPhienAmA.setText(hsk4listen.answerPhienAmA);
        tvTrungB.setText(hsk4listen.answerTrungB);
        tvPhienAmB.setText(hsk4listen.answerPhienAmB);
        tvTrungC.setText(hsk4listen.answerTrungC);
        tvPhienAmC.setText(hsk4listen.answerPhienAmC);
        mediaPlayer = MediaPlayer.create(getApplicationContext(), hsk4listen.audio);;
        mediaPlayer.setVolume(1.0f, 1.0f);
        tvCount.setText(listCount+1+"/40");
        ivSpeaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaPlayer.start();
            }
        });
        answerA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "A";
            }
        });
        answerB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "B";
            }
        });
        answerC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer = "C";
            }
        });
    }

    void checkScore (){
        for(int i = 0; i< listCount-15;i++) {
            if(answerList.get(i+15).equals(listenList.get(i).correctAnswer)) {
                score +=5;
            }
        }
    }
}
