package com.example.LearningCurve.phrase;

import com.example.LearningCurve.firebase.FirebaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class PhraseService {
    FirebaseService<PhraseId, Phrase> firebaseService = new FirebaseService<>("phrases", Phrase.class);

    public Phrase getPhrase(PhraseId phraseId) {
        return firebaseService.get(phraseId);
    }

    public List<Phrase> getPhrases(List<PhraseId> phraseIds) {
        return firebaseService.get(phraseIds);
    }

    public String createPhrase(Phrase phrase) throws InterruptedException, ExecutionException {
        return firebaseService.createOrUpdate(phrase);
    }

    public List<String> bulkCreate(List<Phrase> phrases) throws InterruptedException, ExecutionException {
        return firebaseService.create(phrases);
    }
}
