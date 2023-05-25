package com.example.LearningCurve.models;

import com.example.LearningCurve.services.chatgpt.Message;

import java.util.List;

public class TextGenerationRequest {
    DifficultyLevel difficultyLevel;
    String prompt;
    int length;

    public TextGenerationRequest(DifficultyLevel difficultyLevel, String prompt, int length) {
        this.difficultyLevel = difficultyLevel;
        this.prompt = prompt;
        this.length = length;
    }

    public DifficultyLevel getDifficultyLevel() {
        return this.difficultyLevel;
    }

    public String getPrompt() {
        return this.prompt;
    }

    public int getLength() {
        return this.length;
    }

    public List<Message> getTextGenerationPrompt() {
        return List.of(new Message("system", "Generate a " +
                difficultyLevel.toString() +
                " level " + length +
                " word passage in Chinese off the following prompt"), new Message("user", prompt));
    }

}
