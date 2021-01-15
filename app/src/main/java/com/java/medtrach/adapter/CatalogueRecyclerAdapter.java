package com.java.medtrach.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.java.medtrach.R;

public class CatalogueRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView drugName, drugDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            drugName = itemView.findViewById(R.id.drug_name_text_view);
            drugDescription = itemView.findViewById(R.id.drug_description_text_view);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
