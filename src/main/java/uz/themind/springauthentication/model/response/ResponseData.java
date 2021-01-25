package uz.themind.springauthentication.model.response;

import org.springframework.http.HttpStatus;
import uz.themind.springauthentication.model.CustomError;

import java.util.Date;
import java.util.List;

public class ResponseData<T> {

    private HttpStatus code;
    private boolean success;
    private String message;
    private T data;
    private Date timestamp;
    private List<CustomError> errors;

    public ResponseData() {
    }

    public ResponseData(HttpStatus code, boolean success, String message, T data, Date timestamp) {
        this.code = code;
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = timestamp;
    }

    public ResponseData(HttpStatus code, boolean success, T data, Date timestamp) {
        this.code = code;
        this.success = success;
        this.data = data;
        this.timestamp = timestamp;
    }

    public ResponseData(HttpStatus code, String message, Date timestamp) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
    }

    public ResponseData(HttpStatus code, String message, Date timestamp, List<CustomError> errors) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.errors = errors;
    }

    public HttpStatus getCode() {
        return code;
    }

    public void setCode(HttpStatus code) {
        this.code = code;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public List<CustomError> getErrors() {
        return errors;
    }

    public void setErrors(List<CustomError> errors) {
        this.errors = errors;
    }
}
