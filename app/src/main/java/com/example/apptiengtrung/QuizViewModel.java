package com.example.apptiengtrung;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;

public class QuizViewModel extends AndroidViewModel {
    private final MutableLiveData<ArrayList<Quiz>> quizList = new MutableLiveData<>();
    private final MutableLiveData<Integer> score = new MutableLiveData<>(0);
    private final MutableLiveData<Integer> currentQuestionIndex = new MutableLiveData<>(0);
    private final MutableLiveData<Boolean> quizCompleted = new MutableLiveData<>(false);

    public QuizViewModel(@NonNull Application application) {
        super(application);
        loadQuizData();
    }

    public LiveData<ArrayList<Quiz>> getQuizList() {
        return quizList;
    }

    public LiveData<Integer> getScore() {
        return score;
    }

    public LiveData<Integer> getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public LiveData<Boolean> isQuizCompleted() {
        return quizCompleted;
    }

    private void loadQuizData() {
        ArrayList<Quiz> list = new ArrayList<>();
        // Add your quiz data here
        list.add(new Quiz("Xin chào", "Xin chào", "Ai kia?", "Bạn là ai?", "Bạn khỏe không?", getApplication().getResources().getIdentifier("hello", "raw", getApplication().getPackageName())));
        list.add(new Quiz("Chờ tôi", "Vui mừng được gặp các bạn", "Xin mời vào", "Đi với tôi", "Chờ tôi", getApplication().getResources().getIdentifier("wait", "raw", getApplication().getPackageName())));
        list.add(new Quiz("Bạn đến từ đâu","Cậu nói quá nhanh","Bạn đến từ đâu","Hãy nói chậm hơn","Có ai ở đây nói Tiếng Trung không? ",getApplication().getResources().getIdentifier("whereyoufrom", "raw", getApplication().getPackageName())));
        list.add(new Quiz("Xin mời vào","Bạn muốn gì?","Tôi muốn gặp bạn","Xin mời vào","Bạn khỏe không?",getApplication().getResources().getIdentifier("welcome", "raw", getApplication().getPackageName())));
        list.add(new Quiz("Hãy nhắc lại","Hãy nhắc lại","Tôi muốn gặp bạn","Xin mời vào","Đi với tôi",getApplication().getResources().getIdentifier("repeat", "raw", getApplication().getPackageName())));
        list.add(new Quiz("Tôi nghĩ vậy","Tôi nghĩ vậy","Tôi biết","Dường như với tôi","Tôi đã quên mất",getApplication().getResources().getIdentifier("ithinkso", "raw", getApplication().getPackageName())));
        list.add(new Quiz("Hẹn gặp lại sau nhé","Tôi không có thời gian","Chồng tôi không có ở nhà","Ngồi đây.","Hẹn gặp lại sau nhé",getApplication().getResources().getIdentifier("bye", "raw", getApplication().getPackageName())));
        list.add(new Quiz("Tôi đang vội"," Hẹn gặp lại bạn","Tôi đang vội","Chúc may mắn.","Xin lỗi",getApplication().getResources().getIdentifier("imbusy", "raw", getApplication().getPackageName())));
        list.add(new Quiz("Cảm ơn","Xin lỗi, tôi nghe không rõ","Cảm ơn","Mời uống nước","Tôi hiểu rồi",getApplication().getResources().getIdentifier("thanks", "raw", getApplication().getPackageName())));
        list.add(new Quiz("Bạn bao nhiêu tuổi?","Thật tuyệt","Bạn làm rất tốt đấy","Bạn bao nhiêu tuổi?","Tôi muốn gặp bạn",getApplication().getResources().getIdentifier("howold", "raw", getApplication().getPackageName())));

        quizList.setValue(list);
    }

    public void answerQuestion(String answer) {
        ArrayList<Quiz> list = quizList.getValue();
        Integer  index = currentQuestionIndex.getValue();
        if (list != null && index != null && index < list.size()) {
            Quiz currentQuiz = list.get(index);
            if (currentQuiz.DapAnDung.equals(answer)) {
                score.setValue(score.getValue() + 5);
            } else {
                score.setValue(score.getValue() - 5);
            }
            if (index < list.size() - 1) {
                currentQuestionIndex.setValue(index + 1);
            } else {
                quizCompleted.setValue(true);
            }
        }
    }
}
