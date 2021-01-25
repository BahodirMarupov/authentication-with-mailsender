package uz.themind.springauthentication.exception;

public class EmailConnectionException extends RuntimeException{
    public EmailConnectionException(String email) {
        super("Error in connecting with email "+email);
    }
}
