package com.example.LearningCurve.text;

import com.example.LearningCurve.firebase.FirebaseService;
import com.example.LearningCurve.phrase.Phrase;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TextService {
    FirebaseService<TextId, Text> firebaseService = new FirebaseService<>("texts", Text.class);

    public Text getText(TextId textId) {
        return firebaseService.get(textId);
    }

    public String createText(
            Text text, List<Phrase> phrases) throws InterruptedException, ExecutionException {
        return firebaseService.createOrUpdate(text);
    }
}
