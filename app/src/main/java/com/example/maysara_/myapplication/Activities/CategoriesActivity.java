package com.example.maysara_.myapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.maysara_.myapplication.Adapters.CategoryAdapter;
import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Helpers.DialogHelper;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.R;
import com.facebook.stetho.Stetho;
import com.facebook.stetho.okhttp3.StethoInterceptor;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.OkHttpClient;

public class CategoriesActivity extends AppCompatActivity {

    static ArrayList<Category> categories;
    int budgetId;
    DB_Helper db_helper;
    private RecyclerView categoryList;
    static CategoryAdapter adapter;

    public static void MoveToExpenses(Context context, int category) {
        Intent goToNextActivity = new Intent(context, ExpenseActivity.class);
        goToNextActivity.putExtra("category", category);
        context.startActivity(goToNextActivity);
    }

    public static void deleteCategory(Context context, int category,int position) {
        DB_Helper db_helper = new DB_Helper(context);
        db_helper.removeCategory(category);
        categories.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.category_layout);
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db_helper = new DB_Helper(this);
        budgetId = (getIntent()).getIntExtra("budget", -1);

        categoryList = findViewById(R.id.category_list);
        FloatingActionButton addCategory = findViewById(R.id.addCategory);


        addCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();

        categories = new ArrayList<>(Arrays.asList(db_helper.getCategoriesForBudget(budgetId)));
        adapter = new CategoryAdapter(this, categories);
        categoryList.setLayoutManager(new GridLayoutManager(this, 2));
        categoryList.setAdapter(adapter);
    }

    private void showDialog() {
        DialogHelper helper = new DialogHelper(this, "Create new Category", "add_new_category");
        helper.createCategoryDialog(categories, categoryList, budgetId);
    }


}
