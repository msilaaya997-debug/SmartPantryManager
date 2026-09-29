package com.amina.smartpantrymanager.models;

import java.util.List;

public class Recipe {
    private int id;
    private String name;
    private List<String> ingredients; // e.g. ["egg", "flour", "milk"]
    private String steps;

    public Recipe(int id, String name, List<String> ingredients, String steps) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public List<String> getIngredients() { return ingredients; }
    public String getSteps() { return steps; }
}