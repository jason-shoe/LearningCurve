package com.example.LearningCurve.text;

import com.example.LearningCurve.firebase.FirebaseService;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class TextService {
    @Autowired
    FirebaseService<Text> firebaseService;

    public static final String COL_NAME = "texts";

    public Text getText(String textId) {
        try {
            DocumentSnapshot snapshot = firebaseService.get(COL_NAME, textId);
            Text text = null;
            if (snapshot.exists()) {
                text = snapshot.toObject(Text.class);
            }
            return text;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createText(
            Text text) throws InterruptedException, ExecutionException {
        return firebaseService.createOrUpdate(COL_NAME, text);
    }
}
