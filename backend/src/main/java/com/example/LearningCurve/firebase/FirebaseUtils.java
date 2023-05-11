package com.example.LearningCurve.firebase;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseToken;

public class FirebaseUtils {


    public static String getUidFromToken(String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            return decodedToken.getUid();
        } catch (Exception e) {
            // token is invalid or expired
            e.printStackTrace();
            return null;
        }
    }
}
