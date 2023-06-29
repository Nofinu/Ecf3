package Exeption;

public class WrongPasswordException extends Exception{
    public WrongPasswordException() {
        super("Wrong Password");
    }
}
