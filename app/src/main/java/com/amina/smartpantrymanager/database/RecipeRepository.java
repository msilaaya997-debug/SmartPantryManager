package com.amina.smartpantrymanager.database;

import com.amina.smartpantrymanager.models.Recipe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RecipeRepository {

    public static List<Recipe> getAllRecipes() {
        List<Recipe> recipes = new ArrayList<>();

        recipes.add(new Recipe(1, "Scrambled Eggs",
                Arrays.asList("egg", "milk", "butter", "salt"),
                "1. Whisk eggs and milk together.\n2. Melt butter in a pan.\n3. Pour in egg mixture, stir gently over low heat until set.\n4. Season with salt."));

        recipes.add(new Recipe(2, "Tomato Pasta",
                Arrays.asList("pasta", "tomato", "garlic", "olive oil", "salt"),
                "1. Boil pasta until al dente.\n2. Saute garlic in olive oil.\n3. Add chopped tomato, simmer 10 minutes.\n4. Toss pasta in sauce, season with salt."));

        recipes.add(new Recipe(3, "Grilled Cheese Sandwich",
                Arrays.asList("bread", "cheese", "butter"),
                "1. Butter one side of each bread slice.\n2. Place cheese between the unbuttered sides.\n3. Grill in a pan until golden on both sides."));

        recipes.add(new Recipe(4, "Vegetable Stir Fry",
                Arrays.asList("carrot", "broccoli", "onion", "soy sauce", "garlic"),
                "1. Chop all vegetables.\n2. Saute garlic and onion in a hot pan.\n3. Add carrot and broccoli, stir fry 5-7 minutes.\n4. Add soy sauce, toss and serve."));

        recipes.add(new Recipe(5, "Banana Pancakes",
                Arrays.asList("banana", "egg", "flour", "milk"),
                "1. Mash banana in a bowl.\n2. Whisk in egg and milk.\n3. Fold in flour until just combined.\n4. Cook spoonfuls on a hot greased pan until golden."));

        return recipes;
    }

    public static Recipe getRecipeById(int id) {
        for (Recipe recipe : getAllRecipes()) {
            if (recipe.getId() == id) {
                return recipe;
            }
        }
        return null;
    }
}