package com.example.ecf3.Exception;

public class FieldEmptyException extends Exception{
    public FieldEmptyException(String message) {
        super(message);
    }

    public static FieldEmptyException with(String... fields) {
        String message = "Need Fields : ";
        for(String f : fields) {
            message += f + " ";
        }

        return new FieldEmptyException(message);
    }
}
