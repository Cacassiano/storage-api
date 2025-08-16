package dev.cacassiano.sistema_de_estoque.handlers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;

@ControllerAdvice
public class GlobalExceptioHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> invalidRequest(MethodArgumentNotValidException e) {
        Map<String, String> resp = new HashMap<>();
        resp.put("Message", "Invalid arguments in request");
        resp.put("Argument", e.getFieldError().getField());
        return ResponseEntity.badRequest().body(resp);
    }

}
