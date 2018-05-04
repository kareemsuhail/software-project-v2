package com.example.maysara_.myapplication.Helpers;

import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.Models.Expense;
import com.example.maysara_.myapplication.Models.User;

interface queries {
    User getUser(int id);

    long saveUser(User user);

    Budget[] getAllBudgets();

    Category[] getCategoriesForBudget(int id);

    Budget getBudget(int id);

    long createBudget(Budget budget);

    Budget removeBudget(int id);
    void updateBudget(Budget budget);
    long createCategory(Category category);

    Category[] getAllCategories();

    Category getCategory(int id);

    Category removeCategory(int id);

    long createExpense(Expense expense);

    Expense[] getAllExpenses();

    Expense getExpense(int id);

    Expense removeExpense(int id);

    Expense[] getAllExpensesForCategory(int id);
}
