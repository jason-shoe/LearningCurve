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

    private List<PhraseId> phrases;

    public Text() {
        this.id = new TextId();
    }

    public Text(String id, UserId authorId, List<PhraseId> phrases) {
        this.id = new TextId(id);
        this.authorId = authorId;
        this.phrases = phrases;
    }

    public TextId getId() {
        return id;
    }

    public UserId getAuthorId() {
        return authorId;
    }

    public List<PhraseId> getPhrases() {
        return phrases;
    }

    public void setPhrases(List<PhraseId> phrases) {
        this.phrases = phrases;
    }

    public void setAuthorId(UserId authorId) {
        this.authorId = authorId;
    }
}
