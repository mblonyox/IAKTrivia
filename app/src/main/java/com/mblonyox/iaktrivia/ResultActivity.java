package com.mblonyox.iaktrivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ResultActivity extends AppCompatActivity {

    @BindView(R.id.result_score)
    TextView resultScore;
    @BindView(R.id.result_share_button)
    Button resultShareButton;
    @BindView(R.id.result_back_button)
    Button resultBackButton;

    AppState appState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        ButterKnife.bind(this);

        appState = (AppState) getApplicationContext();
        String score = Integer.toString(appState.getCurrentScore());
        String potential = Integer.toString(appState.getCurrentPotentialScore());

        resultScore.setText(  score + " / " + potential );
    }

    @OnClick({R.id.result_share_button, R.id.result_back_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.result_share_button:
                shareText();
                break;
            case R.id.result_back_button:
                appState.resetCurrentScore();
                finish();
                break;
        }
    }

    private void shareText() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, getText(R.string.share_subject));
        intent.putExtra(Intent.EXTRA_TEXT, String.format(getText(R.string.share_message).toString(), appState.getCurrentScore()));
        startActivity(Intent.createChooser(intent, getResources().getText(R.string.share_to)));
    }
}
