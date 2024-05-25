package com.example.apptiengtrung;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
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

import java.util.ArrayList;
import java.util.Collections;

public class SapXepActivity extends AppCompatActivity {
//    mvvm parttern
//    observe

    public static String title;
    public ArrayList<SapXep> list;
    ImageView ivBack;
    TextView tvTitle ;

    ImageView ivIdea;
    TextView tvTiengViet;
    TextView tvPhienAm;
    TextView tvTiengTrung;
    TextView tvXemDapAn;
    TextView tvDapAnA;
    TextView tvDapAnB;
    TextView tvDapAnC;
    TextView tvDapAnD;
    String dapAn;
    String ketQuaDung;
    int listCount;
    int selectedCount;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sapxep_layout);

        dapAn = "";
        ketQuaDung ="";
        listCount = 0;
        selectedCount = 0;
        list = new ArrayList<>();
        ivBack = findViewById(R.id.ivBack);
        tvTitle = findViewById(R.id.title);

        ivIdea = findViewById(R.id.ivIdea);
        tvTiengViet = findViewById(R.id.tvTiengViet);
        tvPhienAm = findViewById(R.id.tvPhienAm);
        tvTiengTrung = findViewById(R.id.tvTiengTrung);
        tvXemDapAn = findViewById(R.id.tvXemDapAn);
        tvDapAnA = findViewById(R.id.tvDapAnA);
        tvDapAnB = findViewById(R.id.tvDapAnB);
        tvDapAnC = findViewById(R.id.tvDapAnC);
        tvDapAnD = findViewById(R.id.tvDapAnD);

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), KyNangGiaoTiepActivity.class);
                startActivity(intent);
            }
        });

        tvTitle.setText(title);

        ListAdd();

        ivIdea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int colorWhite = getResources().getColor(R.color.white);
                tvXemDapAn.setTextColor(colorWhite);
            }
        });

        tvDapAnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dapAn = dapAn + tvDapAnA.getText();
                tvTiengTrung.setText(dapAn);
                tvDapAnA.setVisibility(View.INVISIBLE);
                selectedCount --;
                if(selectedCount == 0) {
                    if(dapAn.equals(ketQuaDung)) {
                        if(listCount < list.size()-1) {
                            listCount++;
                            init(listCount);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SapXepActivity.this);
                            builder.setMessage("Bạn đã hoàn thành bộ câu hỏi")
                                    .setTitle("Thông báo")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(SapXepActivity.this, KyNangGiaoTiepActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SapXepActivity.this);
                        builder.setMessage("Câu trả lời sai")
                                .setTitle("Thông báo")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        init(listCount);
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }
        });

        tvDapAnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dapAn = dapAn + tvDapAnB.getText();
                tvTiengTrung.setText(dapAn);
                tvDapAnB.setVisibility(View.INVISIBLE);
                selectedCount --;
                if(selectedCount == 0) {
                    if(dapAn.equals(ketQuaDung)) {
                        if(listCount < list.size()-1) {
                            listCount++;
                            init(listCount);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SapXepActivity.this);
                            builder.setMessage("Bạn đã hoàn thành bộ câu hỏi")
                                    .setTitle("Thông báo")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(SapXepActivity.this, KyNangGiaoTiepActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SapXepActivity.this);
                        builder.setMessage("Câu trả lời sai")
                                .setTitle("Thông báo")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        init(listCount);
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }
        });

        tvDapAnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dapAn = dapAn + tvDapAnC.getText();
                tvTiengTrung.setText(dapAn);
                tvDapAnC.setVisibility(View.INVISIBLE);
                selectedCount --;
                if(selectedCount == 0) {
                    if(dapAn.equals(ketQuaDung)) {
                        if(listCount < list.size()-1) {
                            listCount++;
                            init(listCount);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SapXepActivity.this);
                            builder.setMessage("Bạn đã hoàn thành bộ câu hỏi")
                                    .setTitle("Thông báo")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(SapXepActivity.this, KyNangGiaoTiepActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SapXepActivity.this);
                        builder.setMessage("Câu trả lời sai")
                                .setTitle("Thông báo")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        init(listCount);
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }

                }
            }
        });

        tvDapAnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dapAn = dapAn + tvDapAnD.getText();
                tvTiengTrung.setText(dapAn);
                tvDapAnD.setVisibility(View.INVISIBLE);
                selectedCount --;
                if(selectedCount == 0) {
                    if(dapAn.equals(ketQuaDung)) {
                        if(listCount < list.size()-1) {
                            listCount++;
                            init(listCount);
                        } else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(SapXepActivity.this);
                            builder.setMessage("Bạn đã hoàn thành bộ câu hỏi")
                                    .setTitle("Thông báo")
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            Intent intent = new Intent(SapXepActivity.this, KyNangGiaoTiepActivity.class);
                                            startActivity(intent);
                                        }
                                    });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SapXepActivity.this);
                        builder.setMessage("Câu trả lời sai")
                                .setTitle("Thông báo")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        init(listCount);
                                    }
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                }
            }
        });

    }

    void ListAdd(){
        list = new ArrayList<>();
//        list.add(new SapXep(1,"你好吗？","Anh có khỏe không?","nǐ hǎo ma?"));
//        list.add(new SapXep(2,"谢谢，我好，你呢？","Cảm ơn, tôi khỏe, còn anh?","xièxie, wǒ hǎo, nǐ ne?"));
//        list.add(new SapXep(3,"我也好。","Tôi cũng khỏe.","wǒ yě hǎo"));
//        list.add(new SapXep(4,"你忙吗？","Anh có bận không?"," nǐ máng ma?"));
//        list.add(new SapXep(5,"不太忙。","Không bận lắm.","bù tài máng"));
//        list.add(new SapXep(6,"你买菜吗？","Bạn đi mua thức ăn à?","shì, wǒ qù mǎi cài."));
//        list.add(new SapXep(7,"是，我去买菜。","Vâng, tôi đi mua thức ăn.","nǐ hǎo ma?"));
//        list.add(new SapXep(8,"你家怎么样？","Gia đình anh thế nào?","nǐ jiā zěnme yàng?"));
//        list.add(new SapXep(9,"很好！","Rất tốt!","nǐ hǎo ma?"));
//        list.add(new SapXep(10,"你叫什么名字？","Bạn tên là gì?","nǐ jiào shén me míng zì?"));

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.child("SapXep").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot snap : snapshot.getChildren()) {
                    SapXep sx = (SapXep) snap.getValue(SapXep.class);
                    list.add(sx);
                }
                init(listCount);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
   }

    private void init(int count) {
        SapXep sx = this.list.get(count);
        dapAn = "";
        ketQuaDung = "";
        int colorTransparent = getResources().getColor(R.color.transparent);
        tvXemDapAn.setTextColor(colorTransparent);

        tvDapAnA.setVisibility(View.VISIBLE);
        tvDapAnB.setVisibility(View.VISIBLE);
        tvDapAnC.setVisibility(View.VISIBLE);
        tvDapAnD.setVisibility(View.VISIBLE);
        tvTiengTrung.setText("");
        tvTiengViet.setText(sx.Viet);
        tvPhienAm.setText(sx.PhienAm);
        tvXemDapAn.setText(sx.Trung);
        ketQuaDung = sx.Trung;
        ArrayList<String> listAnswer = RandomSwap(Split(sx.Trung));
        selectedCount = listAnswer.size();
        if(listAnswer.size() == 4) {
            tvDapAnA.setText(listAnswer.get(0));
            tvDapAnB.setText(listAnswer.get(1));
            tvDapAnC.setText(listAnswer.get(2));
            tvDapAnD.setText(listAnswer.get(3));
        } else if (listAnswer.size()==3) {
            tvDapAnA.setText(listAnswer.get(0));
            tvDapAnB.setText(listAnswer.get(1));
            tvDapAnC.setText(listAnswer.get(2));
            tvDapAnD.setVisibility(View.INVISIBLE);
        } else if (listAnswer.size() == 2) {
            tvDapAnA.setText(listAnswer.get(0));
            tvDapAnB.setText(listAnswer.get(1));
            tvDapAnC.setVisibility(View.INVISIBLE);
            tvDapAnD.setVisibility(View.INVISIBLE);
        } else {
            tvDapAnA.setText(listAnswer.get(0));
            tvDapAnB.setVisibility(View.INVISIBLE);
            tvDapAnC.setVisibility(View.INVISIBLE);
            tvDapAnD.setVisibility(View.INVISIBLE);
        }

    }

    private  ArrayList<String> Split(String tuvung) {
        int div = tuvung.length()/4;
        int mod = tuvung.length()%4;
        ArrayList<String> list = new ArrayList<>();
        if (mod == 3) {
            int count = 3;
            for (int i = 0; i < tuvung.length(); i += div) {
                if(count != 0) {
                    list.add(tuvung.substring(i, Math.min(i + div +1, tuvung.length())));
                    i++;
                    count--;
                } else {
                    list.add(tuvung.substring(i, Math.min(i + div, tuvung.length())));
                }
            }
        } else if (mod == 2) {
            int count = 2;
            for (int i = 0; i < tuvung.length(); i += div) {
                if(count != 0) {
                    list.add(tuvung.substring(i, Math.min(i + div +1, tuvung.length())));
                    i++;
                    count--;
                } else {
                    list.add(tuvung.substring(i, Math.min(i + div, tuvung.length())));
                }
            }
        } else if (mod == 1) {
            int count = 1;
            for (int i = 0; i < tuvung.length(); i += div) {
                if (count != 0) {
                    list.add(tuvung.substring(i, Math.min(i + div + 1, tuvung.length())));
                    i++;
                    count--;
                } else {
                    list.add(tuvung.substring(i, Math.min(i + div, tuvung.length())));
                }
            }
        } else {
            for (int i = 0; i < tuvung.length(); i += div) {
                list.add(tuvung.substring(i, Math.min(i + div, tuvung.length())));
            }
        }

        return list;
    }

    private ArrayList<String> RandomSwap(ArrayList<String> arr) {
        ArrayList<String> shuffledArr = new ArrayList<>(arr); // Tạo một bản sao của danh sách gốc
        Collections.shuffle(shuffledArr); // Xáo trộn bản sao của danh sách
        return shuffledArr; // Trả về danh sách đã xáo trộn
    }
}
