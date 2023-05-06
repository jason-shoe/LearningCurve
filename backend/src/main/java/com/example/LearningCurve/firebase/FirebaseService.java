package com.example.LearningCurve.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class FirebaseService<T extends Model> {

    public String createOrUpdate(String colName,
                                 T object) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture =
                dbFirestore.collection(colName).document(object.getId()).set(
                        object);
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public DocumentSnapshot get(String colName,
                                String id) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        DocumentReference documentReference =
                dbFirestore.collection(colName).document(id);
        ApiFuture<DocumentSnapshot> future = documentReference.get();

        DocumentSnapshot document = future.get();

        // TODO: try to move the converter into here

        return document;
    }

    public List<String> getAllIds(String colName) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        QuerySnapshot querySnapshot =
                dbFirestore.collection(colName).get().get();
        return querySnapshot.getDocuments().stream().map(QueryDocumentSnapshot::getId).collect(Collectors.toList());
    }
}
