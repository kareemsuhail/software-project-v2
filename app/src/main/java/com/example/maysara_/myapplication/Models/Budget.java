package com.example.maysara_.myapplication.Models;

import com.orm.SugarRecord;

import java.util.Date;

public class Budget  extends SugarRecord{

    private String name;
    private Date startDate;
    private Date endDate;
    private float startBalance;
    private float balance;

    public Budget(String name, Date startDate, Date endDate, float startBalance, float balance) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startBalance = startBalance;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
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
}
