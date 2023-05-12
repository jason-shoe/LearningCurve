package com.example.LearningCurve.text;

import com.example.LearningCurve.firebase.FirebaseService;
import com.example.LearningCurve.phrase.Phrase;
import com.google.cloud.firestore.CollectionReference;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class TextService {
    FirebaseService<TextId, Text> firebaseService = new FirebaseService<>(Text.class);

    public Text getText(TextId textId) {
        return firebaseService.get(getCollectionReference(), textId);
    }

    public List<Text> getAllTexts() {
        try {
            return firebaseService.getAllObjects(getCollectionReference());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createText(
            Text text, List<Phrase> phrases) throws InterruptedException, ExecutionException {
        return firebaseService.createOrUpdate(getCollectionReference(), text);
    }

    public static CollectionReference getCollectionReference() {
        return FirestoreClient.getFirestore().collection("texts");
    }
}
