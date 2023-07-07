package com.example.fetch;

import androidx.recyclerview.widget.RecyclerView;
import androidx.annotation.NonNull;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;
import android.content.Context;
import android.widget.TextView;

import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {

    Context context;
    List<ItemModel> itemList;

    public ItemAdapter(Context context, List<ItemModel> itemlist) {
        this.context = context;
        this.itemList = itemlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if(itemList != null && itemList.size() > 0) {
            ItemModel model1 = itemList.get(position);
            holder.listId_tv.setText(model1.getlistId());
            holder.name_tv.setText(model1.getName());
            holder.id_tv.setText(model1.getId());
        } else {
            return;
        }
    }
    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView listId_tv, name_tv, id_tv;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            listId_tv = itemView.findViewById(R.id.listId_tv);
            name_tv = itemView.findViewById(R.id.name_tv);
            id_tv = itemView.findViewById(R.id.id_tv);

        }
    }
}
