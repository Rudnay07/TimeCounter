package com.time.time.firebase;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


@Service
public class FirebaseInitialization {
    @PostConstruct
    public void init(){
        FileInputStream serviceAccount = null;
    try{
                serviceAccount=new FileInputStream("./serviceAccountKey.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://timer-ea0ac-default-rtdb.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);
    }catch (FileNotFoundException e){
        e.printStackTrace();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }


    }
}
