package com.time.time.service;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.auth.*;
import com.google.firebase.cloud.FirestoreClient;
import com.time.time.user.User;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;



@Service
public class UserService {
    private static FirebaseAuth auth = null;

    private static final String COLLECTION_NAME = "Users";

    public String saveUser(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestor = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestor.collection(COLLECTION_NAME).document(user.getName()).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();
    }

    public User getUserDetails(String id) throws ExecutionException, InterruptedException {
        Firestore dbFirestor = FirestoreClient.getFirestore();
        DocumentReference documentREF = dbFirestor.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = documentREF.get();
        DocumentSnapshot document = future.get();
        User user = null;
        if (document.exists()) {
            user = document.toObject(User.class);
            return user;
        } else {
            return null;
        }

    }
    public String updateProduct(User user) throws ExecutionException, InterruptedException {
        Firestore dbFirestor = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> collectionApiFuture = dbFirestor.collection(COLLECTION_NAME).document(user.getName()).set(user);
        return collectionApiFuture.get().getUpdateTime().toString();

    }
    public String deleteUser(String name) throws ExecutionException, InterruptedException {
        Firestore dbFirestor = FirestoreClient.getFirestore();
        ApiFuture<WriteResult> deleteApiFuture = dbFirestor.collection(COLLECTION_NAME).document(name).delete();
        return "Sikeresen törölve lett" + name+ "felhasználó";

    }


    public String registerUser(User user) throws FirebaseAuthException {

        UserRecord.CreateRequest request = new UserRecord.CreateRequest()
                .setEmail(user.getEmail())
                .setEmailVerified(false)
                .setPassword(user.getPassword())
                .setDisplayName(user.getName())
                .setDisabled(false);
        UserRecord userRecord = auth.createUser(request);



        Firestore dbFirestore = FirestoreClient.getFirestore();
        ApiFuture<DocumentReference> collectionApiFuture = dbFirestore.collection(COLLECTION_NAME).add(user);

        return "Sikeres regisztráció : " + userRecord.getUid();
    }

    public String loginUser(String email, String password) throws FirebaseAuthException {

        UserRecord userRecord2 = FirebaseAuth.getInstance().getUserByEmail(email);
        String uid = userRecord2.getUid();
        System.out.println("Felhasználó azonosító: " + uid);
        return  uid;
        /*
        try {


            String displayName = userRecord2.getDisplayName();

            String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
            System.out.println(customToken);
            String firebaseUid = FirebaseAuth.getInstance().verifyIdToken(uid).getUid();


            return "Sikeres bejelentkezés! Felhasználó: " + displayName + ", Email: " + email;
        } catch (Exception e) {
            e.printStackTrace();
            return "Hiba a bejelentkezés során: " + e.getMessage();
        }*/

    }



}
