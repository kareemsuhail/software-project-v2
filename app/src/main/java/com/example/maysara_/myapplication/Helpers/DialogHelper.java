package com.example.maysara_.myapplication.Helpers;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.Models.Expense;
import com.example.maysara_.myapplication.R;
import com.example.maysara_.myapplication.fragments.DatePickerFragment;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.InputMismatchException;

import java.util.GregorianCalendar;
import java.util.Locale;


public class DialogHelper  {

    private Context context;
    private Dialog dialog;
    private DB_Helper db_helper;
    private SimpleDateFormat dateFormat  ;

    public DialogHelper(Context context, String title, String layout) {
        this.context = context;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dialog = new Dialog(context);
        int layoutId = context.getResources().getIdentifier(layout, "layout", context.getPackageName());
        dialog.setContentView(layoutId);
        dialog.setTitle(title);
        db_helper = new DB_Helper(context);
    }

    public DialogHelper(Context context) {
        this.context = context;
    }

    public static void displayError(String message, Context context) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    public static boolean validateInput(EditText text1, EditText text2) {
        return text1.length() != 0 && text2.length() != 0;
    }

    public void createBudgetDialog(final ArrayList<Budget> budgets, final RecyclerView budgetList) {
        Button dialogButton = dialog.findViewById(R.id.addNewBudget);
        final TextView startDateText = (TextView)dialog.findViewById(R.id.start_date);
        final TextView endDateText = (TextView)dialog.findViewById(R.id.end_date);
        Calendar calendar = Calendar.getInstance();
        Log.i("calender",calendar.YEAR+"");
        DatePickerDialog.OnDateSetListener listener_for_start_date = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {

                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR,y);
                c.set(Calendar.MONTH,m);
                c.set(Calendar.DAY_OF_MONTH,d);
                Log.i("month",""+c.getTime());
                String formatted_date = dateFormat.format(c.getTime());
                startDateText.setText(formatted_date);
            }
        };
        DatePickerDialog.OnDateSetListener listener_for_end_date = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                Calendar c = Calendar.getInstance();
                c.set(Calendar.YEAR,y);
                c.set(Calendar.MONTH,m);
                c.set(Calendar.DAY_OF_MONTH,d);
                String formatted_date = dateFormat.format(c.getTime());
               endDateText.setText(formatted_date);
            }
        };
        final DatePickerDialog datePickerDialog1 = new DatePickerDialog(
                context, listener_for_start_date,calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH), calendar.DAY_OF_MONTH);
        final DatePickerDialog datePickerDialog2 = new DatePickerDialog(
                context, listener_for_end_date, calendar.get(Calendar.YEAR) ,calendar.get(Calendar.MONTH), calendar.DAY_OF_MONTH);
        startDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog1.show();
            }

        });
        endDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                datePickerDialog2.show();
            }

        });
        dialogButton.setOnClickListener(new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                EditText nameEd = dialog.findViewById(R.id.newBudgetName);
                EditText balanceEd = dialog.findViewById(R.id.newBudgetBalance);
                TextView startDateText = (TextView)dialog.findViewById(R.id.start_date);
                TextView endDateText = (TextView)dialog.findViewById(R.id.end_date);
                if (!validateInput(nameEd, balanceEd)) {
                    DialogHelper.displayError("Please enter valid data", context);
                    return;
                }
                String name = nameEd.getText().toString();
                int balance = Integer.parseInt(balanceEd.getText().toString());
                String start_date = startDateText.getText().toString();
                String end_date = endDateText.getText().toString();
                Log.i("start_date","start date is: "+start_date);
                try {
                    Date start_date_date = dateFormat.parse(start_date);
                    Date end_date_date  =dateFormat.parse(end_date);
                    if(start_date_date.after(end_date_date)){
                        throw new InputMismatchException();
                    }
                } catch (ParseException e) {
                    DialogHelper.displayError("Please enter valid date", context);
                    return;
                } catch (InputMismatchException e){
                    DialogHelper.displayError("Please end date should be after start date", context);
                    return;
                }
                Budget newBudget = new Budget(name, start_date, end_date, balance, balance);
                db_helper.createBudget(newBudget);
                budgets.add(newBudget);
                budgetList.invalidate();
                budgetList.getAdapter().notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    public void editBudgetDialog(final ArrayList<Budget> budgets, final RecyclerView budgetList) {
        Button dialogButton = dialog.findViewById(R.id.EditBudget);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEd = dialog.findViewById(R.id.newBudgetName);
                EditText balanceEd = dialog.findViewById(R.id.newBudgetBalance);
                EditText startDateText = dialog.findViewById(R.id.start_date);
                EditText endDateText = dialog.findViewById(R.id.end_date);
                if (!validateInput(nameEd, balanceEd)) {
                    DialogHelper.displayError("Please enter valid data", context);
                    return;
                }
                String name = nameEd.getText().toString();
                int balance = Integer.parseInt(balanceEd.getText().toString());
                String start_date = startDateText.getText().toString();
                String end_date = endDateText.getText().toString();
                Log.i("start_date","start date is: "+start_date);
                try {
                    new SimpleDateFormat("dd/mm/yy").parse(start_date);
                    new SimpleDateFormat("dd/mm/yy").parse(end_date);
                    Budget newBudget = new Budget(name, start_date, end_date, balance, balance);
                    db_helper.createBudget(newBudget);
                    budgets.add(newBudget);
                    budgetList.invalidate();
                    budgetList.getAdapter().notifyDataSetChanged();
                    dialog.dismiss();
                } catch (ParseException e) {
                    DialogHelper.displayError("Please enter valid date", context);
                    return;
                }
                Budget newBudget = new Budget(name, start_date, end_date, balance, balance);
                db_helper.createBudget(newBudget);
                budgets.add(newBudget);
                budgetList.invalidate();
                budgetList.getAdapter().notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void createCategoryDialog(final ArrayList<Category> categories, final RecyclerView categoryList, final int budgetId) {
        Button dialogButton = dialog.findViewById(R.id.addNewCategory);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText nameEd = dialog.findViewById(R.id.newCategoryName);
                EditText limitEd = dialog.findViewById(R.id.newCategoryLimit);
                if (!validateInput(nameEd, limitEd)) {
                    DialogHelper.displayError("Please enter valid data", context);
                    return;
                }
                String name = nameEd.getText().toString();
                int limit = Integer.parseInt(limitEd.getText().toString());
                Category newCategory = new Category(name, budgetId, limit);
                db_helper.createCategory(newCategory);
                categories.add(newCategory);
                categoryList.invalidate();
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    public void createExpensesDialog(final ArrayList<Expense> expenses, final RecyclerView expensesList, final int categoryId) {
        Button dialogButton = dialog.findViewById(R.id.addNewExpense);
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText expenseLabel = dialog.findViewById(R.id.newExpenselabel);
                EditText expenseAmount = dialog.findViewById(R.id.newExpenseAmount);
                String expenseNameString = expenseLabel.getText().toString();
                double expenseAmountValue = Double.parseDouble(expenseAmount.getText().toString());
                String expenseDate = new SimpleDateFormat("yyyy-MM-dd",   Locale.getDefault()).format(new Date());
                Expense newExpense = new Expense(expenseNameString,expenseAmountValue,categoryId,expenseDate);
                db_helper.createExpense(newExpense);
                expenses.add(newExpense);
                expensesList.invalidate();
                expensesList.getAdapter().notifyDataSetChanged();
                dialog.dismiss();
            }
        });
        dialog.show();

    }

    public void showErrorMessage() {

    }


}
