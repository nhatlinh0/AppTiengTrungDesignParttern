package com.example.apptiengtrung;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChuDeAdapter extends BaseAdapter {
    Context context;
    ArrayList<ChuDe> list;

    public ChuDeAdapter(Context context, ArrayList<ChuDe> list) {
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
        View convertView = View.inflate(viewGroup.getContext(), R.layout.chude_item, null);
        ChuDe chuDe =(ChuDe) getItem(i);
        ImageView imgChuDe = convertView.findViewById(R.id.imgChuDe);
        imgChuDe.setImageResource(ChuDeActivity.abc[i]);
        TextView tvChuDeTrung = convertView.findViewById(R.id.tvChuDeTrung);
        tvChuDeTrung.setText(chuDe.TieuDeTrung);
        TextView tvChuDeViet = convertView.findViewById(R.id.tvChuDeViet);
        tvChuDeViet.setText(chuDe.TieuDeViet);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TuVungActivity.ChuDe = chuDe.TieuDeViet;
                Intent intent = new Intent(context, TuVungActivity.class);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
