package com.example.maysara_.myapplication.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Helpers.DialogHelper;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class EditBudgetActivity extends AppCompatActivity {
    private int budgetId ;
    private Budget budget;
    private EditText editBudgetName;
    private EditText editBudgeEndDate ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_budget);
        budgetId = (getIntent()).getIntExtra("budget", -1);
        this.getBudget();
    }
    private void getBudget(){
        DB_Helper db_helper = new DB_Helper(this);
        budget = db_helper.getBudget(budgetId);
        editBudgetName = (EditText)findViewById(R.id.editBudgetName);
        editBudgeEndDate = (EditText)findViewById(R.id.edit_end_date);
        editBudgetName.setText(budget.getName());
        editBudgeEndDate.setText(budget.getEndDate());


    }

    public void EditBudget(View view) {
        String newName = editBudgetName.getText().toString();
        String endDate = editBudgeEndDate.getText().toString();
        try {
            new SimpleDateFormat("dd/mm/yy").parse(endDate);

        } catch (ParseException e) {
            Toast toast = Toast.makeText(this,"please enter a valid date",Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        budget.setName(newName);
        budget.setEndDate(endDate);
        DB_Helper db_helper = new DB_Helper(this);
        db_helper.updateBudget(budget);
        Intent intent = new Intent(this, BudgetActivity.class);
        this.startActivity(intent);


    }
}
