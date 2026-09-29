package com.amina.smartpantrymanager;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.amina.smartpantrymanager.database.RecipeRepository;
import com.amina.smartpantrymanager.models.Recipe;

public class RecipeDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        TextView recipeTitle = findViewById(R.id.recipeTitle);
        TextView recipeIngredients = findViewById(R.id.recipeIngredients);
        TextView recipeSteps = findViewById(R.id.recipeSteps);

        int recipeId = getIntent().getIntExtra("recipe_id", -1);
        Recipe recipe = RecipeRepository.getRecipeById(recipeId);

        if (recipe != null) {
            recipeTitle.setText(recipe.getName());
            recipeIngredients.setText("Ingredients:\n" + String.join(", ", recipe.getIngredients()));
            recipeSteps.setText("Steps:\n" + recipe.getSteps());
        }
    }
}