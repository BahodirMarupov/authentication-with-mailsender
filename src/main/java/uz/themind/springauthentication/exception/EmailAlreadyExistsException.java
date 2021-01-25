package uz.themind.springauthentication.exception;

public class EmailAlreadyExistsException extends RuntimeException{
    public EmailAlreadyExistsException(String email) {
        super(String.format("Already signed up with email %s",email));
    }
}
