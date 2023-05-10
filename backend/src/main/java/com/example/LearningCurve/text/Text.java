package com.example.LearningCurve.text;

import com.example.LearningCurve.firebase.Model;
import com.example.LearningCurve.phrase.PhraseId;
import com.example.LearningCurve.user.UserId;
import com.google.cloud.firestore.annotation.IgnoreExtraProperties;

import java.util.List;

@IgnoreExtraProperties
public class Text implements Model<TextId> {

    private TextId id;
    private UserId authorId;
    private String title;
    private String text;
    private String translation;
    private List<PhraseId> phraseIds;

    public Text() {
        this.id = new TextId();
    }

    public Text(String id, UserId authorId, String title, String text, String translation, List<PhraseId> phraseIds) {
        this.id = new TextId(id);
        this.authorId = authorId;
        this.title = title;
        this.text = text;
        this.translation = translation;
        this.phraseIds = phraseIds;
    }

    public Text(CreateText createText) {
        this.id = new TextId();
        this.authorId = createText.getAuthorId();
        this.title = createText.getTitle();
        this.text = createText.getText();
        this.translation = createText.getTranslation();
    }

    public TextId getId() {
        return id;
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

    public List<PhraseId> getPhraseIds() {
        return phraseIds;
    }

    public void setPhraseIds(List<PhraseId> phraseIds) {
        this.phraseIds = phraseIds;
    }

    public void setAuthorId(UserId authorId) {
        this.authorId = authorId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
