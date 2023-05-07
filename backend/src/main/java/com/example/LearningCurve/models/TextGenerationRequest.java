package com.example.LearningCurve.models;

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
}
