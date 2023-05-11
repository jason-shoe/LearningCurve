package com.example.LearningCurve.firebase;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ModelId {
    ResourceIdentifier resourceIdentifier;
    String baseId;
    String stringId;


    private static String toString(ResourceIdentifier resourceIdentifier,
                                   String baseId) {
        return resourceIdentifier.toString().toLowerCase() + '.' +
                baseId;
    }

    public ModelId(ResourceIdentifier resourceIdentifier) {
        this.resourceIdentifier = resourceIdentifier;
        this.baseId = UUID.randomUUID().toString();
        this.stringId = toString(this.resourceIdentifier, this.baseId);
    }

    public ModelId(ResourceIdentifier resourceIdentifier, String baseId) {
        this.resourceIdentifier = resourceIdentifier;
        this.baseId = baseId;
        this.stringId = toString(this.resourceIdentifier, baseId);
    }

    public ModelId(String stringId) {
        this.stringId = stringId;
        List<String> parts = Arrays.stream(stringId.split(".")).toList();
        if (parts.size() == 2) {
            this.resourceIdentifier = ResourceIdentifier.valueOf(parts.get(0));
            this.baseId = parts.get(1);
        }
    }

    public String toString() {
        if (this.resourceIdentifier != null && this.baseId != null) {
            return toString(this.resourceIdentifier, this.baseId);
        }
        return this.stringId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }
}
