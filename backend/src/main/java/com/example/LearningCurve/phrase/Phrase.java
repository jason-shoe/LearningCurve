package com.example.LearningCurve.phrase;

import com.example.LearningCurve.firebase.Model;

public class Phrase implements Model<PhraseId> {
    private PhraseId id;
    private String value;
    private String pinyin;
    private String meaning;

    public Phrase() {
        this.id = new PhraseId();
    }

    public Phrase(String id, String value, String pinyin, String meaning) {
        this.id = new PhraseId(id);
        this.value = value;
        this.pinyin = pinyin;
        this.meaning = meaning;
    }

    public Phrase(CreatePhrase phraseInput) {
        this.id = new PhraseId();
        id.setBaseId(phraseInput.getValue());
        this.value = phraseInput.getValue();
        this.pinyin = phraseInput.getPinyin();
        this.meaning = phraseInput.getMeaning();
    }

    public PhraseId getId() {
        return id;
    }

    public String getValue() {
        return value;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
