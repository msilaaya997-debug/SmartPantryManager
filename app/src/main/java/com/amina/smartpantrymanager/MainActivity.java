package com.amina.smartpantrymanager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amina.smartpantrymanager.adapters.PantryAdapter;
import com.amina.smartpantrymanager.database.DatabaseHelper;
import com.amina.smartpantrymanager.models.PantryItem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TextView emptyStateText;
    private PantryAdapter adapter;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHelper = new DatabaseHelper(this);

        recyclerView = findViewById(R.id.recyclerViewPantry);
        emptyStateText = findViewById(R.id.emptyStateText);
        FloatingActionButton fabAddItem = findViewById(R.id.fabAddItem);
        View btnFindRecipes = findViewById(R.id.btnFindRecipes);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Set up adapter with an empty list for now — loadPantryItems() will fill it
        adapter = new PantryAdapter(dbHelper.getAllItems(), item -> {
            // Tapping a row opens AddEditItemActivity in "edit" mode
            Intent intent = new Intent(MainActivity.this, AddEditItemActivity.class);
            intent.putExtra("item_id", item.getId());
            startActivity(intent);
        });
        recyclerView.setAdapter(adapter);

        // FAB opens AddEditItemActivity in "add" mode (no item_id passed)
        fabAddItem.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, AddEditItemActivity.class);
            startActivity(intent);
        });

        // Button opens RecipeListActivity
        btnFindRecipes.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
            startActivity(intent);
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadPantryItems(); // refresh list every time we come back to this screen
    }

    private void loadPantryItems() {
        List<PantryItem> items = dbHelper.getAllItems();
        adapter.setItems(items);

        if (items.isEmpty()) {
            emptyStateText.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            emptyStateText.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
        }
    }
}