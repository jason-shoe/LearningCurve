package com.example.LearningCurve.user;

import com.example.LearningCurve.firebase.FirebaseService;
import com.google.cloud.firestore.DocumentSnapshot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    @Autowired
    FirebaseService<User> firebaseService;
    public static final String COL_NAME = "users";

    public User getUser(String userId) {
        try {
            DocumentSnapshot snapshot = firebaseService.get(COL_NAME, userId);
            User user = null;
            if (snapshot.exists()) {
                user = snapshot.toObject(User.class);
            }
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public List<String> getAllUserIds() {
        try {
            return firebaseService.getAllIds(COL_NAME);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createUser(
            User user) throws InterruptedException, ExecutionException {
        return firebaseService.createOrUpdate(COL_NAME, user);
    }
}
