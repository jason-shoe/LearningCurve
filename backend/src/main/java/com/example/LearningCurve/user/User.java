package com.example.LearningCurve.user;

import com.example.LearningCurve.firebase.Model;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class User implements Model<UserId> {
    private UserId id;
    private Optional<String> name;
    private UUID latestSession;
    private Date lastActive;

    public User() {
        this.id = new UserId();
    }

    public User(UserId id, Optional<String> name, UUID latestSession, Date lastActive) {
        this.id = id;
        this.name = name;
        this.latestSession = latestSession;
        this.lastActive = lastActive;
    }

    public User(CreateUser request) {
        this.id = request.getUserId();
        this.name = request.getName();
        this.latestSession = UUID.randomUUID();
        this.lastActive = new Date();
    }

    public UserId getId() {
        return id;
    }

    public Optional<String> getName() {
        return name;
    }

    public UUID getLatestSession() {
        return latestSession;
    }

    public Date getLastActive() {
        return lastActive;
    }

    public void setName(String name) {
        this.name = Optional.of(name);
    }

    public void setLatestSession(UUID latestSession) {
        this.latestSession = latestSession;
    }

    public void setLastActive(Date lastActive) {
        this.lastActive = lastActive;
    }


}
