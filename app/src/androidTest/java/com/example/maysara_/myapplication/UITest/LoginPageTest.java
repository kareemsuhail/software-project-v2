package com.example.maysara_.myapplication.UITest;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.maysara_.myapplication.Activities.ProfileActivity;
import com.example.maysara_.myapplication.Helpers.SharedPref;
import com.example.maysara_.myapplication.R;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginPageTest {

    @Rule
    public ActivityTestRule<ProfileActivity> mActivityRule =
            new ActivityTestRule(ProfileActivity.class);
    @Before
    public void setUp(){
        SharedPreferences preferences = mActivityRule.getActivity().getSharedPreferences("PREFERENCE", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
    }
    @Test
    public void phoneNumberShouldAcceptJustNumbersTest(){
        onView(withId(R.id.phone)).perform(typeText(String.valueOf("15")));
        onView(withText("15")).check(matches(isDisplayed()));
        onView(withId(R.id.phone)).perform(replaceText(String.valueOf("")));
        onView(withId(R.id.phone)).perform(typeText(String.valueOf("text")));
        onView(withId(R.id.phone)).check(matches(withText("")));
    }
    @Test
    public void usernameShouldNotContainNumbersTest(){
        onView(withId(R.id.name)).perform(typeText("kareem ayesh"));
        onView(withId(R.id.name)).check(matches(withText("kareem ayesh")));
        onView(withId(R.id.name)).perform(replaceText(""));
        onView(withId(R.id.name)).perform(typeText("kareem123 ayesh"));
        onView(withId(R.id.name)).check(matches(withText("kareem ayesh")));

    }
}
