package com.example.apptiengtrung;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class BoThuHanThuAdapter extends BaseAdapter {
    Context context;
    List<BoThuHanThu> list;

    public BoThuHanThuAdapter (Context context, ArrayList<BoThuHanThu> list) {
        this.context = context;
        this.list = list;
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
        View convertView = View.inflate(viewGroup.getContext(), R.layout.tuvung_item, null);
        BoThuHanThu bo = (BoThuHanThu) getItem(i);
        TextView tvTuVungTrung = (TextView) convertView.findViewById(R.id.tvTuVungTrung);
        tvTuVungTrung.setText(bo.Trung);
        TextView tvphienAm = (TextView) convertView.findViewById(R.id.tvPhienAm);
        tvphienAm.setText(bo.PhienAm);
        TextView tvTuVungViet = (TextView) convertView.findViewById(R.id.tvTuVungViet);
        tvTuVungViet.setText(bo.Viet);
        return convertView;
    }

    public void updateData(List<BoThuHanThu> newData) {
        this.list = newData;
        notifyDataSetChanged();
    }
}
