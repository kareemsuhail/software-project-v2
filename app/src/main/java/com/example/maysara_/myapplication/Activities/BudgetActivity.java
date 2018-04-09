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
import android.view.View;

import com.example.maysara_.myapplication.Adapters.BudgetAdapter;
import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Helpers.DialogHelper;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.R;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.orm.SugarContext;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;

public class BudgetActivity extends AppCompatActivity {

    DB_Helper db_helper;
    private RecyclerView budgetList;
    private ArrayList<Budget> budgets;

    public static void moveToCategories(Context context, int budget) {
        Intent goToNextActivity = new Intent(context, CategoriesActivity.class);
        goToNextActivity.putExtra("budget", budget);
        context.startActivity(goToNextActivity);
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
        SugarContext.init(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Budgets");
        setSupportActionBar(toolbar);

        db_helper = new DB_Helper(this);

        budgetList = findViewById(R.id.budget_list);
        FloatingActionButton addBudget = findViewById(R.id.addBudget);

        budgets = new ArrayList<>(Arrays.asList(db_helper.getAllBudgets()));

        BudgetAdapter adapter = new BudgetAdapter(this, budgets);
        budgetList.setLayoutManager(new LinearLayoutManager(this));
        budgetList.setAdapter(adapter);

        addBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    private void showDialog() {
        DialogHelper helper = new DialogHelper(this, "Create new Budget", "add_new_budget");
        helper.createBudgetDialog(budgets, budgetList);
    }
}
