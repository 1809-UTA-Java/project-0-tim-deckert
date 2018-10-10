package com.revature.app;

import java.io.Serializable;

public class Account implements Serializable {
    private Integer accountNumber;
    private Integer employeeNumber; 
    private double funds;
    
    Account() {}

	public Account(Integer accountNumber, Integer employeeNumber, double funds) {
		super();
		this.accountNumber = accountNumber;
		this.employeeNumber = employeeNumber;
		this.funds = funds;
	}
	
	public void displayAccount () {
		System.out.println("Account number: "+accountNumber+"\n"+"Available Funds: "+funds);
	}
	
	public void alterFunds (double amount) {
		funds += amount;
	}
}