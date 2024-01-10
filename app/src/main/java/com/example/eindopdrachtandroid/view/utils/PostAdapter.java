package com.example.eindopdrachtandroid.view.utils;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eindopdrachtandroid.R;
import com.example.eindopdrachtandroid.model.Post;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    class PostViewHolder extends RecyclerView.ViewHolder{



        final TextView tvAddress;

        final CardView row;

        final TextView tvDistrict;

        final TextView tvType;

        public PostViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvDistrict = itemView.findViewById(R.id.tv_district);
            tvType = itemView.findViewById(R.id.tv_type);
            row = itemView.findViewById(R.id.card);

            row.setOnClickListener((View v) -> {
                Post current = PostItems.get(getAdapterPosition());
                Bundle mBundle = new Bundle();
                mBundle.putSerializable("details", current);

                Navigation.findNavController(itemView).navigate(R.id.action_list_to_detailsFragment, mBundle);
            });
        }
    }

    private List<Post> PostItems;

    public PostAdapter(){ this.PostItems = new ArrayList<>();
    }

    public void newItems(List<Post> newItems){
        PostItems = newItems;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_post_recycler, parent, false);
        return new PostViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        Post mPost = PostItems.get(position);
        holder.tvAddress.setText(mPost.getAdres());
        holder.tvDistrict.setText(mPost.getDistrict());
        holder.tvType.setText(mPost.getType());
    }

    @Override
    public int getItemCount() {
        return PostItems.size();
    }
}
