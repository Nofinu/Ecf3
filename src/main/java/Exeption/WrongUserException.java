package Exeption;

public class WrongUserException extends Exception{
    public WrongUserException() {
        super("Wrong User");
    }
}
