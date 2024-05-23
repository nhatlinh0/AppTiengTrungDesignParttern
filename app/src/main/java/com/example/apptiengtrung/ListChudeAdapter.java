package com.example.apptiengtrung;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.apptiengtrung.ListChude;
import com.example.apptiengtrung.R;

import java.util.ArrayList;

public class ListChudeAdapter extends RecyclerView.Adapter<ListChudeAdapter.viewholder> {
    ArrayList<ListChude> items;
    Context context;
    public  ListChudeAdapter(ArrayList<ListChude> items){
        this.items=items;

    }
    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       context=parent.getContext();

        return new viewholder(LayoutInflater.from(context).inflate(R.layout.viewhold_list_chude,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull viewholder holder, int position) {
        holder.caunoi.setText(items.get(position).getCaunoi());
        holder.phienam.setText(items.get(position).getPhienam());
        holder.dichthuat.setText(items.get(position).getDichthuat());

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView caunoi,phienam,dichthuat;
        public viewholder(@NonNull View itemView) {
            super(itemView);
            caunoi=itemView.findViewById(R.id.caunoi);
            phienam=itemView.findViewById(R.id.phienam);
            dichthuat=itemView.findViewById(R.id.dichnghia);
        }
    }
}
