package com.example.maysara_.myapplication.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maysara_.myapplication.Activities.CategoriesActivity;
import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.Models.Expense;
import com.example.maysara_.myapplication.R;

import java.util.ArrayList;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Category> categories;
    private DB_Helper db_helper;

    public CategoryAdapter(Context context, ArrayList<Category> categories) {
        this.context = context;
        this.categories = categories;
        db_helper = new DB_Helper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_item, parent,false);
        final CategoryAdapter.ViewHolder holder = new CategoryAdapter.ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.name.setText(categories.get(position).getName());
        holder.limit.setText("limit : "+categories.get(position).getLimit() + " $ ");
        Expense[] expenses = db_helper.getAllExpensesForCategory(categories.get(holder.getAdapterPosition()).getId());
        int total = 0;
        for(int i =0;i<expenses.length;i++){
            total+=expenses[i].getAmount();
        }
        holder.totalExpense.setText("spent : "+total+ " $ ");
        holder.deleteCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoriesActivity.deleteCategory(context, categories.get(holder.getAdapterPosition()).getId(),holder.getAdapterPosition());
            }
        });
        holder.addExpenseToCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CategoriesActivity.MoveToExpenses(context,categories.get(holder.getAdapterPosition()).getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        TextView limit;
        TextView totalExpense;
        ImageView deleteCategory;
        ImageView addExpenseToCategory;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.categoryName);
            limit = itemView.findViewById(R.id.categoryLimit);
            totalExpense = itemView.findViewById(R.id.categoryExpense);
            deleteCategory = itemView.findViewById(R.id.deleteCategory);
            addExpenseToCategory = itemView.findViewById(R.id.addExpenseToCategory);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
        }
    }
}
