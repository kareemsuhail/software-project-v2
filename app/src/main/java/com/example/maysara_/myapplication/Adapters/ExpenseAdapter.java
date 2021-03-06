package com.example.maysara_.myapplication.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maysara_.myapplication.Activities.ExpenseActivity;
import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Models.Expense;
import com.example.maysara_.myapplication.R;

import java.util.ArrayList;


public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.ViewHolder> {

    private Context context;
    private ArrayList<Expense> expenses;
    private DB_Helper helper;

    public ExpenseAdapter(Context context, ArrayList<Expense> expenses){
        this.context = context;
        this.expenses = expenses;
        this.helper = new DB_Helper(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.expense_item,parent,false );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.label.setText(expenses.get(position).getLabel());
        holder.amount.setText("Amount: " + expenses.get(position).getAmount()+"");
        holder.date.setText("Date: "+expenses.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return expenses.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {
        TextView label;
        TextView amount;
        TextView date;

        ViewHolder(View itemView) {
            super(itemView);
            label = itemView.findViewById(R.id.expenseLabel);
            amount = itemView.findViewById(R.id.expenseAmount);
            date = itemView.findViewById(R.id.expenseDate);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public boolean onLongClick(View view) {
            Toast.makeText(context, "alright", Toast.LENGTH_SHORT).show();
            ((ExpenseActivity)context).showOptionsButtons();
            ((ExpenseActivity)context).selectedItemPosition = getAdapterPosition();
            return false;
        }
    }

}
