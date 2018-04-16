package com.example.maysara_.myapplication.UITest;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.example.maysara_.myapplication.Activities.BudgetActivity;
import com.example.maysara_.myapplication.Activities.ProfileActivity;
import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Helpers.SharedPref;
import com.example.maysara_.myapplication.Models.Budget;
import com.example.maysara_.myapplication.R;
import com.example.maysara_.myapplication.RecyclerViewMatcher;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.maysara_.myapplication.TestUtils.withRecyclerView;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;
//import static com.example.maysara_.myapplication.TestUtils.*;
import static com.example.maysara_.myapplication.RecyclerViewMatcher.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BudgetActivityTest {

    private static final String  budgetNameString = "Budget";
    private static final double  budgetBalance = 1000d;

    @Rule
    public ActivityTestRule<BudgetActivity> mActivityRule =
            new ActivityTestRule(BudgetActivity.class);
    private DB_Helper db_helper;
    @Before
    public void setUp(){
        Context context = InstrumentationRegistry.getTargetContext();
        db_helper = new DB_Helper(context);
        db_helper.clearDB();
        SharedPreferences preferences = mActivityRule.getActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
    @Test
    public void checkIfAddBudgetDisplaysDialog(){
        clickOnFab();
        onView(withId(R.id.newBudgetName)).check(matches(isDisplayed()));
    }

    @Test
    public void createNewBudget(){
        clickOnFab();
        addBudget();
        onView(withRecyclerView(R.id.budget_list).atPositionOnView(0, R.id.budgetName)).check(matches(withText(budgetNameString)));
        onView(withRecyclerView(R.id.budget_list).atPositionOnView(0, R.id.budgetBalance)).check(matches(withText(String.valueOf(budgetBalance)+"$")));
    }

    private void clickOnFab(){
        onView(withId(R.id.addBudget)).perform(click());
    }

    private void addBudget(){
        onView(withId(R.id.newBudgetName)).perform(typeText(budgetNameString));
        onView(withId(R.id.newBudgetBalance)).perform(clearText());
        onView(withId(R.id.newBudgetBalance)).perform(typeText("1000"));
        onView(withId(R.id.addNewBudget)).perform(click());
    }

    @After
    public void tearDown(){
        db_helper.clearDB();
        SharedPreferences preferences = mActivityRule.getActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }

}
