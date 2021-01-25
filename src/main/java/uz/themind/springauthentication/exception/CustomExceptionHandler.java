package uz.themind.springauthentication.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import uz.themind.springauthentication.model.CustomError;
import uz.themind.springauthentication.model.response.ResponseData;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> fieldsValidationHandler(MethodArgumentNotValidException exception) {
        List<CustomError> customErrors = exception.getFieldErrors().stream().map(error ->
                new CustomError(error.getField(), error.getDefaultMessage())).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ResponseData<List<CustomError>>(HttpStatus.BAD_REQUEST, "Validation errors!", new Date(), customErrors));
    }

    @ExceptionHandler(value = EmailAlreadyExistsException.class)
    public ResponseEntity<?> emailAlreadyExistExceptionHandler(EmailAlreadyExistsException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseData<>(HttpStatus.BAD_REQUEST, exception.getMessage(), new Date()));
    }

    @ExceptionHandler(value = EmailConnectionException.class)
    public ResponseEntity<?> emailConnectionExceptionHandler(EmailConnectionException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseData<>(HttpStatus.INTERNAL_SERVER_ERROR, exception.getMessage(), new Date()));
    }
}
