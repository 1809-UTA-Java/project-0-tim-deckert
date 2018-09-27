package com.revature.app;

import java.io.IOException;
import java.util.Scanner;

class UserInput {
    public static String getData() throws IOException {
        String data = null;

        try (Scanner sc = new Scanner(System.in)) {
            data = sc.nextLine();
            sc.close();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }
}