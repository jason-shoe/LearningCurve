package com.example.LearningCurve.firebase;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class FirebaseService<I extends ModelId, T extends Model<I>> {

    private final String colName;
    private final Class<T> clazz;

    private ObjectMapper mapper;

    public FirebaseService(String colName, Class<T> clazz) {
        this.colName = colName;
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(ModelId.class, new ModelIdSerializer());
        objectMapper.registerModule(module);
        this.mapper = objectMapper;
        this.clazz = clazz;
    }

    public String createOrUpdate(T object) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionsApiFuture =
                dbFirestore.collection(colName).document(object.getId().toString()).set(
                        serialize(object));
        return collectionsApiFuture.get().getUpdateTime().toString();
    }

    public List<String> create(List<T> objects) throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();

        WriteBatch batch = dbFirestore.batch();

        objects.stream().forEach(object ->
                batch.set(dbFirestore.collection(colName).document(object.getId().toString()), serialize(object))
        );

        ApiFuture<List<WriteResult>> result = batch.commit();
        return result.get().stream().map(WriteResult::getUpdateTime).map(Object::toString).collect(Collectors.toList());
    }

    public T get(I id) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference documentReference =
                    dbFirestore.collection(colName).document(id.toString());
            ApiFuture<DocumentSnapshot> future = documentReference.get();

            DocumentSnapshot document = future.get();

            if (document.exists()) {
                return deserialize(document.getData());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<T> get(List<I> ids) {
        try {
            Firestore dbFirestore = FirestoreClient.getFirestore();
            DocumentReference[] references = ids.stream().map(id -> dbFirestore.collection(colName).document(id.toString())).toArray(size -> new DocumentReference[size]);
            ApiFuture<List<DocumentSnapshot>> documentSnapshots = dbFirestore.getAll(references);
            return documentSnapshots.get().stream().map(DocumentSnapshot::getData).map(this::deserialize).collect(Collectors.toList());
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    public List<String> getAllIds() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        QuerySnapshot querySnapshot =
                dbFirestore.collection(colName).get().get();
        return querySnapshot.getDocuments().stream().map(QueryDocumentSnapshot::getId).collect(Collectors.toList());
    }

    public List<T> getAllObjects() throws InterruptedException, ExecutionException {
        Firestore dbFirestore = FirestoreClient.getFirestore();
        QuerySnapshot querySnapshot =
                dbFirestore.collection(colName).get().get();
        return querySnapshot
                .getDocuments()
                .stream()
                .map(QueryDocumentSnapshot::getData)
                .map(this::deserialize)
                .collect(Collectors.toList());
    }

    private Map<String, Object> serialize(T object) {
        return mapper.convertValue(object, new TypeReference<Map<String, Object>>() {
        });
    }

    private T deserialize(Map<String, Object> fields) {
        return mapper.convertValue(fields, clazz);
    }
}
