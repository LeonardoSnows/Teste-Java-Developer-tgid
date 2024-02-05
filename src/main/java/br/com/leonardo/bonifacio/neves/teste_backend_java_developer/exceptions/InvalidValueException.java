package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions;

public class InvalidValueException extends RuntimeException {
    public InvalidValueException() {
        super("O valor da taxa é superior ao valor enviado para saque.");
    }

    public InvalidValueException(String msg) {
        super(msg);
    }
}
