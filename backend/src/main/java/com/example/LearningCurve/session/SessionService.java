package com.example.LearningCurve.session;

import com.example.LearningCurve.firebase.FirebaseService;
import com.example.LearningCurve.user.UserId;
import com.example.LearningCurve.user.UserService;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Query;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class SessionService {
    private final int STALE_SESSION_DURATION_MINUTES = 30;

    FirebaseService<SessionId, Session> firebaseService = new FirebaseService<>(Session.class);

    public Session getSession(UserId userId, SessionId sessionId) {
        return firebaseService.get(getCollectionReference(userId), sessionId);
    }

    public void maybeUpdateSession(UserId userId) {
        try {
            Optional<Session> latestSession = firebaseService.getAllObjects(getCollectionReference(userId)
                    .orderBy("started", Query.Direction.DESCENDING).limit(1)).stream().findFirst();
            if (latestSession.isEmpty()) {
                firebaseService.createOrUpdate(getCollectionReference(userId), new Session());
                return;
            }

            long diffInMs = Math.abs(latestSession.get().getLastActive().getTime() - new Date().getTime());
            long diffInMinutes = TimeUnit.MINUTES.convert(diffInMs, TimeUnit.MILLISECONDS);

            if (diffInMinutes >= STALE_SESSION_DURATION_MINUTES) {
                firebaseService.createOrUpdate(getCollectionReference(userId), new Session());
                return;
            }

            firebaseService.setField(getCollectionReference(userId), latestSession.get().getId(), "lastActive", new Date().getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private CollectionReference getCollectionReference(UserId userId) {
        return UserService.getCollectionReference().document(userId.toString()).collection("sessions");
    }
}
