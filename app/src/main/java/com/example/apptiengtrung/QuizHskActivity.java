package com.example.apptiengtrung;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;


import com.example.apptiengtrung.databinding.ActivityMainOneBinding;
import com.example.apptiengtrung.databinding.ActivityQuizBinding;
import com.example.apptiengtrung.databinding.ScoreDialogBinding;
import com.squareup.picasso.Picasso;

import java.util.List;

public class QuizHskActivity extends AppCompatActivity implements View.OnClickListener {

    public static List<QuestionModel> questionModelList;
    public static String time;

    private ActivityQuizBinding binding;

    private int currentQuestionIndex = 0;
    private String selectedAnswer = "";
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuizBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btn0.setOnClickListener(this);
        binding.btn1.setOnClickListener(this);
        binding.btn2.setOnClickListener(this);
        binding.btn3.setOnClickListener(this);
        binding.nextBtn.setOnClickListener(this);

        loadQuestions();
        startTimer();
    }

    public void startTimer() {
        long totalTimeInMillis = Integer.parseInt(time) * 60 * 1000L;
        new CountDownTimer(totalTimeInMillis, 1000L) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished / 1000;
                long minutes = seconds / 60;
                long remainingSeconds = seconds % 60;
                binding.timerIndicatorTextview.setText(String.format("%02d:%02d", minutes, remainingSeconds));
            }

            @Override
            public void onFinish() {
                // Finish the quiz
            }
        }.start();
    }

    public void loadQuestions() {
        selectedAnswer = "";
        if (currentQuestionIndex == questionModelList.size()) {
            finishQuiz();
            return;
        }

        QuestionModel currentQuestion = questionModelList.get(currentQuestionIndex);
        binding.questionIndicatorTextview.setText("Question " + (currentQuestionIndex + 1) + "/ " + questionModelList.size());
        binding.questionProgressIndicator.setProgress((int) ((currentQuestionIndex / (float) questionModelList.size()) * 100));
        binding.questionTextview.setText(currentQuestion.getQuestion());
        binding.btn0.setText(currentQuestion.getOptions().get(0));
        binding.btn1.setText(currentQuestion.getOptions().get(1));
        binding.btn2.setText(currentQuestion.getOptions().get(2));
        binding.btn3.setText(currentQuestion.getOptions().get(3));

        String imagePath = currentQuestion.getImagePath();
        Log.d("ImagePath", "Image path: " + imagePath);
        if (imagePath != null && !imagePath.isEmpty()) {
            Picasso.get().load(imagePath).into(binding.questionImageView);
            binding.questionImageView.setVisibility(View.VISIBLE); // Ensure ImageView is visible if image is loaded
        } else {
            binding.questionImageView.setVisibility(View.GONE); // Hide ImageView if imagePath is empty
        }
    }

    @Override
    public void onClick(View view) {
        binding.btn0.setBackgroundColor(getColor(R.color.gray));
        binding.btn1.setBackgroundColor(getColor(R.color.gray));
        binding.btn2.setBackgroundColor(getColor(R.color.gray));
        binding.btn3.setBackgroundColor(getColor(R.color.gray));

        Button clickedBtn = (Button) view;
        if (clickedBtn.getId() == R.id.next_btn) {
            // next button is clicked
            if (selectedAnswer.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please select answer to continue", Toast.LENGTH_SHORT).show();
                return;
            }
            if (selectedAnswer.equals(questionModelList.get(currentQuestionIndex).getCorrect())) {
                score++;
                Log.i("Score of quiz", String.valueOf(score));
            }
            currentQuestionIndex++;
            loadQuestions();
        } else {
            // options button is clicked
            selectedAnswer = clickedBtn.getText().toString();
            clickedBtn.setBackgroundColor(getColor(R.color.orange));
        }
    }

    public void finishQuiz() {
        int totalQuestions = questionModelList.size();
        int percentage = (int) ((score / (float) totalQuestions) * 100);

        ScoreDialogBinding dialogBinding = ScoreDialogBinding.inflate(getLayoutInflater());
        dialogBinding.scoreProgressIndicator.setProgress(percentage);
        dialogBinding.scoreProgressText.setText(percentage + " %");
        if (percentage > 60) {
            dialogBinding.scoreTitle.setText("Congrats! You have passed");
            dialogBinding.scoreTitle.setTextColor(Color.BLUE);
        } else {
            dialogBinding.scoreTitle.setText("Oops! You have failed");
            dialogBinding.scoreTitle.setTextColor(Color.RED);
        }
        dialogBinding.scoreSubtitle.setText(score + " out of " + totalQuestions + " are correct");
        dialogBinding.finishBtn.setOnClickListener(v -> finish());

        new AlertDialog.Builder(this)
                .setView(dialogBinding.getRoot())
                .setCancelable(false)
                .show();
    }
}
