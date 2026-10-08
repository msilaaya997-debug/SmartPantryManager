# Smart Pantry Manager

## Overview

Smart Pantry Manager is an Android mobile application developed to help users manage their pantry ingredients and identify recipes that can be prepared using the ingredients they currently have.

The application allows users to add, edit, view, and delete pantry items. It stores pantry information using an SQLite database and uses a recipe-matching feature to identify recipes that can be prepared from the available pantry ingredients.

## Features

- Add pantry ingredients
- Edit pantry ingredients
- Delete pantry ingredients
- View all pantry items
- Store pantry data using SQLite
- Match available pantry ingredients with recipes
- Display matching recipes
- View recipe ingredients and preparation steps
- Navigate between pantry and recipe screens

## Recipe Matching

The application uses strict recipe matching. A recipe is displayed as a match only when all of its required ingredients are available in the user's pantry.

For example, if a recipe requires:

- Egg
- Milk
- Butter
- Salt

the recipe will only be displayed when all four ingredients are available in the pantry.

Additional pantry items that are not required by the recipe do not prevent the recipe from being matched.

## Technologies Used

- Java
- Android Studio
- Android SDK
- SQLite
- XML layouts
- RecyclerView
- Android Activities

## Database

The application uses SQLite to store pantry items.

The pantry database stores information including:

- Item ID
- Item name
- Quantity
- Unit
- Expiry date

## Main Application Screens

The application includes:

1. Pantry List
2. Add/Edit Pantry Item
3. Matching Recipes
4. Recipe Details

## Project Structure

The project is organised into different packages for models, database functionality, adapters, and activities.

Important components include:

- DatabaseHelper
- PantryItem
- Recipe
- RecipeRepository
- RecipeMatcher
- PantryAdapter
- RecipeAdapter
- RecipeListActivity
- RecipeDetailActivity
- AddEditItemActivity
- MainActivity

## How to Run the Application

1. Clone or download the repository.
2. Open the project in Android Studio.
3. Allow Android Studio to synchronise the Gradle files.
4. Connect an Android device or start an emulator.
5. Run the application from Android Studio.
6. Add pantry ingredients and use the recipe matching feature.

## GitHub Repository

This repository contains the source code and project files for the Smart Pantry Manager Android application.

Developed as part of the Mobile App Development 700 assignment.
