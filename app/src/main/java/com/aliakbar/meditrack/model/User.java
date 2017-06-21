package com.aliakbar.meditrack.model;

/**
 * Created by ALIAKBAR on 21-06-2017.
 */

public class User {

    public String name;
    public String age ;
    public String uId;


    public User(String name, String age, String uId) {
        this.name = name;
        this.age = age;
        this.uId = uId;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }
}
