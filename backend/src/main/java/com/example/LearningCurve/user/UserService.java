package com.example.LearningCurve.user;

import com.example.LearningCurve.firebase.FirebaseService;
import com.google.cloud.firestore.CollectionReference;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    FirebaseService<UserId, User> firebaseService = new FirebaseService<>(User.class);

    public User getUser(UserId userId) {
        return firebaseService.get(getCollectionReference(), userId);
    }

    public List<String> getAllUserIds() {
        try {
            return firebaseService.getAllIds(getCollectionReference());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createUser(
            User user) throws InterruptedException, ExecutionException {
        return firebaseService.createOrUpdate(getCollectionReference(), user);
    }

    public static CollectionReference getCollectionReference() {
        return FirestoreClient.getFirestore().collection("users");
    }
}
