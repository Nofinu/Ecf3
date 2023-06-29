package Exeption;

public class UserExistException extends Exception{
    public UserExistException() {
        super("User Already Exist");
    }
}
