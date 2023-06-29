package Exeption;

public class NotLoggedException extends Exception{
    public NotLoggedException() {
        super("No User Logged");
    }
}
