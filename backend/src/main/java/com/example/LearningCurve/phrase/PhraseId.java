package com.example.LearningCurve.phrase;

import com.example.LearningCurve.firebase.ModelId;
import com.example.LearningCurve.firebase.ResourceIdentifier;

public class PhraseId extends ModelId {
    public PhraseId() {
        super(ResourceIdentifier.PHRASE);
    }

    public PhraseId(String id) {
        super(id);
    }
}
