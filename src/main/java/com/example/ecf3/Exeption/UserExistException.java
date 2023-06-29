package com.example.ecf3.Exeption;

public class UserExistException extends Exception{
    public UserExistException() {
        super("User Already Exist");
    }
}
