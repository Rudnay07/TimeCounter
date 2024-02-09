package com.time.time.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import com.google.firebase.auth.UserRecord;
import com.time.time.service.UserService;
import com.time.time.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserService userService;

    /*@PostMapping("/user")
    public String saveUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.saveUser(user);
    }*/
    @GetMapping("/user/{name}")
    public User getProduct(@PathVariable String name) throws ExecutionException, InterruptedException{
        return userService.getUserDetails(name);
    }
    @PutMapping("/user")
    public String update(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.updateProduct(user);
    }
    @DeleteMapping("/user/{name}")
    public String deleteUsers(@PathVariable String name) throws ExecutionException, InterruptedException{
        return userService.deleteUser(name);
    }
    /*@PostMapping("/login")
    public String login(@RequestBody User user) {

            try {
                FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
                String uid = decodedToken.getUid();
                // Itt további logikát implementálhatsz, például az adatbázisban ellenőrizve a felhasználó adatait
                return "Sikeres bejelentkezés, UID: " + uid;
            } catch (FirebaseAuthException e) {
                e.printStackTrace();
                return "Hibás bejelentkezési adatok";
            }
        }*

     */
    @PostMapping("/login")
    public String login(@RequestBody String idToken) {
        try {
            FirebaseToken decodedToken = FirebaseAuth.getInstance().verifyIdToken(idToken);
            String uid = decodedToken.getUid();
            // Itt további logikát implementálhatsz, például az adatbázisban ellenőrizve a felhasználó adatait
            return "Sikeres bejelentkezés, UID: " + uid;
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
            return "Hibás bejelentkezési adatok";
        }
    }
    /*
        firebase.auth().signInWithEmailAndPassword(email, password)
      .then((userCredential) => {
        // Sikeres bejelentkezés
        const user = userCredential.user;
        const idToken = user && (await user.getIdToken());

        // Most az "idToken" változó tartalmazza az ID token-t, amit továbbíthatsz a szerveroldali alkalmazásnak
      })
      .catch((error) => {
        // Bejelentkezési hiba
        const errorCode = error.code;
        const errorMessage = error.message;
        console.error(`${errorCode}: ${errorMessage}`);
      });

     */

        /*System.out.println(user.getEmail());

        try {
            boolean isAuthenticated = Boolean.parseBoolean(userService.loginUser(user.getEmail(),user.getPassword()));
            if (isAuthenticated) {
                return ResponseEntity.ok("Login successful");
            } else {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Valami nem jó");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error during login: " + e.getMessage());
        }*/




    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        try {
            return userService.registerUser(user);
        } catch (Exception e) {
            return "Registration failed: " + e.getMessage();
        }
    }

}
