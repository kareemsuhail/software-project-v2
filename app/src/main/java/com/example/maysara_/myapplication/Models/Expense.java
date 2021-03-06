package com.example.maysara_.myapplication.Models;


import android.content.ContentValues;
import android.database.Cursor;

public class Expense {
    private int id;
    private String label;
    private double amount;
    private int categoryId;
    private String date;

    public Expense() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public Expense(String label, double amount, int categoryId, String date) {
        this.label = label;
        this.amount = amount;
        this.categoryId = categoryId;
        this.date = date;
    }

    public Expense(int id, String label, double amount, int categoryId, String date) {
        this.id = id;
        this.label = label;
        this.amount = amount;
        this.categoryId = categoryId;
        this.date = date;
    }

    public static Expense buildFromCursor(Cursor cursor) {
        if(cursor.isAfterLast()){
            return null;
        }
        int id = cursor.getInt(cursor.getColumnIndex("ID"));
        String label = cursor.getString(cursor.getColumnIndex("label"));
        int categoryId = cursor.getInt(cursor.getColumnIndex("categoryID"));
        String date = cursor.getString(cursor.getColumnIndex("date"));
        double amount = cursor.getDouble(cursor.getColumnIndex("amount"));
        return new Expense(id, label, amount, categoryId, date);

    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("label", this.label);
        contentValues.put("categoryId", this.categoryId);
        contentValues.put("date",this.date);
        contentValues.put("amount", this.amount);
        return contentValues;

    }

    public int getId() {
        return id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getCategory() {
        return categoryId;
    }

    public void setCategory(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
