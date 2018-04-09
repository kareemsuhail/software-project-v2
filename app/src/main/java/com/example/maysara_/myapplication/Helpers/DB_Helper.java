package com.example.maysara_.myapplication.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.Models.User;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class DB_Helper extends SQLiteOpenHelper implements queries {
    private static  final int DB_VERSION  = 1;
    private static  final String DB_NAME = "cashDB.db";
    private static  final String[] BUDGET_TABLE_COLUMNS = {"ID","name","startDate","endDate","startBalance","balance"};
    private static  final String[] CATEGORY_TABLE_COLUMNS = {"ID","name","budgetID","limit"};
    private static  final String[] EXPENSE_TABLE_COLUMNS = {"ID","label","amount","categoryID","date"};
    private static  final String[] USER_TABLE_COLUMNS = {"ID","name","phone","gender"};

    public DB_Helper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE budget(ID INTEGER PRIMARY KEY,name TEXT,startDate TEXT,endDate TEXT,startBalance REAL, balance REAL)");
        db.execSQL("CREATE TABLE category(ID INTEGER PRIMARY KEY,name TEXT,budgetID INTEGER ,limitAmount REAl ,FOREIGN KEY(budgetID) REFERENCES budget(ID))");
        db.execSQL("CREATE TABLE expense(ID INTEGER PRIMARY KEY,label TEXT,categoryID INTEGER ,amount REAl ,FOREIGN KEY(categoryID) REFERENCES category(ID))");
        db.execSQL("CREATE TABLE User(ID INTEGER PRIMARY KEY,name TEXT,phone TEXT, gender REAL)");
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
        Cursor cursor = db.query("user", BUDGET_TABLE_COLUMNS, "ID = ?", new String[] {id+"" }, null, null, null);
        cursor.moveToFirst();
        return User.buildFromCursor(cursor);
    }

    @Override
    public void saveUser(User user) {
        ContentValues contentValues = user.getContentValues();
        SQLiteDatabase db = getWritableDatabase();
        db.insert("user",null,contentValues);

    }

    @Override
    public Budget[] getAllBudgets() {
        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor = db.rawQuery(QueryBuilder.selectAll("budget"),null);
        ArrayList<Budget> budgets = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            budgets.add(Budget.buildFromCursor(cursor));
            cursor.moveToNext();
        }
        Budget[] budgetsArr = new Budget[budgets.size()];
        budgetsArr = budgets.toArray(budgetsArr);
        return budgetsArr ;
    }

    @Override
    public Category[] getCategoriesForBudget(int id) {
            return new Category[]{};
    }


    @Override
    public Budget getBudget(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("budget", BUDGET_TABLE_COLUMNS, "ID = ?", new String[] {id+"" }, null, null, null);
        cursor.moveToFirst();
        return Budget.buildFromCursor(cursor);

    }

    @Override
    public void createBudget(Budget budget) {
        ContentValues contentValues = budget.getContentValues();
        SQLiteDatabase db = getWritableDatabase();
        db.insert("budget",null,contentValues);
    }

    @Override
    public Budget removeBudget(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("budget", BUDGET_TABLE_COLUMNS, "ID = ?", new String[] {id+"" }, null, null, null);
        cursor.moveToFirst();
        db.delete("budget", "ID = ?", new String[]{id+""});
        return Budget.buildFromCursor(cursor);
    }

    @Override
    public void createCategory(Category category) {
        ContentValues contentValues = category.getContentValues();
        SQLiteDatabase db = getWritableDatabase();
        db.insert("category",null,contentValues);

    }

    @Override
    public Category[] getAllCategories() {
        SQLiteDatabase db =getReadableDatabase();
        Cursor cursor = db.rawQuery(QueryBuilder.selectAll("category"),null);
        ArrayList<Category> categories = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            categories.add(Category.buildFromCursor(cursor));
            cursor.moveToNext();
        }
        Category[] categoriesArr = new Category[categories.size()];
        categoriesArr = categories.toArray(categoriesArr);
        return categoriesArr ;
    }

    @Override
    public Category getCategory(int id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("category", BUDGET_TABLE_COLUMNS, "ID = ?", new String[] {id+"" }, null, null, null);
        cursor.moveToFirst();
        return Category.buildFromCursor(cursor);

    }

    @Override
    public Category removeCategory(int id) {
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("category", BUDGET_TABLE_COLUMNS, "ID = ?", new String[] {id+"" }, null, null, null);
        cursor.moveToFirst();
        db.delete("category", "ID = ?", new String[]{id+""});
        return Category.buildFromCursor(cursor);
    }


}
