package com.example.LearningCurve.text;

import com.example.LearningCurve.firebase.Model;

public class Text extends Model {
    private TextId id;
    private String text;
    private String authorId;

    public Text() {
    }

    public Text(String id, String text, String authorId) {
        this.id = new TextId(id);
        this.text = text;
        this.authorId = authorId;
    }

    public String getId() {
        return id.toString();
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getAuthorId() {
        return authorId;
    }


}
