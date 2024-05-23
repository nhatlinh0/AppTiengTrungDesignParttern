package com.example.apptiengtrung;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class KyNangGiaoTiepAdapter extends BaseAdapter {
    Context context;
    ArrayList<KyNangGiaoTiep> list;

    public KyNangGiaoTiepAdapter(Context context, ArrayList<KyNangGiaoTiep> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View convertView = View.inflate(viewGroup.getContext(), R.layout.kynanggiaotiep_item, null);
        KyNangGiaoTiep kyNangGiaoTiep =(KyNangGiaoTiep) getItem(i);
        ImageView ivImage = convertView.findViewById(R.id.ivImage);
        ivImage.setImageResource(kyNangGiaoTiep.image);
        TextView tvTitle = convertView.findViewById(R.id.tvTitle);
        tvTitle.setText(kyNangGiaoTiep.title);
        LinearLayout btnPhatAm = convertView.findViewById(R.id.btnPhatAm);
        btnPhatAm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MauCauGiaoTiepActivity.title = kyNangGiaoTiep.title;
                MauCauGiaoTiepActivity.arr = kyNangGiaoTiep.list;
                Intent intent = new Intent(context, MauCauGiaoTiepActivity.class);
                context.startActivity(intent);
            }
        });
        LinearLayout btnSapXep = convertView.findViewById(R.id.btnSapXep);
        btnSapXep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SapXepActivity.title = kyNangGiaoTiep.title;
                Intent intent = new Intent(context, SapXepActivity.class);
                context.startActivity(intent);
            }
        });
        LinearLayout btnQuiz = convertView.findViewById(R.id.btnQuiz);
        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QuizActivity.title = kyNangGiaoTiep.title;
                Intent intent = new Intent(context, QuizActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
