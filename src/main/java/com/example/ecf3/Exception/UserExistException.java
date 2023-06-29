package com.example.ecf3.Exception;

public class UserExistException extends Exception{
    public UserExistException() {
        super("User Already Exist");
    }
}
