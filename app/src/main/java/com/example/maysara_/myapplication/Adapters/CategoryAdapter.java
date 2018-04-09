package com.example.maysara_.myapplication.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Category> categories;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_item, null);
        final CategoryAdapter.ViewHolder holder = new CategoryAdapter.ViewHolder(view);
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                CategoriesActivity.MoveToExpenses(context,categories.get(holder.getAdapterPosition()).getId());
//            }
//        });
        return holder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(categories.get(position).getName());
        holder.limit.setText(categories.get(position).getLimit() + "$");
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView limit;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.categoryName);
            limit = itemView.findViewById(R.id.categoryLimit);
        }

    }
}
