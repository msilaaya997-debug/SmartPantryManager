package com.amina.smartpantrymanager.database;

import com.amina.smartpantrymanager.models.PantryItem;
import com.amina.smartpantrymanager.models.Recipe;

import java.util.ArrayList;
import java.util.List;

public class RecipeMatcher {

    /**
     * Returns only the recipes that can be made using STRICTLY what's in the
     * pantry — every ingredient the recipe needs must be present. Pantry items
     * not used by a recipe are ignored (having extra items is fine).
     */
    public static List<Recipe> getMatchingRecipes(List<PantryItem> pantryItems) {
        List<Recipe> matches = new ArrayList<>();

        // Build a simple lowercase list of pantry ingredient names for easy comparing
        List<String> pantryNames = new ArrayList<>();
        for (PantryItem item : pantryItems) {
            pantryNames.add(item.getName().trim().toLowerCase());
        }

        for (Recipe recipe : RecipeRepository.getAllRecipes()) {
            boolean hasAllIngredients = true;

            for (String requiredIngredient : recipe.getIngredients()) {
                if (!pantryNames.contains(requiredIngredient.trim().toLowerCase())) {
                    hasAllIngredients = false;
                    break;
                }
            }

            if (hasAllIngredients) {
                matches.add(recipe);
            }
        }

        return matches;
    }
}