package com.example.LearningCurve.phrase;

import com.example.LearningCurve.firebase.FirebaseService;
import com.google.cloud.firestore.CollectionReference;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PhraseService {
    FirebaseService<PhraseId, Phrase> firebaseService = new FirebaseService<>(Phrase.class);

    public Phrase getPhrase(PhraseId phraseId) {
        return firebaseService.get(getCollectionReference(), phraseId);
    }

    public List<Phrase> getPhrases(List<PhraseId> phraseIds) {
        return firebaseService.get(getCollectionReference(), phraseIds);
    }

    public String createPhrase(Phrase phrase) throws InterruptedException, ExecutionException {
        return firebaseService.createOrUpdate(getCollectionReference(), phrase);
    }

    public List<String> bulkCreate(List<Phrase> phrases) throws InterruptedException, ExecutionException {
        return firebaseService.create(getCollectionReference(), phrases);
    }

    public static CollectionReference getCollectionReference() {
        return FirestoreClient.getFirestore().collection("phrases");
    }
}
