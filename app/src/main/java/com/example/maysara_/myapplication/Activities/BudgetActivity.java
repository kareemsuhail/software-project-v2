package com.example.maysara_.myapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maysara_.myapplication.Adapters.BudgetAdapter;
import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Helpers.DialogHelper;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.R;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;

public class BudgetActivity extends AppCompatActivity {

    DB_Helper db_helper;
    private RecyclerView budgetList;
    private static ArrayList<Budget> budgets;
    static BudgetAdapter adapter;
    static TextView totalBudgets;
    public static int total = 0;

    public static void moveToCategories(Context context, int budget) {
        Intent goToNextActivity = new Intent(context, CategoriesActivity.class);
        goToNextActivity.putExtra("budget", budget);
        context.startActivity(goToNextActivity);
    }
    public static void moveToEditBudget(Context context, int budget) {
        Intent goToNextActivity = new Intent(context, EditBudgetActivity.class);
        goToNextActivity.putExtra("budget", budget);
        context.startActivity(goToNextActivity);
    }
    public static void deleteBudget(Context context, int budget,int position) {
        float balanceToRemove = budgets.get(position).getBalance();
        DB_Helper db_helper = new DB_Helper(context);
        db_helper.removeBudget(budget);
        budgets.remove(position);
        adapter.notifyItemRemoved(position);
        total-=balanceToRemove;
        totalBudgets.setText(total + "$");
        Toast toast = Toast.makeText(context,"budget has been deleted",Toast.LENGTH_SHORT);
        toast.show();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.budget_layout);
        // adding remote debug bridge by kareem
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_budget);
        setSupportActionBar(toolbar);
        db_helper = new DB_Helper(this);

        budgetList = findViewById(R.id.budget_list);
        FloatingActionButton addBudget = findViewById(R.id.addBudget);


        addBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        Budget[] budgetItems = db_helper.getAllBudgets();
        budgets = new ArrayList<>(Arrays.asList(budgetItems));

        adapter = new BudgetAdapter(this, budgets);
        budgetList.setLayoutManager(new LinearLayoutManager(this));
        budgetList.setAdapter(adapter);
        totalBudgets = findViewById(R.id.totlaBudget);
        total = 0;
        for(int i =0;i<budgetItems.length;i++){
            total+=budgetItems[i].getBalance();
        }
        totalBudgets.setText(total + " $ ");
    }

    private void showDialog() {
        DialogHelper helper = new DialogHelper(this, "Create new Budget", "add_new_budget");

        helper.createBudgetDialog(budgets, budgetList,totalBudgets);
    }

    public void sss(View view) {
        Log.i("start_date","statrt date was clicked ");
    }
}
