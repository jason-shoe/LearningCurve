package com.example.LearningCurve.user;

import com.example.LearningCurve.firebase.Model;

public class User extends Model {
    private UserId id;

    private String name;

    public User() {
        this.id = new UserId();
    }

    public User(String id) {
        this.id = new UserId(id);
    }

    public User(String id, String name) {
        this.id = new UserId(id);
        this.name = name;
    }

    public String getId() {
        return id.toString();
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


}
