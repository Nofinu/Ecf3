package com.example.ecf3.Exeption;

public class WrongPasswordException extends Exception{
    public WrongPasswordException() {
        super("Wrong Password");
    }
}
