package com.example.LearningCurve.text;

import com.example.LearningCurve.firebase.ModelId;
import com.example.LearningCurve.firebase.ResourceIdentifier;

public class TextId extends ModelId {
    public TextId() {
        super(ResourceIdentifier.TEXT);
    }

    public TextId(String id) {
        super(id);
    }
}
