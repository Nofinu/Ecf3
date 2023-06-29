package Exeption;

public class UserNotExistException extends Exception{
    public UserNotExistException() {
        super("User Not Exist");
    }
}
