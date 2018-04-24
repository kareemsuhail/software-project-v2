package com.example.maysara_.myapplication.DBHelperTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.Models.Expense;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class ExpensesTests {

    private static final String label = "expenses";
    private static final double amount = 10.111;
    private static final int budgetId = 1;
    private static final int categoryId = 1;
    private static final String date = "1/10/2018";
    private static final int expenseNumber = 1;
    private DB_Helper helper;

    @Before
    public void before() {
        Context context = InstrumentationRegistry.getTargetContext();
        helper = new DB_Helper(context);
    }

    @Test
    public void createExpense() {
        createExpensesInstance(categoryId);
        Expense expense = helper.getExpense(1);
        assertEquals(label, expense.getLabel());
        assertEquals(amount, expense.getAmount(), .1);
        assertEquals(categoryId, expense.getCategory());
        assertEquals(date, expense.getDate());
        helper.clearDB();
    }

    @Test
    public void removeExpense() {
        createExpensesInstance(categoryId);
        helper.removeExpense(1);
        Expense deletedExpense = helper.getExpense(1);
        assertNull(deletedExpense);
    }

    @Test
    public void getAllExpenses() {
        for (int i = 1; i < expenseNumber + 1; i++) {
            createExpensesInstance(i);
        }
        Expense[] expenses = helper.getAllExpenses();
        Category[] categories = helper.getAllCategories();
        assertEquals(expenseNumber, expenses.length);
        assertEquals(expenseNumber, categories.length);
        helper.clearDB();
    }

    @Test
    public void checkCorrespondingExpenses() {
        for (int i = 0; i < expenseNumber; i++) {
            createExpensesInstance(categoryId);
        }
        Expense[] correspondingExpenses = helper.getAllExpensesForCategory(categoryId);
        assertEquals(expenseNumber, correspondingExpenses.length);
    }

    @Test
    public void deleteCategoryAndCheckCorrespondingExpenses() {
        for (int i = 0; i < expenseNumber; i++) {
            createExpensesInstance(categoryId);
        }
        helper.removeCategory(categoryId);
        Expense[] correspondingExpenses = helper.getAllExpensesForCategory(categoryId);
        assertEquals(0, correspondingExpenses.length);
    }

    @Test
    public void deleteBudgetAndCheckCorrespondingExpenses() {
        for (int i = 0; i < expenseNumber; i++) {
            createExpensesInstance(categoryId);
        }
        helper.removeBudget(budgetId);
        Expense[] correspondingExpenses = helper.getAllExpensesForCategory(categoryId);
        assertEquals(0, correspondingExpenses.length);
    }

    private void createExpensesInstance(int categoryId) {
        helper.createBudget(new Budget());
        helper.createCategory(new Category("category", budgetId, 10));
        Expense expense = new Expense(label, amount, categoryId, date);
        helper.createExpense(expense);
    }

    @After
    public void clear() {
        helper.clearDB();
    }

}
