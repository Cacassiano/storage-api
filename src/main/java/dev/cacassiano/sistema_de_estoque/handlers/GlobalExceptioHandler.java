package dev.cacassiano.sistema_de_estoque.handlers;


import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

import dev.cacassiano.sistema_de_estoque.handlers.exception.NotFoundException;

@ControllerAdvice(annotations= RestController.class)
public class GlobalExceptioHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> invalidRequest(MethodArgumentNotValidException e) {
        Map<String, String> resp = new HashMap<>();
        resp.put("Message", "Invalid arguments in request");
        resp.put("Argument", e.getFieldError().getField());
        return ResponseEntity.badRequest().body(resp);
    }
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> constraintViolationHandler(DataIntegrityViolationException e) {
        Map<String, String> resp = new HashMap<>();
        resp.put("Message", e.getMessage());
        resp.put("timestamp", OffsetDateTime.now().toString());
        return ResponseEntity.internalServerError().body(resp);
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Map<String, String>> notFoundExceptionHandler(NotFoundException e) {
        Map<String, String> resp = new HashMap<>();
        resp.put("Message", e.getMessage());
        resp.put("timestamp", OffsetDateTime.now().toString());
        return ResponseEntity.internalServerError().body(resp);
    }
    // @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    // public ResponseEntity<Map<String, String>> invalidMethodHandler(HttpRequestMethodNotSupportedException e){
    //      Map<String, String> resp = new HashMap<>();
    //     resp.put("Message", e.getMessage());
    //     return ResponseEntity.status(405).body(resp);
    // }
}
