package com.example.LearningCurve.phrase;

public class CreatePhrase {
    private String value;
    private String pinyin;
    private String meaning;

    public CreatePhrase(String value, String pinyin, String meaning) {
        this.value = value;
        this.pinyin = pinyin;
        this.meaning = meaning;
    }

    public String getValue() {
        return this.value;
    }

    public String getPinyin() {
        return this.pinyin;
    }

    public String getMeaning() {
        return this.meaning;
    }
}
