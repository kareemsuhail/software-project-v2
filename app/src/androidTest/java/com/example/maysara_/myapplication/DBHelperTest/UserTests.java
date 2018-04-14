package com.example.maysara_.myapplication.DBHelperTest;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Models.User;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class UserTests {
    private static final String userName = "testUser";
    private static final int userGender = 1;
    private static final String userPhoneNumber = "1231234435435";
    private DB_Helper helper;

    @Before
    public void before() {
        Context context = InstrumentationRegistry.getTargetContext();
        helper = new DB_Helper(context);
    }

    @Test
    public void createUser() {
        User user = new User(userName, userPhoneNumber, userGender);
        helper.saveUser(user);
        User savedUser = helper.getUser(1);

        assertEquals(userName, savedUser.getName());
        assertEquals(userPhoneNumber, savedUser.getPhoneNumber());
        assertEquals(userGender, savedUser.getGender());
    }

    @After
    public void clear() {
        helper.clearDB();
    }

}
