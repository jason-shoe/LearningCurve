package com.example.LearningCurve.session;

import com.example.LearningCurve.firebase.Model;

import java.util.Date;

public class Session implements Model<SessionId> {
    private SessionId id;
    private Date started;
    private Date lastActive;

    public static class Builder {
        private SessionId id;
        private Date started;
        private Date lastActive;

        public Builder id(SessionId id) {
            this.id = id;
            return this;
        }

        public Builder started(Date started) {
            this.started = started;
            return this;
        }

        public Builder lastActive(Date lastActive) {
            this.lastActive = lastActive;
            return this;
        }

        public Session build() {
            return new Session(this);
        }
    }

    public Session() {
        this.id = new SessionId();
        this.started = new Date();
        this.lastActive = this.started;
    }

    public Session(Builder builder) {
        this.id = builder.id;
        this.started = builder.started;
        this.lastActive = builder.lastActive;
    }

    public SessionId getId() {
        return id;
    }

    public Date getStarted() {
        return started;
    }

    public Date getLastActive() {
        return lastActive;
    }


}
