package com.example.ecf3.Exception;

public class WrongPasswordException extends Exception{
    public WrongPasswordException() {
        super("Wrong Password");
    }
}
