package com.example.maysara_.myapplication.Helpers;

import android.content.Context;

import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.Models.User;

import java.lang.reflect.Array;
import java.util.List;

public class DB_Helper implements queries {

    public DB_Helper(Context context) {

    }

    @Override
    public User getUser(int id) {
        return User.findById(User.class,id);
    }

    @Override
    public void saveUser(User user) {
            user.save();
    }

    @Override
    public Budget[] getAllBudgets() {
        List<Budget> budgets = Budget.listAll(Budget.class);
        Budget[] array = new Budget[budgets.size()];
        budgets.toArray(array);
        return array;
    }

    @Override
    public Category[] getCategoriesForBudget(int id) {
        List<Category> categories = Category.find(Category.class, "budget = ?",new String[]{id+""} );
        Category[] array = new Category[categories.size()];
        categories.toArray(array);
        return array;
    }


    @Override
    public Budget getBudget(int id) {
        return Budget.findById(Budget.class,id);

    }

    @Override
    public void createBudget(Budget budget) {
        budget.save();
    }

    @Override
    public Budget removeBudget(int id) {
        Budget budget = Budget.findById(Budget.class, id);
        budget.delete();
        return  budget;
    }

    @Override
    public void createCategory(Category category) {
        category.save();
    }

    @Override
    public Category[] getAllCategories() {

        List<Category> categories = Category.listAll(Category.class);
        Category[] array = new Category[categories.size()];
        categories.toArray(array);
        return array;
    }

    @Override
    public Category getCategory(int id) {

        return Category.findById(Category.class,id);

    }

    @Override
    public Category removeCategory(int id) {

        Category category = Category.findById(Category.class, id);
        category.delete();
        return  category;
    }
}
