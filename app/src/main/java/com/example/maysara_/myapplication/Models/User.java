package com.example.maysara_.myapplication.Models;

import com.orm.SugarRecord;

public class User extends SugarRecord {

    private String name;
    private String phoneNumber;
    private int gender;

    public User(String name, String phoneNumber, int gender) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
