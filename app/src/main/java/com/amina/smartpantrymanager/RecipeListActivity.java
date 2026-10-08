package com.amina.smartpantrymanager;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amina.smartpantrymanager.adapters.RecipeAdapter;
import com.amina.smartpantrymanager.database.DatabaseHelper;
import com.amina.smartpantrymanager.database.RecipeMatcher;
import com.amina.smartpantrymanager.models.PantryItem;
import com.amina.smartpantrymanager.models.Recipe;

import java.util.List;

public class RecipeListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_list);

        // Set up RecyclerView
        RecyclerView recyclerViewRecipes =
                findViewById(R.id.recyclerViewRecipes);

        recyclerViewRecipes.setLayoutManager(
                new LinearLayoutManager(this)
        );

        // Get pantry items from the SQLite database
        DatabaseHelper databaseHelper = new DatabaseHelper(this);
        List<PantryItem> pantryItems = databaseHelper.getAllItems();

        // Find recipes that match the pantry items
        List<Recipe> matchingRecipes =
                RecipeMatcher.getMatchingRecipes(pantryItems);

        // Create the recipe adapter
        RecipeAdapter adapter =
                new RecipeAdapter(this, matchingRecipes);

        // Display the matching recipes
        recyclerViewRecipes.setAdapter(adapter);
    }
}