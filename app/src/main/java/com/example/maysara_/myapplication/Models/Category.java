package com.example.maysara_.myapplication.Models;

import com.orm.SugarRecord;

public class Category extends SugarRecord {

    private String name;
    private Budget budget;
    private int limit;

    public Category(String name, int budget, int limit) {
        this.name = name;
        this.budget = Budget.findById(Budget.class,budget);
        this.limit = limit;
    }



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
