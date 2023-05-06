package com.example.LearningCurve.user;

import com.example.LearningCurve.firebase.ModelId;
import com.example.LearningCurve.firebase.ResourceIdentifier;

public class UserId extends ModelId {

    public UserId() {
        super(ResourceIdentifier.USER);
    }

    public UserId(String id) {
        super(id);
    }

}
