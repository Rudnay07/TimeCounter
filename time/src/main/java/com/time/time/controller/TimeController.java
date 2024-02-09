package com.time.time.controller;



import com.google.firebase.auth.FirebaseAuthException;
import com.time.time.service.TimeService;
import com.time.time.user.Time;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api")
public class TimeController {
    @PostMapping("/date")
    public String saveDate(@RequestBody Time time) throws ExecutionException, InterruptedException, FirebaseAuthException {
        return TimeService.saveData(time);
    }

}
