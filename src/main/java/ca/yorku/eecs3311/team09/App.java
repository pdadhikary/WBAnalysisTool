package ca.yorku.eecs3311.team09;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        String pw = "Hello";
        String hpw = BCrypt.hashpw(pw, BCrypt.gensalt());

        System.out.println(BCrypt.checkpw(pw, hpw));
    }
}
