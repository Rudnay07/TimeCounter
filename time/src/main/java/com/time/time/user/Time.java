package com.time.time.user;


import lombok.Data;

import java.util.List;
@Data
public class Time  {

    private String Date;
    private List <String> intervall_pm;
    private List  <String> intervall_am;
    private String email;


}
