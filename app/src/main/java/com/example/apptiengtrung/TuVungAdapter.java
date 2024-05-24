package com.example.apptiengtrung;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class TuVungAdapter extends BaseAdapter {
    Context context;
    ArrayList<TuVung> list;

    public TuVungAdapter (Context context, ArrayList<TuVung> list) {
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
        TuVung tuVung = (TuVung) getItem(i);
        TextView tvTuVungTrung = (TextView) convertView.findViewById(R.id.tvTuVungTrung);
        tvTuVungTrung.setText(tuVung.Trung);
        TextView tvphienAm = (TextView) convertView.findViewById(R.id.tvPhienAm);
        tvphienAm.setText(tuVung.PhienAm);
        TextView tvTuVungViet = (TextView) convertView.findViewById(R.id.tvTuVungViet);
        tvTuVungViet.setText(tuVung.Viet);
        if(ListUser.checkTeacher(LoginActivity.username) || ListUser.checkAdmin(LoginActivity.username)) {
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, CRUDTuVungActivity.class);
                    context.startActivity(intent);
                    CRUDTuVungActivity.id = tuVung.id;
                    CRUDTuVungActivity.tieuDeTrung = tuVung.Trung;
                    CRUDTuVungActivity.tieuDeViet = tuVung.Viet;
                    CRUDTuVungActivity.phienAm = tuVung.PhienAm;
                }
            });
        }
        return convertView;
    }

    public void setTuVungList(List<TuVung> newTuVungList) {
        list.clear();
        list.addAll(newTuVungList);
        notifyDataSetChanged();
    }
}
