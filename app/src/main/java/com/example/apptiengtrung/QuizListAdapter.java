package com.example.apptiengtrung;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.apptiengtrung.QuizHskActivity;
import com.example.apptiengtrung.databinding.QuizItemRecyclerRowBinding;


import java.util.List;

public class QuizListAdapter extends RecyclerView.Adapter<QuizListAdapter.MyViewHolder> {

    private final List<QuizModel> quizModelList;

    public QuizListAdapter(List<QuizModel> quizModelList) {
        this.quizModelList = quizModelList;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private final QuizItemRecyclerRowBinding binding;

        public MyViewHolder(QuizItemRecyclerRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void bind(QuizModel model) {
            binding.quizTitleText.setText(model.getTitle());
            binding.quizSubtitleText.setText(model.getSubtitle());
            binding.quizTimeText.setText(model.getTime() + " min");
            binding.getRoot().setOnClickListener(v -> {
                Intent intent = new Intent(binding.getRoot().getContext(), QuizHskActivity.class);
                QuizHskActivity.questionModelList = model.getQuestionList();
                QuizHskActivity.time = model.getTime();
                binding.getRoot().getContext().startActivity(intent);
            });
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        QuizItemRecyclerRowBinding binding = QuizItemRecyclerRowBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public int getItemCount() {
        return quizModelList.size();
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.bind(quizModelList.get(position));
    }
}