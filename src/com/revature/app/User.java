package com.revature.app;

import java.util.ArrayList;

public class User {
    protected String username;
    protected String password;
    //protected ArrayList <Account> accounts;

    User () {}
    User (String username, String password) {
        this.username = username;
        this.password = password;
        
    }

}