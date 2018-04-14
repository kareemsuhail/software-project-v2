package com.example.maysara_.myapplication.UITest;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.maysara_.myapplication.Activities.BudgetActivity;
import com.example.maysara_.myapplication.Activities.ProfileActivity;
import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Helpers.SharedPref;
import com.example.maysara_.myapplication.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.Matchers.is;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class BudgetActivityTest {

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

        onView(withId(R.id.addBudget)).perform(click());
        onView(withId(R.id.newBudgetName)).check(matches(isDisplayed()));

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
