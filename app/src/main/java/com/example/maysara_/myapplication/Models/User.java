package com.example.maysara_.myapplication.Models;


import android.content.ContentValues;
import android.database.Cursor;

public class User {

    private  int id ;
    private String name;
    private String phone;
    private int gender;

    public User() {
    }

    public User(String name, String phone, int gender) {
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }

    public User(int id, String name, String phone, int gender) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.gender = gender;
    }
    public ContentValues getContentValues(){
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",this.name);
        contentValues.put("phone",this.phone);
        contentValues.put("gender",this.gender);
        return contentValues ;

    }
    public static User buildFromCursor(Cursor cursor){
        int id = cursor.getInt(cursor.getColumnIndex("ID"));
        String name  = cursor.getString(cursor.getColumnIndex("name"));
        String phone  = cursor.getString(cursor.getColumnIndex("phone"));
        int gender  = cursor.getInt(cursor.getColumnIndex("gender"));

        return new User(id,name,phone,gender) ;

    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(String phone) {
        this.phone = phone;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }
}
