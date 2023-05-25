package com.example.LearningCurve.services.chatgpt;

import java.util.ArrayList;
import java.util.List;

public class ChatResponse {

    private Usage usage;

    private List<Choice> choices;

    // constructors, getters and setters

    public static class Choice {

        private int index;
        private Message message;

        public Choice() {
        }

        public Choice(int index, Usage usage, Message message) {
            this.index = index;
            this.message = message;
        }

        public int getIndex() {
            return index;
        }

        public Message getMessage() {
            return message;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public void setMessage(Message message) {
            this.message = message;
        }
    }

    public ChatResponse() {
        this.choices = new ArrayList<>();
    }

    public List<Choice> getChoices() {
        return choices;
    }

    public Usage getUsage() {
        return usage;
    }
}
