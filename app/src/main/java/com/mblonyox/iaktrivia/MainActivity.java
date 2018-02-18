package com.mblonyox.iaktrivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;


import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_button)
    Button homeButton;
    String sessionToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        AppState appState = (AppState) getApplicationContext();
        sessionToken = appState.getSessionToken();
    }


    @OnClick(R.id.home_button)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, SetupActivity.class));
    }

}
