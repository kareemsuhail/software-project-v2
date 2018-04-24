package com.example.maysara_.myapplication.Models;

import android.content.ContentValues;
import android.database.Cursor;

public class Budget {
    private int id;
    private String name;
    private String startDate;
    private String endDate;
    private float startBalance;
    private float balance;

    public Budget() {

    }

    public Budget(String name, String startDate, String endDate, float startBalance, float balance) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startBalance = startBalance;
        this.balance = balance;
    }

    public Budget(int id, String name, String startDate, String endDate, float startBalance, float balance) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startBalance = startBalance;
        this.balance = balance;
    }

    public static Budget buildFromCursor(Cursor cursor) {
        if(cursor.isAfterLast()){
           return null;
        }
        int id = cursor.getInt(cursor.getColumnIndex("ID"));
        String name = cursor.getString(cursor.getColumnIndex("name"));
        String startDate = cursor.getString(cursor.getColumnIndex("startDate"));
        String endDate = cursor.getString(cursor.getColumnIndex("endDate"));
        float startBalance = cursor.getFloat(cursor.getColumnIndex("startBalance"));
        float balance = cursor.getFloat(cursor.getColumnIndex("balance"));
        return new Budget(id, name, startDate, endDate, startBalance, balance);

    }

    public ContentValues getContentValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", this.name);
        contentValues.put("startDate", this.startDate);
        contentValues.put("endDate", this.endDate);
        contentValues.put("startBalance", this.startBalance);
        contentValues.put("balance", this.balance);
        return contentValues;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public float getStartBalance() {
        return startBalance;
    }

    public void setStartBalance(float startBalance) {
        this.startBalance = startBalance;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }
}
