package com.example.maysara_.myapplication.Models;


import android.content.ContentValues;
import android.database.Cursor;

public class Category {

    private int id;
    private String name;
    private int budgetId;
    private int limitAmount;

    public Category() {

    }

    public Category(String name, int budget, int limitAmount) {
        this.name = name;
        this.budgetId = budget;
        this.limitAmount = limitAmount;
    }

    public Category(int id, String name, int budgetId, int limitAmount) {
        this.id = id;
        this.name = name;
        this.budgetId = budgetId;
        this.limitAmount = limitAmount;
    }

    public static Category buildFromCursor(Cursor cursor) {
        int id = cursor.getInt(cursor.getColumnIndex("ID"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        int budgetId = cursor.getInt(cursor.getColumnIndex("budgetID"));
        int limitAmount = cursor.getInt(cursor.getColumnIndex("limitAmount"));
        return new Category(id, name, budgetId, limitAmount);

    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", this.name);
        contentValues.put("budgetId", this.budgetId);
        contentValues.put("limitAmount", this.limitAmount);
        return contentValues;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBudgetID() {
        return budgetId;
    }

    public void setBudgetID(int budgetId) {
        this.budgetId = budgetId;
    }

    public int getLimit() {
        return limitAmount;
    }

    public void setLimit(int limitAmount) {
        this.limitAmount = limitAmount;
    }
}
