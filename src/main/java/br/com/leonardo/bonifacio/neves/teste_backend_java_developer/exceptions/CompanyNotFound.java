package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions;

public class CompanyNotFound extends RuntimeException {
    public CompanyNotFound() {
        super("Empresa não está cadastrada");
    }

    public CompanyNotFound(String msg) {
        super(msg);
    }
}
