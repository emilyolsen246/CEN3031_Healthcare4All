package com.example.healthcare4all;

//Stores the users name, email, and uid that is used with the firebase database.
public class User {
    String name;
    String email;
    String uid;

    User() {};

    User(String name, String email, String uid) {
        this.name = name;
        this.email = email;
        this.uid = uid;
    }
}
