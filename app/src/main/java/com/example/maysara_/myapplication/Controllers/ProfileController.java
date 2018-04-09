package com.example.maysara_.myapplication.Controllers;

import android.content.Context;

import com.example.maysara_.myapplication.Helpers.DB_Helper;
import com.example.maysara_.myapplication.Helpers.SharedPref;
import com.example.maysara_.myapplication.Models.User;

public class ProfileController {

    private DB_Helper db_helper;
    private SharedPref sharedPref;
    private Context context;

    public ProfileController(Context context) {
        db_helper = new DB_Helper(context);
        sharedPref = new SharedPref(context);
        this.context = context;
    }

    public void CreateProfile(String name, int gender, String phoneNumber) {

        User user = new User(name, phoneNumber, gender);
        login(name);
        db_helper.saveUser(user);
    }

    public boolean isNewUser() {
        return sharedPref.readItem("userName") == null;
    }

    private void login(String userName) {
        sharedPref.storeItem("userName", userName);
    }
}
