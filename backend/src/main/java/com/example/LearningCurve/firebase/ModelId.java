package com.example.LearningCurve.firebase;

import com.google.cloud.firestore.annotation.PropertyName;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ModelId {
    ResourceIdentifier resourceIdentifier;
    UUID id;
    String stringId;


    private static String toString(ResourceIdentifier resourceIdentifier,
                                   UUID id) {
        return resourceIdentifier.toString().toLowerCase() + '.' +
                id.toString();
    }

    public ModelId(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        this.id = UUID.randomUUID();
        this.stringId = toString(this.resourceIdentifier, this.id);
    }

    public ModelId(ResourceIdentifier resourceIdentifier, String id) {
        this.resourceIdentifier = resourceIdentifier;
        this.id = UUID.fromString(id);
        this.stringId = toString(this.resourceIdentifier, this.id);
    }

    public ModelId(String stringId) {
        this.stringId = stringId;
        List<String> parts = Arrays.stream(stringId.split(".")).toList();
        if (parts.size() == 2) {
            this.resourceIdentifier = ResourceIdentifier.valueOf(parts.get(0));
            this.id = UUID.fromString(parts.get(1));
        }
    }

    @PropertyName("id")
    public String toString() {
        if (this.resourceIdentifier != null && this.id != null) {
            return toString(this.resourceIdentifier, this.id);
        }
        return this.stringId;
    }


}
