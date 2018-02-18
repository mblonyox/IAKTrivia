package com.mblonyox.iaktrivia;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Spinner;

import com.mblonyox.iaktrivia.http.OpenTriviaAPI;
import com.mblonyox.iaktrivia.model.ResponseTrivia;
import com.mblonyox.iaktrivia.model.TriviaCategoriesItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SetupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.spinner_category)
    Spinner spinnerCategory;
    @BindView(R.id.spinner_difficulty)
    Spinner spinnerDifficulty;
    @BindView(R.id.setup_button)
    Button setupButton;
    @BindView(R.id.setup_progress)
    RelativeLayout setupProgress;

    List<TriviaCategoriesItem> categories;
    List<String> categoryText;
    List<Integer> categoryId;
    Integer selectedCategory;
    String selectedDifficulty;
    String sessionToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        ButterKnife.bind(this);

        AppState appState = (AppState) getApplicationContext();
        categories = appState.getTriviaCategories();
        sessionToken = appState.getSessionToken();
        fillSpinnerCategory();
        fillSpinnerDifficulty();
    }

    private void fillSpinnerCategory() {

        categoryText = new ArrayList<String>();
        categoryId = new ArrayList<Integer>();

        categoryText.add(getString(R.string.any));
        categoryId.add(null);

        for (int i = 0; i < categories.size(); i++) {
            TriviaCategoriesItem item = categories.get(i);
            categoryText.add(item.getName());
            categoryId.add(item.getId());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                categoryText
        );

        spinnerCategory.setAdapter(adapter);
    }

    private void fillSpinnerDifficulty() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.difficulty_levels,
                R.layout.support_simple_spinner_dropdown_item
        );

        spinnerDifficulty.setAdapter(adapter);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int i, long l) {
        if (parent.getId() == R.id.spinner_category) {
            selectedCategory = categoryId.get(i);
        } else if (parent.getId() == R.id.spinner_difficulty) {
            selectedDifficulty = parent.getSelectedItem().toString().toLowerCase();
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        if (parent.getId() == R.id.spinner_category) {
            selectedCategory = null;
        } else if (parent.getId() == R.id.spinner_difficulty) {
            selectedDifficulty = "any";
        }
    }

    @OnClick(R.id.setup_button)
    public void onViewClicked() {
        setupProgress.setVisibility(View.VISIBLE);
        if(selectedDifficulty != null && selectedDifficulty.equals("any")) {
            selectedDifficulty = null;
        }
        OpenTriviaAPI.getClient()
                .getTrivia(
                        10,
                        selectedCategory,
                        selectedDifficulty,
                        sessionToken
                )
                .enqueue(new Callback<ResponseTrivia>() {
                    @Override
                    public void onResponse(Call<ResponseTrivia> call, Response<ResponseTrivia> response) {
                        setupProgress.setVisibility(View.GONE);
                        if(response.body().getResponseCode() == 0) {
                            AppState appState = (AppState) getApplicationContext();
                            appState.setTriviaQuestions(response.body().getResults());
                            startActivity(new Intent(getApplicationContext(), QuestionActivity.class));
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseTrivia> call, Throwable t) {
                        setupProgress.setVisibility(View.GONE);
                    }
                });
    }

    //TODO: Buat method untuk request api.
}
