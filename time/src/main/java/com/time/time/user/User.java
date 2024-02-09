package com.time.time.user;

import lombok.Data;
import org.mindrot.jbcrypt.BCrypt;
@Data
public class User {
    private String name;
    private String email;
    private String password;
    private String firebaseToken;




}
