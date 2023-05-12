package com.example.LearningCurve.session;

import com.example.LearningCurve.firebase.ModelId;
import com.example.LearningCurve.firebase.ResourceIdentifier;

public class SessionId extends ModelId {

    public SessionId() {
        super(ResourceIdentifier.SESSION);
    }

    public SessionId(String id) {
        super(id);
    }
}
