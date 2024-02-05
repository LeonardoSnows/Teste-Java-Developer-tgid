package br.com.leonardo.bonifacio.neves.teste_backend_java_developer.infra;

import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions.CompanyNotFound;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions.InvalidBalanceClientException;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions.InvalidBalanceCompanyException;
import br.com.leonardo.bonifacio.neves.teste_backend_java_developer.exceptions.InvalidValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(CompanyNotFound.class)
    private ResponseEntity<RestErrorMsg> userNotFoundHandler(CompanyNotFound exception) {
        RestErrorMsg restErrorMsg = new RestErrorMsg(HttpStatus.NOT_FOUND, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(restErrorMsg);
    }

    @ExceptionHandler(InvalidBalanceClientException.class)
    private ResponseEntity<RestErrorMsg> userNotFoundHandler(InvalidBalanceClientException exception) {
        RestErrorMsg restErrorMsg = new RestErrorMsg(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(restErrorMsg);
    }

    @ExceptionHandler(InvalidBalanceCompanyException.class)
    private ResponseEntity<RestErrorMsg> userNotFoundHandler(InvalidBalanceCompanyException exception) {
        RestErrorMsg restErrorMsg = new RestErrorMsg(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(restErrorMsg);
    }

    @ExceptionHandler(InvalidValueException.class)
    private ResponseEntity<RestErrorMsg> userNotFoundHandler(InvalidValueException exception) {
        RestErrorMsg restErrorMsg = new RestErrorMsg(HttpStatus.NOT_ACCEPTABLE, exception.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(restErrorMsg);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> validationFields(MethodArgumentNotValidException ex) {
        RestErrorMsg restErrorMsg = new RestErrorMsg(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
        Map<String, String> erropMap = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(erd -> {
            String erroField = ((FieldError) erd).getField();
            String errorMsg = erd.getDefaultMessage();
            erropMap.put(erroField, errorMsg);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erropMap);
    }


}
