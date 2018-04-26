package com.example.maysara_.myapplication.Activities;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Helpers.DialogHelper;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EditBudgetActivity extends AppCompatActivity {
    private int budgetId ;
    private Budget budget;
    private EditText editBudgetName;
    private TextView editBudgeEndDate ;
    private DatePickerDialog datePickerDialog;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private Calendar calendar ;
    private SimpleDateFormat dateFormat  ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_budget);
        budgetId = (getIntent()).getIntExtra("budget", -1);
        editBudgetName = (EditText)findViewById(R.id.editBudgetName);
        editBudgeEndDate = (TextView)findViewById(R.id.edit_end_date);
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.calendar = Calendar.getInstance();
        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                calendar.set(Calendar.YEAR,y);
                calendar.set(Calendar.MONTH,m);
                calendar.set(Calendar.DAY_OF_MONTH,d);
                String formatted_date = dateFormat.format(calendar.getTime());
                editBudgeEndDate.setText(formatted_date);
            }
        };
        datePickerDialog = new DatePickerDialog(
                this, dateSetListener,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.DAY_OF_MONTH);
        this.getBudget();
    }
    private void getBudget(){
        DB_Helper db_helper = new DB_Helper(this);
        budget = db_helper.getBudget(budgetId);

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

    public void showDatePickerDialog(View view) {
        datePickerDialog.show();
    }
}
