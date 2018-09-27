package com.revature.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

class Bank {
    final private static String IntroMessage = "Welcome to Tim's bank.\nAre you a new user?(y/n) ";
    public static void main(String[] args) {
        System.out.print(IntroMessage);
        if(UserInput.getData().equals("y")) {
            UserFactory.createUser();
        }
        else {

        }
    }
}