package com.mblonyox.iaktrivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.mblonyox.iaktrivia.http.OpenTriviaAPI;
import com.mblonyox.iaktrivia.http.OpenTriviaService;
import com.mblonyox.iaktrivia.model.ResponseToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.home_button)
    Button homeButton;
    String sessionToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        requestSessionToken();
    }

    private void requestSessionToken() {
        OpenTriviaService client = OpenTriviaAPI.getClient();
        Call<ResponseToken> requestToken = client.requestToken();
        requestToken.enqueue(new Callback<ResponseToken>() {
            @Override
            public void onResponse(Call<ResponseToken> call, Response<ResponseToken> response) {
                Log.d("", response.body().getResponseMessage());
                if(response.body().getResponseCode() == 0) {
                    sessionToken = response.body().getToken();
                }
                Toast.makeText(
                        MainActivity.this,
                        response.body().getResponseMessage(),
                        Toast.LENGTH_SHORT
                );
            }

            @Override
            public void onFailure(Call<ResponseToken> call, Throwable t) {
                Log.d("", t.getMessage());
                Toast.makeText(
                        MainActivity.this,
                        t.getMessage(),
                        Toast.LENGTH_SHORT
                );
            }
        });
    }

    @OnClick(R.id.home_button)
    public void onViewClicked() {
        startActivity(new Intent(MainActivity.this, SetupActivity.class));
    }
}
