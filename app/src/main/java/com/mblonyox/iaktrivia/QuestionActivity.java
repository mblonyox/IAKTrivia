package com.mblonyox.iaktrivia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import org.apache.commons.lang3.StringEscapeUtils;

import com.mblonyox.iaktrivia.model.ResultsItem;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuestionActivity extends AppCompatActivity {

    @BindView(R.id.question_number)
    TextView questionNumber;
    @BindView(R.id.question_text)
    TextView questionText;

    Integer currentIndex;
    ResultsItem currentQuestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        ButterKnife.bind(this);

        currentIndex = getIntent().getIntExtra("index", 0);
        AppState appState = (AppState) getApplicationContext();
        currentQuestion = appState.getTriviaQuestions().get(currentIndex);

        questionNumber.setText( Integer.toString(currentIndex + 1) );
        questionText.setText(
                StringEscapeUtils.unescapeHtml4(currentQuestion.getQuestion()));
    }


}
