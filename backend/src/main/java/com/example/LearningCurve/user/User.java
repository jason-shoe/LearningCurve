package com.example.LearningCurve.user;

import com.example.LearningCurve.firebase.Model;

import java.util.Optional;

public class User implements Model<UserId> {
    private UserId id;
    private Optional<String> name;

    public static class Builder {
        private UserId id;
        private Optional<String> name;

        public Builder id(UserId id) {
            this.id = id;
            return this;
        }

        public Builder name(Optional<String> name) {
            this.name = name;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    public User() {
        this.id = new UserId();
    }

    public User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
    }

    public User(CreateUser request) {
        this.id = request.getUserId();
        this.name = request.getName();
    }

    public UserId getId() {
        return id;
    }

    public Optional<String> getName() {
        return name;
    }

}
