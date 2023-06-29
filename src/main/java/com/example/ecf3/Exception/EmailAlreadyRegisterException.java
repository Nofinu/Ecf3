package com.example.ecf3.Exception;

public class EmailAlreadyRegisterException extends Exception{
    public EmailAlreadyRegisterException() {
        super("Email Already Exist");
    }
}
