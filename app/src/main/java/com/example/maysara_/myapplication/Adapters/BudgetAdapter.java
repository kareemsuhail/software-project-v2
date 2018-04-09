package com.example.maysara_.myapplication.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maysara_.myapplication.Activities.BudgetActivity;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.R;

import java.util.ArrayList;

public class BudgetAdapter extends RecyclerView.Adapter<BudgetAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Budget> budgets;

    public BudgetAdapter(Context context, ArrayList<Budget> budgets) {
        this.context = context;
        this.budgets = budgets;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.budget_item, null);
        final ViewHolder holder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetActivity.moveToCategories(context,  budgets.get(holder.getAdapterPosition()).getId().intValue());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(budgets.get(position).getName());
        holder.balance.setText(budgets.get(position).getBalance() + "$");
    }

    @Override
    public int getItemCount() {
        return budgets.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView balance;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.budgetName);
            balance = itemView.findViewById(R.id.budgetBalance);
        }

    }
}
