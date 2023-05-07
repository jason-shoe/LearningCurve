package com.example.LearningCurve.text;

import com.example.LearningCurve.phrase.Phrase;
import com.example.LearningCurve.phrase.PhraseInput;
import com.example.LearningCurve.phrase.PhraseService;
import com.example.LearningCurve.user.User;
import com.example.LearningCurve.user.UserId;
import com.example.LearningCurve.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TextController {

    @Autowired
    TextService textService;

    @Autowired
    UserService userService;

    @Autowired
    PhraseService phraseService;

    @QueryMapping
    public Text textById(@Argument TextId id) {
        return textService.getText(id);
    }

    @MutationMapping
    public String createText(@Argument UserId authorId, @Argument List<PhraseInput> phrases) {
        try {
            Text newText = new Text();
            newText.setAuthorId(authorId);
            List<Phrase> phrasesWithId = phrases.stream().map(Phrase::new).collect(Collectors.toList());
            newText.setPhrases(phrasesWithId.stream().map(Phrase::getId).collect(Collectors.toList()));
            for (Phrase phrase : phrasesWithId) {
                phraseService.createPhrase(phrase);
            }
            return textService.createText(newText, phrasesWithId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @SchemaMapping
    public User author(Text text) {
        return userService.getUser(text.getAuthorId());
    }

    @SchemaMapping
    public List<Phrase> phrases(Text text) {
        return phraseService.getPhrases(text.getPhrases());
    }


}
