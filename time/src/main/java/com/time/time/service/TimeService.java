package com.time.time.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.cloud.FirestoreClient;
import com.time.time.user.Time;
import com.time.time.user.User;

import java.util.concurrent.ExecutionException;

public class TimeService {

    private static final String COLLECTION_NAME = "SavedDates";
    public static String itsID(String email) throws FirebaseAuthException {
        if(email!= null){
            UserRecord userRecord2 = FirebaseAuth.getInstance().getUserByEmail(email);
            String uid = userRecord2.getUid();
            return uid;
        }
        return "Nincs semmi";
    }

    public static String saveData(Time time) throws ExecutionException, InterruptedException, FirebaseAuthException {
        String uid= itsID(time.getEmail());
        uid = uid +"-"+time.getDate();
        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).document(uid).set(time);
        return collectionApiFuture.get().getUpdateTime().toString();
    }
    public Time getData(Time time ) throws ExecutionException, InterruptedException, FirebaseAuthException {
        Firestore dbFirestor = FirestoreClient.getFirestore();
        String uid= itsID(time.getEmail());
        uid = uid +"-"+time.getDate();
        DocumentReference documentREF = dbFirestor.collection(COLLECTION_NAME).document(uid);
        ApiFuture<DocumentSnapshot> future = documentREF.get();
        DocumentSnapshot document = future.get();
        Time time2 = null;
        if (document.exists()) {
            time2 = document.toObject(Time.class);
            return time2;
        } else {
            return null;
        }

    }
    public String deleteUser(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestor = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> deleteApiFuture = dbFirestor.collection(COLLECTION_NAME).document(name).delete();
        return "Sikeresen törölve lett" + name+ "felhasználó";

    }
}
