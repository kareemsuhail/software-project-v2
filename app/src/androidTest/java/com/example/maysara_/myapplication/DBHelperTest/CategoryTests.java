package com.example.maysara_.myapplication.DBHelperTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.Models.Category;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(AndroidJUnit4.class)
public class CategoryTests {

    private static final String categoryName = "category";
    private static final int limitAmount = 10;
    private static final int budgetId = 1;
    private static final int categoriesNumber = 1;
    private DB_Helper helper;

    @Before
    public void before() {
        Context context = InstrumentationRegistry.getTargetContext();
        helper = new DB_Helper(context);
    }

    @Test
    public void createCategory() {
        createCategoryInstance(budgetId);
        Category savedCategory = helper.getCategory(1);
        assertEquals(categoryName, savedCategory.getName());
        assertEquals(budgetId, savedCategory.getBudgetID());
        assertEquals(limitAmount, savedCategory.getLimit());
        helper.clearDB();
    }

    @Test
    public void removeCategory() {
        createCategoryInstance(budgetId);
        helper.removeCategory(1);
        Category deletedCategory = helper.getCategory(1);
        assertNull(deletedCategory);
    }

    @Test
    public void getAllCategories() {
        for (int i = 1; i < categoriesNumber + 1; i++) {
            createCategoryInstance(i);
        }
        Budget[] budgets = helper.getAllBudgets();
        Category[] categories = helper.getAllCategories();
        assertEquals(categoriesNumber, budgets.length);
        assertEquals(categoriesNumber, categories.length);
        helper.clearDB();
    }

    @Test
    public void checkCorrespondingCategories() {
        for (int i = 0; i < categoriesNumber; i++) {
            createCategoryInstance(budgetId);
        }
        Category[] correspondingCategories = helper.getCategoriesForBudget(budgetId);
        assertEquals(categoriesNumber, correspondingCategories.length);
    }

    @Test
    public void checkDeletedCorrespondingCategories() {
        for (int i = 0; i < categoriesNumber; i++) {
            createCategoryInstance(budgetId);
        }
        helper.removeBudget(budgetId);
        Category[] correspondingCategories = helper.getCategoriesForBudget(budgetId);
        assertEquals(0, correspondingCategories.length);
    }

    private void createCategoryInstance(int budgetId) {
        helper.createBudget(new Budget());
        Budget savedBudget = helper.getBudget(budgetId);
        Category category = new Category(categoryName, savedBudget.getId(), limitAmount);
        helper.createCategory(category);
    }

    @After
    public void clear() {
        helper.clearDB();
    }
}
