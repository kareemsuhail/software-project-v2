package com.example.maysara_.myapplication.Helpers;

import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.Models.User;

interface queries {
    User getUser(int id);

    void saveUser(User user);

    Budget[] getAllBudgets();

    Category[] getCategoriesForBudget(int id);

    Budget getBudget(int id);

    void createBudget(Budget budget);

    Budget removeBudget(int id);

    void createCategory(Category category);

    Category[] getAllCategories();

    Category getCategory(int id);

    Category removeCategory(int id);
}
