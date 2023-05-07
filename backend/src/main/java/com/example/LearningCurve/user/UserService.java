package com.example.LearningCurve.user;

import com.example.LearningCurve.firebase.FirebaseService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    FirebaseService<UserId, User> firebaseService = new FirebaseService<>("users", User.class);

    public User getUser(UserId userId) {
        return firebaseService.get(userId);
    }

    public List<String> getAllUserIds() {
        try {
            return firebaseService.getAllIds();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String createUser(
            User user) throws InterruptedException, ExecutionException {
        return firebaseService.createOrUpdate(user);
    }
}
