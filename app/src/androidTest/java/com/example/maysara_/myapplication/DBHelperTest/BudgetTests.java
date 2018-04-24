package com.example.maysara_.myapplication.DBHelperTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Models.Budget;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


@RunWith(AndroidJUnit4.class)
public class BudgetTests {

    private static final String budgetName = "budget";
    private static final String startDate = "1/1/2019";
    private static final String endDate = "10/10/2019";
    private static final float startBalance = (float) 1000.11;
    private static final float balance = (float) 1000.11;
    private static final int budgetsNumber = 10;
    private DB_Helper helper;

    @Before
    public void before() {
        Context context = InstrumentationRegistry.getTargetContext();
        helper = new DB_Helper(context);
    }

    @Test
    public void createBudget() {
        createBudgetInstance();
        Budget savedBudget = helper.getBudget(1);
        assertEquals(budgetName, savedBudget.getName());
        assertEquals(startDate, savedBudget.getStartDate());
        assertEquals(endDate, savedBudget.getEndDate());
        assertEquals(startBalance, savedBudget.getStartBalance(), .1);
        assertEquals(balance, savedBudget.getBalance(), .1);
        helper.clearDB();
    }

    @Test
    public void removeBudget() {
        createBudgetInstance();
        helper.removeBudget(1);
        Budget deletedBudget = helper.getBudget(1);
        assertNull(deletedBudget);
    }

    @Test
    public void getAllBudgets() {
        for (int i = 0; i < budgetsNumber; i++) {
            createBudgetInstance();
        }
        Budget[] budgets = helper.getAllBudgets();
        assertEquals(budgetsNumber, budgets.length);
    }

    private void createBudgetInstance() {
        Budget budget = new Budget(budgetName, startDate, endDate, startBalance, balance);
        helper.createBudget(budget);
    }

    @After
    public void clear() {
        helper.clearDB();
    }
}
