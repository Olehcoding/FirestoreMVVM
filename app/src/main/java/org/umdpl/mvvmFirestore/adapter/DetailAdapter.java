package org.umdpl.mvvmFirestore.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.umdpl.mvvmFirestore.R;
import org.umdpl.mvvmFirestore.model.Title;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailAdapter extends RecyclerView.Adapter<DetailAdapter.ViewHolder> {
    @NonNull
    private static final String TAG = "ADAPTER";

    private List<Title> titleList = new ArrayList<>();



    @NonNull
    @Override
    public DetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
        return new DetailAdapter.ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title_tv, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailAdapter.ViewHolder holder, final int position) {
        final Title item = titleList.get(position);
        holder.tvTitle.setText(item.getTitle());
        holder.tvOrder.setText( String.valueOf(position +1) + ". ");


    }

    @Override
    public int getItemCount() {
        return titleList == null ? 0 : titleList.size();
    }


    public void setNewData(List<Title> newData) {
        titleList.clear();
        titleList.addAll(newData);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;
        private TextView tvOrder;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.title_textview);
            tvOrder = itemView.findViewById(R.id.title_order);
        }
    }

}
