package com.example.maysara_.myapplication;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.Models.Category;
import com.example.maysara_.myapplication.Models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class DBHelperTest {
    private DB_Helper db_helper ;
    @Before
    public void createDb() {
        Context context = InstrumentationRegistry.getTargetContext();
        this.db_helper = new DB_Helper(context);

    }
    @Test
    public void insert_user_test(){
        //
        User user = new User("kareem","0592760040",1);
        db_helper.saveUser(user);
        User savedUser = db_helper.getUser(1);

        assertEquals("kareem",savedUser.getName());
        assertEquals("0592760040",savedUser.getPhoneNumber());
        assertEquals(1,savedUser.getGender());
    }
    @Test
    public void insert_budget_test(){
        Budget budget = new Budget("budget1","","",200,200);
        db_helper.createBudget(budget);
        Budget savedBudget = db_helper.getBudget(1);
        assertEquals("budget1",savedBudget.getName());
    }
    @Test
    public void insert_category_test(){
        Category category = new Category("food",3,100);
        db_helper.createCategory(category);
        Category savedCategory = db_helper.getCategory(1);
        Log.d("category","ID is :"+savedCategory.getId()+" name is :"+savedCategory.getName());
        assertEquals("food",savedCategory.getName());
        assertEquals(3,savedCategory.getBudgetID());

    }
    @After
    public void tearDown(){
        db_helper.clearDB();
    }

}
