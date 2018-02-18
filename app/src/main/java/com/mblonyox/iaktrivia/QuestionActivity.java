package com.mblonyox.iaktrivia;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.mblonyox.iaktrivia.adapter.QuestionAdapter;
import com.mblonyox.iaktrivia.model.ResultsItem;

import org.apache.commons.lang3.StringEscapeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity {

    @BindView(R.id.question_number)
    TextView questionNumber;
    @BindView(R.id.question_text)
    TextView questionText;
    @BindView(R.id.question_rv)
    RecyclerView questionRv;
    @BindView(R.id.question_difficulty)
    TextView questionDifficulty;

    AppState appState;
    Integer currentIndex;
    ResultsItem currentQuestion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

        currentIndex = getIntent().getIntExtra("index", 0);
        appState = (AppState) getApplicationContext();
        currentQuestion = appState.getTriviaQuestions().get(currentIndex);

        questionNumber.setText(Integer.toString(currentIndex + 1));
        questionDifficulty.setText(currentQuestion.getDifficulty());
        questionText.setText(StringEscapeUtils.unescapeHtml4(currentQuestion.getQuestion()));

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        questionRv.setLayoutManager(layoutManager);

        QuestionAdapter adapter = new QuestionAdapter(
                this,
                currentQuestion.getIncorrectAnswers(),
                currentQuestion.getCorrectAnswer()
        );

        questionRv.setAdapter(adapter);
    }

    public void onAnswer(String answer) {
        int score;
        switch (currentQuestion.getDifficulty()) {
            case "easy":
                score = 10;
                break;
            case "medium":
                score = 20;
                break;
            case "hard":
                score = 50;
                break;
            default:
                score = 0;
                break;
        }
        if (answer.equals(currentQuestion.getCorrectAnswer())) {
            showDialog(true);
            appState.increaseCurrentScore(score, true);
        } else {
            showDialog(false);
            appState.increaseCurrentScore(score, false);
        }
    }

    private void showDialog(boolean correct) {
        CharSequence message;
        int icon;
        if(correct) {
            message = getText(R.string.answer_is_correct);
            icon = R.drawable.ic_check;
        } else {
            message = getText(R.string.answer_is_wrong);
            icon = R.drawable.ic_close;
        }
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(R.string.answer);
        alertDialogBuilder.setIcon(icon);
        alertDialogBuilder.setMessage(message);
        alertDialogBuilder.setPositiveButton(R.string.next_question, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                nextAnswer();
            }
        });
        alertDialogBuilder.create().show();
    }

    private void nextAnswer() {
        AppState appState = (AppState) getApplicationContext();
        Intent next;
        int total = appState.getTriviaQuestions().size();
        if(currentIndex < (total - 1)) {
            next = new Intent(this, QuestionActivity.class);
            next.putExtra("index", currentIndex + 1);
        } else {
            next = new Intent(this, ResultActivity.class);
        }

        startActivity(next);
        finish();
    }

}
