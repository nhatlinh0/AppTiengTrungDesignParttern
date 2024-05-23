package com.example.apptiengtrung;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Hsk3writeActivity extends AppCompatActivity {
    public static ArrayList<Hsk3write> listenList;
    public static ArrayList<String> answerList;
    String answer1;
    String answer2;
    String answer3;
    String answer4;
    String answer5;
    public static TextView tvTime;
    TextView tvAnswerTrungA;
    TextView tvAnswerPhienAmA;
    TextView tvAnswerTrungB;
    TextView tvAnswerPhienAmB;
    TextView tvAnswerTrungC;
    TextView tvAnswerPhienAmC;
    TextView tvAnswerTrungD;
    TextView tvAnswerPhienAmD;
    TextView tvAnswerTrungE;
    TextView tvAnswerPhienAmE;
    TextView tvTrung1;
    TextView tvPhienAm1;
    TextView tvTrung2;
    TextView tvPhienAm2;
    TextView tvTrung3;
    TextView tvPhienAm3;
    TextView tvTrung4;
    TextView tvPhienAm4;
    TextView tvTrung5;
    TextView tvPhienAm5;
    Button answerAquestion1;
    Button answerBquestion1;
    Button answerCquestion1;
    Button answerDquestion1;
    Button answerEquestion1;
    Button answerAquestion2;
    Button answerBquestion2;
    Button answerCquestion2;
    Button answerDquestion2;
    Button answerEquestion2;
    Button answerAquestion3;
    Button answerBquestion3;
    Button answerCquestion3;
    Button answerDquestion3;
    Button answerEquestion3;
    Button answerAquestion4;
    Button answerBquestion4;
    Button answerCquestion4;
    Button answerDquestion4;
    Button answerEquestion4;
    Button answerAquestion5;
    Button answerBquestion5;
    Button answerCquestion5;
    Button answerDquestion5;
    Button answerEquestion5;
    TextView tvCount;
    TextView tvNop;
    Button btnPrev;
    Button btnNext;
    ImageView ivBack;
    public static int listCount;
    public static String timeLeftFormatted;
    public static int score;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hskwrite3_layout);

        if(Hsk4writeActivity.listCount != 0) {
            listCount = Hsk4writeActivity.listCount;
        } else {
            listCount = Hsk2writeActivity.listCount;
        }

        if(Hsk4writeActivity.answerList != null) {
            if(!Hsk4writeActivity.answerList.isEmpty()) {
                answerList = Hsk4writeActivity.answerList;
            }else {
                answerList = Hsk2writeActivity.answerList;
            }
        } else {
            answerList = Hsk2writeActivity.answerList;
        }

        answer1 = "";
        answer2 = "";
        answer3 = "";
        answer4 = "";
        answer5 = "";
        score = 0;
        ivBack = findViewById(R.id.ivBack);
        tvTime = findViewById(R.id.tvTime);
        tvAnswerTrungA = findViewById(R.id.tvAnswerTrungA);
        tvAnswerPhienAmA = findViewById(R.id.tvAnswerPhienAmA);
        tvAnswerTrungB = findViewById(R.id.tvAnswerTrungB);
        tvAnswerPhienAmB = findViewById(R.id.tvAnswerPhienAmB);
        tvAnswerTrungC = findViewById(R.id.tvAnswerTrungC);
        tvAnswerPhienAmC = findViewById(R.id.tvAnswerPhienAmC);
        tvAnswerTrungD = findViewById(R.id.tvAnswerTrungD);
        tvAnswerPhienAmD = findViewById(R.id.tvAnswerPhienAmD);
        tvAnswerTrungE = findViewById(R.id.tvAnswerTrungE);
        tvAnswerPhienAmE = findViewById(R.id.tvAnswerPhienAmE);
        tvTrung1 = findViewById(R.id.tvTrung1);
        tvPhienAm1 = findViewById(R.id.tvPhienAm1);
        tvTrung2 = findViewById(R.id.tvTrung2);
        tvPhienAm2 = findViewById(R.id.tvPhienAm2);
        tvTrung3 = findViewById(R.id.tvTrung3);
        tvPhienAm3 = findViewById(R.id.tvPhienAm3);
        tvTrung4 = findViewById(R.id.tvTrung4);
        tvPhienAm4 = findViewById(R.id.tvPhienAm4);
        tvTrung5 = findViewById(R.id.tvTrung5);
        tvPhienAm5 = findViewById(R.id.tvPhienAm5);

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
                Intent intent = new Intent(Hsk3writeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listCount += 5;
                if(answerList.size() <35 ){
                    answerList.add(answer1);
                    answerList.add(answer2);
                    answerList.add(answer3);
                    answerList.add(answer4);
                    answerList.add(answer5);
                }

                if(listCount == 35){
                    checkScore();
                    listCount = 35;
                    Toast toast1 = Toast.makeText(getApplicationContext(),score+"",Toast.LENGTH_SHORT);
                    toast1.show();
                    Intent intent = new Intent(Hsk3writeActivity.this, Hsk4writeActivity.class);
                    startActivity(intent);
                }
            }
        });

        btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listCount -= 5;
                if(answerList.size() > 0){
                    for (int i = 0; i < 5; i++) {
                          answerList.remove(answerList.size() - 1);
                    }
                }
                init(listCount);
                if(listCount < 30){
                    timeLeftFormatted = Hsk2writeActivity.timeLeftFormatted;
                    Hsk2listenActivity.tvTime.setText(timeLeftFormatted);
                    Intent intent = new Intent(Hsk3writeActivity.this, Hsk2writeActivity.class);
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
                if(Hsk4listenActivity.score != 0) {
                    score +=Hsk4listenActivity.score;
                }
                if(Hsk2writeActivity.score != 0) {
                    score +=Hsk2writeActivity.score;
                }
                if(Hsk4writeActivity.score != 0) {
                    score +=Hsk4writeActivity.score;
                }

                Intent intent = new Intent(Hsk3writeActivity.this, ResultActivity.class);
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
        answer1();
        answer2();
        answer3();
        answer4();
        answer5();
        init(listCount);
    }

    void listAdd() {
        listenList = new ArrayList<>();
        listenList.add(new Hsk3write(1, "医院 。", "Yīyuàn.", "那个人是谁？", "Nàge rén shì shéi?", "C")); // nguoi do la ai
        listenList.add(new Hsk3write(2, "下雨了 。", "Xià yǔ le. 。", "他 女儿多大了 ?", "Tā nǚ'ér duō dà le?", "D")); //em bn tuoi
        listenList.add(new Hsk3write(3, "我不认识 她 。", "Wǒ bú rènshi tā.", "你的同学在哪儿工作 ?", "Nǐ de tóngxué zài nǎr gōngzuò?", "A")); // ban m lam nghe gi
        listenList.add(new Hsk3write(4, "7岁 。", "suì.", "昨天上午天气怎么样 ?", "Zuótiān shàngwǔ tiānqì zěnmeyàng?", "B")); // hoom qua thoi tiet
        listenList.add(new Hsk3write(5, "下个月 。", "Xià ge yuè", "爸爸什么时候来北京呢 ?", "Bàba shénme shíhou lái Běijīng ne?", "E")); // khi nao bo m di
    }

    void init (int listCount) {
        answer1 = "";
        answer2 = "";
        answer3 = "";
        answer4 = "";
        answer5 = "";

        tvCount.setText(listCount+5+"/40");
        tvAnswerTrungA.setText(listenList.get(0).answerTrung);
        tvAnswerPhienAmA.setText(listenList.get(0).answerPhienAm);
        tvAnswerTrungB.setText(listenList.get(1).answerTrung);
        tvAnswerPhienAmB.setText(listenList.get(1).answerPhienAm);
        tvAnswerTrungC.setText(listenList.get(2).answerTrung);
        tvAnswerPhienAmC.setText(listenList.get(2).answerPhienAm);
        tvAnswerTrungD.setText(listenList.get(3).answerTrung);
        tvAnswerPhienAmD.setText(listenList.get(3).answerPhienAm);
        tvAnswerTrungE.setText(listenList.get(4).answerTrung);
        tvAnswerPhienAmE.setText(listenList.get(4).answerPhienAm);

        tvTrung1.setText(listenList.get(0).trung);
        tvPhienAm1.setText(listenList.get(0).phienAm);
        tvTrung2.setText(listenList.get(1).trung);
        tvPhienAm2.setText(listenList.get(1).phienAm);
        tvTrung3.setText(listenList.get(2).trung);
        tvPhienAm3.setText(listenList.get(2).phienAm);
        tvTrung4.setText(listenList.get(3).trung);
        tvPhienAm4.setText(listenList.get(3).phienAm);
        tvTrung5.setText(listenList.get(4).trung);
        tvPhienAm5.setText(listenList.get(4).phienAm);

    }
    void answer1(){
        answerAquestion1 = findViewById(R.id.answerAquestion1);
        answerBquestion1 = findViewById(R.id.answerBquestion1);
        answerCquestion1 = findViewById(R.id.answerCquestion1);
        answerDquestion1 = findViewById(R.id.answerDquestion1);
        answerEquestion1 = findViewById(R.id.answerEquestion1);

        answerAquestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer1 = "A";
                answerAquestion1.setTextColor(Color.BLUE);
                answerBquestion1.setTextColor(Color.BLACK);
                answerCquestion1.setTextColor(Color.BLACK);
                answerDquestion1.setTextColor(Color.BLACK);
                answerEquestion1.setTextColor(Color.BLACK);
            }
        });
        answerBquestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer1 = "B";
                answerAquestion1.setTextColor(Color.BLACK);
                answerBquestion1.setTextColor(Color.BLUE);
                answerCquestion1.setTextColor(Color.BLACK);
                answerDquestion1.setTextColor(Color.BLACK);
                answerEquestion1.setTextColor(Color.BLACK);
            }
        });
        answerCquestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer1 = "C";
                answerAquestion1.setTextColor(Color.BLACK);
                answerBquestion1.setTextColor(Color.BLACK);
                answerCquestion1.setTextColor(Color.BLUE);
                answerDquestion1.setTextColor(Color.BLACK);
                answerEquestion1.setTextColor(Color.BLACK);
            }
        });
        answerDquestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer1 = "D";
                answerAquestion1.setTextColor(Color.BLACK);
                answerBquestion1.setTextColor(Color.BLACK);
                answerCquestion1.setTextColor(Color.BLACK);
                answerDquestion1.setTextColor(Color.BLUE);
                answerEquestion1.setTextColor(Color.BLACK);
            }
        });
        answerEquestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer1 = "E";
                answerAquestion1.setTextColor(Color.BLACK);
                answerBquestion1.setTextColor(Color.BLACK);
                answerCquestion1.setTextColor(Color.BLACK);
                answerDquestion1.setTextColor(Color.BLACK);
                answerEquestion1.setTextColor(Color.BLUE);
            }
        });
    }
    void answer2(){
        answerAquestion2 = findViewById(R.id.answerAquestion2);
        answerBquestion2 = findViewById(R.id.answerBquestion2);
        answerCquestion2 = findViewById(R.id.answerCquestion2);
        answerDquestion2 = findViewById(R.id.answerDquestion2);
        answerEquestion2 = findViewById(R.id.answerEquestion2);

        answerAquestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer2 = "A";
                answerAquestion2.setTextColor(Color.BLUE);
                answerBquestion2.setTextColor(Color.BLACK);
                answerCquestion2.setTextColor(Color.BLACK);
                answerDquestion2.setTextColor(Color.BLACK);
                answerEquestion2.setTextColor(Color.BLACK);
            }
        });
        answerBquestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer2 = "B";
                answerAquestion2.setTextColor(Color.BLACK);
                answerBquestion2.setTextColor(Color.BLUE);
                answerCquestion2.setTextColor(Color.BLACK);
                answerDquestion2.setTextColor(Color.BLACK);
                answerEquestion2.setTextColor(Color.BLACK);
            }
        });
        answerCquestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer2 = "C";
                answerAquestion2.setTextColor(Color.BLACK);
                answerBquestion2.setTextColor(Color.BLACK);
                answerCquestion2.setTextColor(Color.BLUE);
                answerDquestion2.setTextColor(Color.BLACK);
                answerEquestion2.setTextColor(Color.BLACK);
            }
        });
        answerDquestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer1 = "D";
                answerAquestion2.setTextColor(Color.BLACK);
                answerBquestion2.setTextColor(Color.BLACK);
                answerCquestion2.setTextColor(Color.BLACK);
                answerDquestion2.setTextColor(Color.BLUE);
                answerEquestion2.setTextColor(Color.BLACK);
            }
        });
        answerEquestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer2 = "E";
                answerAquestion2.setTextColor(Color.BLACK);
                answerBquestion2.setTextColor(Color.BLACK);
                answerCquestion2.setTextColor(Color.BLACK);
                answerDquestion2.setTextColor(Color.BLACK);
                answerEquestion2.setTextColor(Color.BLUE);
            }
        });
    }
    void answer3(){
        answerAquestion3 = findViewById(R.id.answerAquestion3);
        answerBquestion3 = findViewById(R.id.answerBquestion3);
        answerCquestion3 = findViewById(R.id.answerCquestion3);
        answerDquestion3 = findViewById(R.id.answerDquestion3);
        answerEquestion3 = findViewById(R.id.answerEquestion3);

        answerAquestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer3 = "A";
                answerAquestion3.setTextColor(Color.BLUE);
                answerBquestion3.setTextColor(Color.BLACK);
                answerCquestion3.setTextColor(Color.BLACK);
                answerDquestion3.setTextColor(Color.BLACK);
                answerEquestion3.setTextColor(Color.BLACK);
            }
        });
        answerBquestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer3 = "B";
                answerAquestion3.setTextColor(Color.BLACK);
                answerBquestion3.setTextColor(Color.BLUE);
                answerCquestion3.setTextColor(Color.BLACK);
                answerDquestion3.setTextColor(Color.BLACK);
                answerEquestion3.setTextColor(Color.BLACK);
            }
        });
        answerCquestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer3 = "C";
                answerAquestion3.setTextColor(Color.BLACK);
                answerBquestion3.setTextColor(Color.BLACK);
                answerCquestion3.setTextColor(Color.BLUE);
                answerDquestion3.setTextColor(Color.BLACK);
                answerEquestion3.setTextColor(Color.BLACK);
            }
        });
        answerDquestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer3 = "D";
                answerAquestion3.setTextColor(Color.BLACK);
                answerBquestion3.setTextColor(Color.BLACK);
                answerCquestion3.setTextColor(Color.BLACK);
                answerDquestion3.setTextColor(Color.BLUE);
                answerEquestion3.setTextColor(Color.BLACK);
            }
        });
        answerEquestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer3 = "E";
                answerAquestion3.setTextColor(Color.BLACK);
                answerBquestion3.setTextColor(Color.BLACK);
                answerCquestion3.setTextColor(Color.BLACK);
                answerDquestion3.setTextColor(Color.BLACK);
                answerEquestion3.setTextColor(Color.BLUE);
            }
        });
    }
    void answer4(){
        answerAquestion4 = findViewById(R.id.answerAquestion4);
        answerBquestion4 = findViewById(R.id.answerBquestion4);
        answerCquestion4 = findViewById(R.id.answerCquestion4);
        answerDquestion4 = findViewById(R.id.answerDquestion4);
        answerEquestion4 = findViewById(R.id.answerEquestion4);

        answerAquestion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer4 = "A";
                answerAquestion4.setTextColor(Color.BLUE);
                answerBquestion4.setTextColor(Color.BLACK);
                answerCquestion4.setTextColor(Color.BLACK);
                answerDquestion4.setTextColor(Color.BLACK);
                answerEquestion4.setTextColor(Color.BLACK);
            }
        });
        answerBquestion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer4 = "B";
                answerAquestion4.setTextColor(Color.BLACK);
                answerBquestion4.setTextColor(Color.BLUE);
                answerCquestion4.setTextColor(Color.BLACK);
                answerDquestion4.setTextColor(Color.BLACK);
                answerEquestion4.setTextColor(Color.BLACK);
            }
        });
        answerCquestion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer4 = "C";
                answerAquestion4.setTextColor(Color.BLACK);
                answerBquestion4.setTextColor(Color.BLACK);
                answerCquestion4.setTextColor(Color.BLUE);
                answerDquestion4.setTextColor(Color.BLACK);
                answerEquestion4.setTextColor(Color.BLACK);
            }
        });
        answerDquestion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer4 = "D";
                answerAquestion4.setTextColor(Color.BLACK);
                answerBquestion4.setTextColor(Color.BLACK);
                answerCquestion4.setTextColor(Color.BLACK);
                answerDquestion4.setTextColor(Color.BLUE);
                answerEquestion4.setTextColor(Color.BLACK);
            }
        });
        answerEquestion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer4 = "E";
                answerAquestion4.setTextColor(Color.BLACK);
                answerBquestion4.setTextColor(Color.BLACK);
                answerCquestion4.setTextColor(Color.BLACK);
                answerDquestion4.setTextColor(Color.BLACK);
                answerEquestion4.setTextColor(Color.BLUE);
            }
        });
    }
    void answer5(){
        answerAquestion5 = findViewById(R.id.answerAquestion5);
        answerBquestion5 = findViewById(R.id.answerBquestion5);
        answerCquestion5 = findViewById(R.id.answerCquestion5);
        answerDquestion5 = findViewById(R.id.answerDquestion5);
        answerEquestion5 = findViewById(R.id.answerEquestion5);

        answerAquestion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer5 = "A";
                answerAquestion5.setTextColor(Color.BLUE);
                answerBquestion5.setTextColor(Color.BLACK);
                answerCquestion5.setTextColor(Color.BLACK);
                answerDquestion5.setTextColor(Color.BLACK);
                answerEquestion5.setTextColor(Color.BLACK);
            }
        });
        answerBquestion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer5= "B";
                answerAquestion5.setTextColor(Color.BLACK);
                answerBquestion5.setTextColor(Color.BLUE);
                answerCquestion5.setTextColor(Color.BLACK);
                answerDquestion5.setTextColor(Color.BLACK);
                answerEquestion5.setTextColor(Color.BLACK);
            }
        });
        answerCquestion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer5 = "C";
                answerAquestion5.setTextColor(Color.BLACK);
                answerBquestion5.setTextColor(Color.BLACK);
                answerCquestion5.setTextColor(Color.BLUE);
                answerDquestion5.setTextColor(Color.BLACK);
                answerEquestion5.setTextColor(Color.BLACK);
            }
        });
        answerDquestion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer5 = "D";
                answerAquestion5.setTextColor(Color.BLACK);
                answerBquestion5.setTextColor(Color.BLACK);
                answerCquestion5.setTextColor(Color.BLACK);
                answerDquestion5.setTextColor(Color.BLUE);
                answerEquestion5.setTextColor(Color.BLACK);
            }
        });
        answerEquestion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer5 = "E";
                answerAquestion5.setTextColor(Color.BLACK);
                answerBquestion5.setTextColor(Color.BLACK);
                answerCquestion5.setTextColor(Color.BLACK);
                answerDquestion5.setTextColor(Color.BLACK);
                answerEquestion5.setTextColor(Color.BLUE);
            }
        });
    }
    void checkScore (){
        for(int i = 0; i< listCount-30;i++) {
            if(answerList.get(i+30).equals(listenList.get(i).correctAnswer)) {
                score +=5;
            }
        }
    }
}
