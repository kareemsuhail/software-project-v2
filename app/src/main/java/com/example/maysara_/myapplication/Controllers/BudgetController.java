package com.example.maysara_.myapplication.Controllers;

import android.content.Context;

import com.example.maysara_.myapplication.Helpers.DB_Helper;

public class BudgetController {

    private DB_Helper db_helper;

    public BudgetController(Context context) {
        db_helper = new DB_Helper(context);
    }

}
