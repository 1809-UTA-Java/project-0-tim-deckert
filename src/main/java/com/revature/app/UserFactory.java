package com.revature.app;

public class UserFactory {
    public static User createUser() {
    	String[] user_pass = UserInput.getNewUser();
    	UserInput.closeInput();
    	
    	User user = new User(user_pass[0], user_pass[1]);

        Users.addUser(user_pass[0], user);
        return user;
    }
    
    public static Employee createEmployee() {
     	String[] user_pass = UserInput.getNewUser();
     	UserInput.closeInput();
     	
     	Employee employee = new Employee(user_pass[0], user_pass[1]);
     	Users.addUser(user_pass[0], employee);
    	return employee;
     }
    
     public static Admin createAdmin() {
     	 String[] user_pass = UserInput.getNewUser();
     	 UserInput.closeInput();
     	 
     	 Admin admin = new Admin(user_pass[0], user_pass[1]);
     	 Users.addUser(user_pass[0], admin);
     	 return admin;
     }
}