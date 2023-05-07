package com.example.LearningCurve.phrase;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;

public class PhraseController {
    @Autowired
    PhraseService phraseService;


    @QueryMapping
    public Phrase phraseById(@Argument PhraseId id) {
        return phraseService.getPhrase(id);
    }

    @MutationMapping
    public String createPhrase(@Argument String value, @Argument String pinyin, @Argument String meaning) {
        try {
            Phrase phrase = new Phrase();
            phrase.setValue(value);
            phrase.setMeaning(meaning);
            phrase.setPinyin(pinyin);
            return phraseService.createPhrase(phrase);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
