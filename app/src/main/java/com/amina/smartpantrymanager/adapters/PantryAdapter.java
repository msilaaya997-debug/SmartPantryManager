package com.amina.smartpantrymanager.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.amina.smartpantrymanager.R;
import com.amina.smartpantrymanager.models.PantryItem;

import java.util.List;

public class PantryAdapter extends RecyclerView.Adapter<PantryAdapter.PantryViewHolder> {

    private List<PantryItem> itemList;
    private final OnItemClickListener listener;

    // Interface so MainActivity can be told when a row is tapped
    public interface OnItemClickListener {
        void onItemClick(PantryItem item);
    }

    public PantryAdapter(List<PantryItem> itemList, OnItemClickListener listener) {
        this.itemList = itemList;
        this.listener = listener;
    }

    // Lets MainActivity refresh the list after add/edit/delete
    public void setItems(List<PantryItem> newItems) {
        this.itemList = newItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PantryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_pantry, parent, false);
        return new PantryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PantryViewHolder holder, int position) {
        PantryItem item = itemList.get(position);

        holder.itemName.setText(item.getName());
        holder.itemQuantity.setText(item.getQuantity() + " " + item.getUnit());

        if (item.getExpiryDate() != null && !item.getExpiryDate().isEmpty()) {
            holder.itemExpiry.setText("Exp: " + item.getExpiryDate());
        } else {
            holder.itemExpiry.setText("");
        }

        holder.itemView.setOnClickListener(v -> listener.onItemClick(item));
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    // Holds references to the views in one row (item_pantry.xml)
    static class PantryViewHolder extends RecyclerView.ViewHolder {
        TextView itemName, itemQuantity, itemExpiry;

        public PantryViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName = itemView.findViewById(R.id.itemName);
            itemQuantity = itemView.findViewById(R.id.itemQuantity);
            itemExpiry = itemView.findViewById(R.id.itemExpiry);
        }
    }
}