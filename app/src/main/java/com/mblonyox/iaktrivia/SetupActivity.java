package com.mblonyox.iaktrivia;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.mblonyox.iaktrivia.model.TriviaCategoriesItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetupActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    @BindView(R.id.spinner_category)
    Spinner spinnerCategory;
    @BindView(R.id.spinner_difficulty)
    Spinner spinnerDifficulty;
    @BindView(R.id.setup_button)
    Button setupButton;

    List<TriviaCategoriesItem> categories;
    List<String> categoryText;
    List<Integer> categoryId;
    int selectedCategory;
    String selectedDifficulty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);
        ButterKnife.bind(this);

        AppState appState = (AppState) getApplicationContext();
        categories = appState.getTriviaCategories();
        fillSpinnerCategory();
        fillSpinnerDifficulty();
    }

    private void fillSpinnerCategory() {

        categoryText = new ArrayList<String>();
        categoryId = new ArrayList<Integer>();

        categoryText.add(getString(R.string.any));
        categoryId.add(0);

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
            selectedCategory = 0;
        } else if (parent.getId() == R.id.spinner_difficulty) {
            selectedDifficulty = "any";
        }
    }

    @OnClick(R.id.setup_button)
    public void onViewClicked() {
        //TODO: Tampilkan progress bar. Jalankan request api.
    }

    //TODO: Buat method untuk request api.
}
