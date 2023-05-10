package com.example.LearningCurve.text;

import com.example.LearningCurve.phrase.CreatePhrase;
import com.example.LearningCurve.user.UserId;

import java.util.List;

public class CreateText {
    private UserId authorId;
    private String title;
    private String text;
    private String translation;
    private List<CreatePhrase> phrases;

    public CreateText(UserId authorId, String title, String text, String translation, List<CreatePhrase> phrases) {
        this.authorId = authorId;
        this.title = title;
        this.text = text;
        this.translation = translation;
        this.phrases = phrases;
    }

    public UserId getAuthorId() {
        return authorId;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getTranslation() {
        return translation;
    }

    public List<CreatePhrase> getPhrases() {
        return phrases;
    }
}
