package com.example.LearningCurve.services.chatgpt;

import java.util.List;

public class ChatRequest {

    private String model;
    private List<Message> messages;
    private double temperature;

    public ChatRequest(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    public String getModel() {
        return model;
    }

    public List<Message> getMessages() {
        return messages;
    }

}
