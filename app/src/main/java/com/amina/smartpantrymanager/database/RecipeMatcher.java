package com.amina.smartpantrymanager.database;

import android.util.Log;

import com.amina.smartpantrymanager.models.PantryItem;
import com.amina.smartpantrymanager.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeMatcher {

    public static List<Recipe> getMatchingRecipes(List<PantryItem> pantryItems) {
        List<Recipe> matches = new ArrayList<>();

        List<String> pantryNames = new ArrayList<>();
        for (PantryItem item : pantryItems) {
            pantryNames.add(item.getName().trim().toLowerCase());
        }

        Log.d("RecipeMatcher", "Pantry contains: " + pantryNames);

        for (Recipe recipe : RecipeRepository.getAllRecipes()) {
            boolean hasAllIngredients = true;

            for (String requiredIngredient : recipe.getIngredients()) {
                if (!pantryNames.contains(requiredIngredient.trim().toLowerCase())) {
                    hasAllIngredients = false;
                    break;
                }
            }

            Log.d("RecipeMatcher", recipe.getName() + " needs " + recipe.getIngredients() + " -> match: " + hasAllIngredients);

            if (hasAllIngredients) {
                matches.add(recipe);
            }
        }

        return matches;
    }
}