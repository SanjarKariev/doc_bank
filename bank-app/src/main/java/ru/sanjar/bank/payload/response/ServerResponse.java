package ru.sanjar.bank.payload.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ServerResponse<T> {
    private final boolean success;
    private final String message;
    private final T body;

    public ServerResponse(boolean success, String message, T body) {
        this.success = success;
        this.message = message;
        this.body = body;
    }

    public ServerResponse(boolean success, T body) {
        this.success = success;
        this.message = null;
        this.body = body;
    }

    public ServerResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
        this.body = null;
    }

    public static <T> ResponseEntity<?> response(boolean success, String message, T body, HttpStatus status) throws JsonProcessingException {
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(new ServerResponse<>(success, message, body)), status);
    }

    public static <T> ResponseEntity<?> response(boolean success, String message, HttpStatus status) throws JsonProcessingException {
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(new ServerResponse<T>(success, message)), status);
    }

    public static <T> ResponseEntity<?> response(boolean success, T body, HttpStatus status) throws JsonProcessingException {
        return new ResponseEntity<>(new ObjectMapper().writeValueAsString(new ServerResponse<>(success, body)), status);
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public T getBody() {
        return body;
    }
}
