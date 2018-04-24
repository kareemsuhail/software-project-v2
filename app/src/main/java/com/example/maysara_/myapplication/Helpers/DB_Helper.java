package com.example.maysara_.myapplication.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.maysara_.myapplication.Activities.BudgetActivity;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.Models.Expense;
import com.example.maysara_.myapplication.Models.User;

import java.util.ArrayList;

public class DB_Helper extends SQLiteOpenHelper implements queries {
    private static final int DB_VERSION = 5;
    private static final String DB_NAME = "cashDB.db";
    private static final String[] BUDGET_TABLE_COLUMNS = {"ID", "name", "startDate", "endDate", "startBalance", "balance"};
    private static final String[] CATEGORY_TABLE_COLUMNS = {"ID", "name", "budgetID", "limitAmount"};
    private static final String[] EXPENSE_TABLE_COLUMNS = {"ID", "label", "amount", "categoryID", "date"};
    private static final String[] USER_TABLE_COLUMNS = {"ID", "name", "phone", "gender"};

    public DB_Helper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE budget(ID INTEGER PRIMARY KEY,name TEXT UNIQUE,startDate TEXT,endDate TEXT,startBalance REAL, balance REAL)");
        db.execSQL("CREATE TABLE category(ID INTEGER PRIMARY KEY,name TEXT UNIQUE,budgetID INTEGER ,limitAmount REAl ,FOREIGN KEY(budgetID) REFERENCES budget(ID) ON DELETE CASCADE)");
        db.execSQL("CREATE TABLE expense(ID INTEGER PRIMARY KEY,label TEXT UNIQUE,categoryID INTEGER ,date TEXT ,amount REAl ,FOREIGN KEY(categoryID) REFERENCES category(ID) ON DELETE CASCADE)");
        db.execSQL("CREATE TABLE User(ID INTEGER PRIMARY KEY,name TEXT UNIQUE,phone TEXT, gender REAL)");
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        db.execSQL("PRAGMA foreign_keys = ON;");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS user ");
        db.execSQL("DROP TABLE IF EXISTS expense ");
        db.execSQL("DROP TABLE IF EXISTS category ");
        db.execSQL("DROP TABLE IF EXISTS budget ");
        this.onCreate(db);
    }


    @Override
    public User getUser(int id) {

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("user", USER_TABLE_COLUMNS, "ID = ?", new String[]{id + ""}, null, null, null);
        cursor.moveToFirst();
        User temp = User.buildFromCursor(cursor);

        return User.buildFromCursor(cursor);
    }

    @Override
    public void saveUser(User user) {
        ContentValues contentValues = user.getContentValues();
        SQLiteDatabase db = getWritableDatabase();

        db.insert("user", null, contentValues);

    }

    @Override
    public Budget[] getAllBudgets() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QueryBuilder.selectAll("budget"), null);
        ArrayList<Budget> budgets = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            budgets.add(Budget.buildFromCursor(cursor));
            cursor.moveToNext();
        }
        Budget[] budgetsArr = new Budget[budgets.size()];
        budgetsArr = budgets.toArray(budgetsArr);
        return budgetsArr;
    }

    @Override
    public Category[] getCategoriesForBudget(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("category", CATEGORY_TABLE_COLUMNS, "budgetID = ?", new String[]{id + ""}, null, null, null);
        ArrayList<Category> categories = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categories.add(Category.buildFromCursor(cursor));
            cursor.moveToNext();
        }
        Category[] categoriesArr = new Category[categories.size()];
        categoriesArr = categories.toArray(categoriesArr);
        return categoriesArr;
    }


    @Override
    public Budget getBudget(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("budget", BUDGET_TABLE_COLUMNS, "ID = ?", new String[]{id + ""}, null, null, null);
        cursor.moveToFirst();


        return Budget.buildFromCursor(cursor);

    }

    @Override
    public void createBudget(Budget budget) {
        try {
            ContentValues contentValues = budget.getContentValues();
            SQLiteDatabase db = getWritableDatabase();
            db.insert("budget", null, contentValues);
        }catch (Exception ex){

        }

    }

    @Override
    public Budget removeBudget(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("budget", BUDGET_TABLE_COLUMNS, "ID = ?", new String[]{id + ""}, null, null, null);
        cursor.moveToFirst();
        Budget temp = Budget.buildFromCursor(cursor);
        db.delete("budget", "ID = ?", new String[]{id + ""});
        return temp ;
    }

    @Override
    public void updateBudget(Budget budget) {
        int id = budget.getId();

        SQLiteDatabase db = getReadableDatabase();

        db.update("budget", budget.getContentValues(), "ID"+ " = ?",
                new String[] { String.valueOf(budget.getId()) });


    }

    @Override
    public void createCategory(Category category) {
        try {
            ContentValues contentValues = category.getContentValues();
            SQLiteDatabase db = getWritableDatabase();
            db.insert("category", null, contentValues);
        }catch (Exception ex){

        }


    }

    @Override
    public Category[] getAllCategories() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QueryBuilder.selectAll("category"), null);
        ArrayList<Category> categories = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            categories.add(Category.buildFromCursor(cursor));
            cursor.moveToNext();
        }
        Category[] categoriesArr = new Category[categories.size()];
        categoriesArr = categories.toArray(categoriesArr);
        return categoriesArr;
    }

    @Override
    public Category getCategory(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("category", CATEGORY_TABLE_COLUMNS, "ID = ?", new String[]{id + ""}, null, null, null);
        cursor.moveToFirst();
        return Category.buildFromCursor(cursor);

    }

    @Override
    public Category removeCategory(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("category", CATEGORY_TABLE_COLUMNS, "ID = ?", new String[]{id + ""}, null, null, null);
        cursor.moveToFirst();
        db.delete("category", "ID = ?", new String[]{id + ""});
        return Category.buildFromCursor(cursor);
    }

    @Override
    public void createExpense(Expense expense) {
        try {
            ContentValues contentValues = expense.getContentValues();
            SQLiteDatabase db = getWritableDatabase();
            db.insert("expense", null, contentValues);
        }catch (Exception ex){

        }

    }

    @Override
    public Expense[] getAllExpenses() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(QueryBuilder.selectAll("expense"), null);
        ArrayList<Expense> expenses = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            expenses.add(Expense.buildFromCursor(cursor));
            cursor.moveToNext();
        }
        Expense[] expensesArr = new Expense[expenses.size()];
        expensesArr = expenses.toArray(expensesArr);
        return expensesArr;
    }

    @Override
    public Expense getExpense(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("expense", EXPENSE_TABLE_COLUMNS, "ID = ?", new String[]{id + ""}, null, null, null);
        cursor.moveToFirst();
        return Expense.buildFromCursor(cursor);
    }

    @Override
    public Expense removeExpense(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("expense", EXPENSE_TABLE_COLUMNS, "ID = ?", new String[]{id + ""}, null, null, null);
        cursor.moveToFirst();
        db.delete("expense", "ID = ?", new String[]{id + ""});
        return Expense.buildFromCursor(cursor);
    }

    @Override
    public Expense[] getAllExpensesForCategory(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("expense", EXPENSE_TABLE_COLUMNS, "categoryID = ?", new String[]{id + ""}, null, null, null);
        ArrayList<Expense> expenses = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            expenses.add(Expense.buildFromCursor(cursor));
            cursor.moveToNext();
        }
        Expense[] expensesArr = new Expense[expenses.size()];
        expensesArr = expenses.toArray(expensesArr);
        return expensesArr;

    }

    public void clearDB() {
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DROP TABLE IF EXISTS user ");
        db.execSQL("DROP TABLE IF EXISTS expense ");
        db.execSQL("DROP TABLE IF EXISTS category ");
        db.execSQL("DROP TABLE IF EXISTS budget ");
        db.execSQL("CREATE TABLE budget(ID INTEGER PRIMARY KEY,name TEXT,startDate TEXT,endDate TEXT,startBalance REAL, balance REAL)");
        db.execSQL("CREATE TABLE category(ID INTEGER PRIMARY KEY,name TEXT,budgetID INTEGER ,limitAmount REAl ,FOREIGN KEY(budgetID) REFERENCES budget(ID) ON DELETE CASCADE)");
        db.execSQL("CREATE TABLE expense(ID INTEGER PRIMARY KEY,label TEXT,categoryID INTEGER ,date TEXT ,amount REAl ,FOREIGN KEY(categoryID) REFERENCES category(ID) ON DELETE CASCADE)");
        db.execSQL("CREATE TABLE User(ID INTEGER PRIMARY KEY,name TEXT,phone TEXT, gender REAL)");
    }


}
