package com.example.LearningCurve.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class FBInitialize {

    @PostConstruct
    public void initialize() {
        try {
            GoogleCredentials credentials =
                    GoogleCredentials.getApplicationDefault();
            FirebaseOptions options = FirebaseOptions.builder().setCredentials(
                    credentials).setDatabaseUrl(
                    "https://learningcurve-ddb4d.firebaseio.com/").setProjectId(
                    "learningcurve-ddb4d").build();

            FirebaseApp.initializeApp(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}