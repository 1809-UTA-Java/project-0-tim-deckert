package com.revature.app;

import java.util.HashMap;

public class Users {
    private static HashMap <String, User> masterUserList = new HashMap<>();

    public static boolean contains(String uname) {
        return masterUserList.containsKey(uname);
    }
    
    public static boolean contains(String uname, String pword) {
    	User user;
    	if ((user = masterUserList.get(uname)) != null) {
    		return user.checkPassword(pword);
    	}
    	return false;
    }

    public static void addUser(String uname, User newUser) {
        masterUserList.put(uname, newUser);
    }
    
    public static User verify(String uname, String pword) {
    	if (Users.contains(uname, pword)) {
    		User user = masterUserList.get(uname);
    		return user;
    	}
    	return null;
    }

}