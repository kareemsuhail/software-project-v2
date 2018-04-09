package com.example.maysara_.myapplication.Models;

import com.orm.SugarRecord;

import java.util.Date;

public class Expense extends SugarRecord {
    private String label;
    private float amount;
    private Category category;
    private Date date;

    public Expense(String label, float amount, int category, Date date) {
        this.label = label;
        this.amount = amount;
        this.category = Category.findById(Category.class,category);
        this.date = date;
    }



    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = Category.findById(Category.class,category);
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
