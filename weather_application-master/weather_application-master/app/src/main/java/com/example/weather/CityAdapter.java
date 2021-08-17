package com.example.weather;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private final List<City> dataSource;
    private OnItemClickListener itemClickListener;

    CityAdapter(List<City> dataSource) {
        this.dataSource = dataSource;
    }

    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cities_list, parent, false);
        ViewHolder vh = new ViewHolder(view);

        return vh;
    }

    void SetOnItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        City item = dataSource.get(position);
        holder.city.setText(item.getName());
    }

    @Override
    public int getItemCount() {
        return dataSource.size();
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView city;

        ViewHolder(View view) {
            super(view);

            city = view.findViewById(R.id.city);
            city.setOnClickListener(view1 -> {
                if (itemClickListener != null)
                    itemClickListener.onItemClick(view1, getAdapterPosition());
            });
        }
    }
}

