package com.example.LearningCurve.user;

import java.util.Optional;

public class CreateUser {
    private UserId userId;
    private Optional<String> name;

    public CreateUser(UserId userId, Optional<String> name) {
        this.userId = userId;
        this.name = name;
    }

    public UserId getUserId() {
        return userId;
    }

    public Optional<String> getName() {
        return name;
    }
}
