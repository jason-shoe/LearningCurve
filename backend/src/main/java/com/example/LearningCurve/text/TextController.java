package com.example.LearningCurve.text;

import com.example.LearningCurve.models.TextGenerationRequest;
import com.example.LearningCurve.phrase.Phrase;
import com.example.LearningCurve.phrase.PhraseService;
import com.example.LearningCurve.services.chatgpt.ChatResponse;
import com.example.LearningCurve.services.chatgpt.ChatService;
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

    @Autowired
    ChatService chatService;

    @QueryMapping
    public Text textById(@Argument TextId id) {
        return textService.getText(id);
    }

    @QueryMapping
    public List<Text> texts() {
        return textService.getAllTexts();
    }

    @MutationMapping
    public String createText(@Argument CreateText request) {
        try {
            Text newText = new Text(request);
            List<Phrase> phrases = request.getPhrases().stream().map(Phrase::new).collect(Collectors.toList());
            newText.setPhraseIds(phrases.stream().map(Phrase::getId).collect(Collectors.toList()));
            phraseService.bulkCreate(phrases);
            return textService.createText(newText);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @MutationMapping
    public String generateText(@Argument TextGenerationRequest request) {
        try {
            Text newText = new Text();
            ChatResponse response = chatService.generateText(request);
            newText.setAuthorId(new UserId());
            newText.setTitle(request.getPrompt());
            newText.setText(response.getChoices().get(0).getMessage().getContent());
            newText.setTranslation("");
            newText.setPhraseIds(List.of());

            return textService.createText(newText);
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
        return phraseService.getPhrases(text.getPhraseIds());
    }


}
