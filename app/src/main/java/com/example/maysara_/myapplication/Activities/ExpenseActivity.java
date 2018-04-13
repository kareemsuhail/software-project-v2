package com.example.maysara_.myapplication.Activities;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Helpers.DialogHelper;
import com.example.maysara_.myapplication.Models.Expense;
import com.example.maysara_.myapplication.R;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;

public class ExpenseActivity extends AppCompatActivity {
    private DB_Helper db_helper ;
    private ArrayList<Expense> expenses;
    private RecyclerView expensesList;
    private int categoryID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("expenses");
        setSupportActionBar(toolbar);
        db_helper = new DB_Helper(this);
        expensesList = findViewById(R.id.expenses_list);
        FloatingActionButton addExpense = findViewById(R.id.addExpense);
        categoryID = (getIntent()).getIntExtra("category", -1);
        expenses = new ArrayList<>(Arrays.asList(db_helper.getAllExpensesForCategory(categoryID)));



    }
    private void showDialog() {

    }
}
