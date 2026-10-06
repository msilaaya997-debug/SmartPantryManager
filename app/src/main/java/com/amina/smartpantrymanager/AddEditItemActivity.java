package com.amina.smartpantrymanager;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.amina.smartpantrymanager.database.DatabaseHelper;
import com.amina.smartpantrymanager.models.PantryItem;

import java.util.Calendar;
import java.util.Locale;

public class AddEditItemActivity extends AppCompatActivity {

    private EditText editName, editQuantity, editUnit, editExpiry;
    private Button btnSave, btnDelete;
    private DatabaseHelper dbHelper;

    private int itemId = -1;
    private boolean isEditMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_item);

        dbHelper = new DatabaseHelper(this);

        editName = findViewById(R.id.editName);
        editQuantity = findViewById(R.id.editQuantity);
        editUnit = findViewById(R.id.editUnit);
        editExpiry = findViewById(R.id.editExpiry);
        btnSave = findViewById(R.id.btnSave);
        btnDelete = findViewById(R.id.btnDelete);

        if (getIntent().hasExtra("item_id")) {
            itemId = getIntent().getIntExtra("item_id", -1);
            isEditMode = true;
            loadItemData();
            btnDelete.setVisibility(android.view.View.VISIBLE);
        }

        editExpiry.setOnClickListener(v -> showDatePicker());
        btnSave.setOnClickListener(v -> saveItem());
        btnDelete.setOnClickListener(v -> deleteItem());
    }

    private void loadItemData() {
        PantryItem item = dbHelper.getItemById(itemId);
        if (item != null) {
            editName.setText(item.getName());
            editQuantity.setText(String.valueOf(item.getQuantity()));
            editUnit.setText(item.getUnit());
            editExpiry.setText(item.getExpiryDate());
        }
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    String formattedDate = String.format(Locale.getDefault(),
                            "%04d-%02d-%02d", selectedYear, selectedMonth + 1, selectedDay);
                    editExpiry.setText(formattedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    private void saveItem() {
        String name = editName.getText().toString().trim();
        String quantityStr = editQuantity.getText().toString().trim();
        String unit = editUnit.getText().toString().trim();
        String expiry = editExpiry.getText().toString().trim();

        if (name.isEmpty()) {
            editName.setError("Please enter an ingredient name");
            return;
        }
        if (quantityStr.isEmpty()) {
            editQuantity.setError("Please enter a quantity");
            return;
        }

        double quantity = Double.parseDouble(quantityStr);

        if (isEditMode) {
            PantryItem item = new PantryItem(itemId, name, quantity, unit, expiry);

            dbHelper.updateItem(item);
            Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
        } else {
            PantryItem item = new PantryItem(name, quantity, unit, expiry);
            dbHelper.addItem(item);
            Toast.makeText(this, "Item added", Toast.LENGTH_SHORT).show();
        }

        finish();
    }

    private void deleteItem() {
        dbHelper.deleteItem(itemId);
        Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
        finish();
    }
}
