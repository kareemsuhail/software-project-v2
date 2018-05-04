package com.example.maysara_.myapplication.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.maysara_.myapplication.Adapters.ExpenseAdapter;
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
    private DB_Helper db_helper;
    private ArrayList<Expense> expenses;
    private RecyclerView expensesList;
    ExpenseAdapter adapter ;
    private int categoryID;
    public int selectedItemPosition = -1 ;
    Toolbar toolbar ;
    private  boolean  IS_ITEM_SELECTED = false ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        Stetho.initializeWithDefaults(this);
        new OkHttpClient.Builder()
                .addNetworkInterceptor(new StethoInterceptor())
                .build();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        db_helper = new DB_Helper(this);
        expensesList = findViewById(R.id.expense_list);
        FloatingActionButton addExpense = findViewById(R.id.addExpense);
        categoryID = (getIntent()).getIntExtra("category", -1);
        Toast.makeText(this, "id"+categoryID, Toast.LENGTH_SHORT).show();
        expenses = new ArrayList<>(Arrays.asList(db_helper.getAllExpensesForCategory(categoryID)));

        adapter = new ExpenseAdapter(this,expenses);
        expensesList.setLayoutManager(new LinearLayoutManager(this));
        expensesList.setAdapter(adapter);

        addExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
            }
        });

    }

    private void showDialog() {
        DialogHelper helper = new DialogHelper(this, "Create new Expense", "add_new_expense");
        helper.createExpensesDialog(expenses,expensesList,categoryID);
    }

    public void showOptionsButtons()
    {
        Toast.makeText(this, "hello its me", Toast.LENGTH_SHORT).show();
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.items_options);
        IS_ITEM_SELECTED = true ;
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setBackgroundColor(this.getResources().getColor(R.color.colorAccent));
        setTitle("");
    }

    private void showMainButtons()
    {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_main);
        IS_ITEM_SELECTED = false;
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        toolbar.setBackgroundColor(this.getResources().getColor(R.color.colorPrimary));
        selectedItemPosition = -1 ;
        setTitle("hello");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.deleteExpenseItem :
            {
                deleteExpense(selectedItemPosition);
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if (toolbar!=null&&IS_ITEM_SELECTED)
            showMainButtons();
        else
            super.onBackPressed();

    }

    private void deleteExpense(int position){
        int id = expenses.get(position).getId();
        expenses.remove(position);
        adapter.notifyItemRemoved(position);
        db_helper.removeExpense(id);
    }
}
