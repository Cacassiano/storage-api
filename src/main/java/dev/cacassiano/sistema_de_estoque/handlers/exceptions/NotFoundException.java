package dev.cacassiano.sistema_de_estoque.handlers.exceptions;

public class NotFoundException extends Exception {
    String message;
    public NotFoundException(String message) {
        this.message = message;
    }
    @Override
    public String getMessage() {
        return this.message;
    }
}
