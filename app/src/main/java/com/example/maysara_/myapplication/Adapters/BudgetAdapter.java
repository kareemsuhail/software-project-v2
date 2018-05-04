package com.example.maysara_.myapplication.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.maysara_.myapplication.Activities.BudgetActivity;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.R;

import org.w3c.dom.Text;

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
        View view = inflater.inflate(R.layout.budget_item, parent,false);
        final ViewHolder holder = new ViewHolder(view);
        ImageView editBudget = (ImageView) view.findViewById(R.id.EditBudget) ;
        ImageView addCategoryToBudget = (ImageView) view.findViewById(R.id.addCategoryToBudget) ;
        ImageView deleteBudget = (ImageView) view.findViewById(R.id.deleteBudget) ;
        addCategoryToBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetActivity.moveToCategories(context, budgets.get(holder.getAdapterPosition()).getId());
            }
        });
        editBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetActivity.moveToEditBudget(context, budgets.get(holder.getAdapterPosition()).getId());
            }
        });
        deleteBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BudgetActivity.deleteBudget(context, budgets.get(holder.getAdapterPosition()).getId(),holder.getAdapterPosition());
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(budgets.get(position).getName());
        holder.balance.setText(budgets.get(position).getBalance() + "$");
        holder.startBalance.setText(budgets.get(position).getStartBalance() + "$");
        holder.startDate.setText("start date : " + budgets.get(position).getStartDate());
        holder.endDate.setText("end date : " +budgets.get(position).getEndDate());
    }

    @Override
    public int getItemCount() {
        return budgets.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView balance;
        TextView startBalance;
        TextView startDate;
        TextView endDate;
        Button showGraph;

        ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.budgetName);
            balance = itemView.findViewById(R.id.budgetBalance);
            startBalance = itemView.findViewById(R.id.budgetStartBalance);
            startDate = itemView.findViewById(R.id.startDate);
            endDate = itemView.findViewById(R.id.endDate);
            showGraph = itemView.findViewById(R.id.showGraph);
            showGraph.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                        // show graph
                }
            });
        }

    }
}
