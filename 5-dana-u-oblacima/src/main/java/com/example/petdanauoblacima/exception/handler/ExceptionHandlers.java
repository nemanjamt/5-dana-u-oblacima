package com.example.petdanauoblacima.exception.handler;

import com.example.petdanauoblacima.exception.NicknameAlreadyExists;
import com.example.petdanauoblacima.exception.TeamAlreadyAssigned;
import com.example.petdanauoblacima.exception.TeamNameAlreadyExist;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class ExceptionHandlers extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        final BindingResult result = ex.getBindingResult();

        String error = result.getAllErrors().stream().map(e -> {
            if (e instanceof FieldError) {
                return ((FieldError) e).getField() + " : " + e.getDefaultMessage();
            } else {
                return e.getObjectName() + " : " + e.getDefaultMessage();
            }
        }).collect(Collectors.joining(", "));
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={NicknameAlreadyExists.class})
    protected ResponseEntity<String> handleNicknameAlreadyExists(RuntimeException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={TeamAlreadyAssigned.class})
    protected ResponseEntity<String> handleTeamAlreadyAssigned(RuntimeException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={TeamNameAlreadyExist.class})
    protected ResponseEntity<String> handleTeamNameAlreadyAssigned(RuntimeException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={EntityNotFoundException.class})
    protected ResponseEntity<String> handleEntityNotFound(RuntimeException ex, WebRequest request){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

}
