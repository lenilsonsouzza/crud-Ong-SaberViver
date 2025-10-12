package saberViver.com.appSaberviver.controllers.handlers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import saberViver.com.appSaberviver.dto.CustomError;
import saberViver.com.appSaberviver.dto.ValidacaoERRO;
import saberViver.com.appSaberviver.servicos.exceptions.ResourceNotFoundException;

import java.time.Instant;
@ControllerAdvice
public class ControllerExceptionHandler {
@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        CustomError err = new CustomError(Instant.now(), status.value(), e.getMessage(),request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
       ValidacaoERRO err = new ValidacaoERRO(Instant.now(), status.value(), "Dados Invalidos",request.getRequestURI());
      for(FieldError f : e.getBindingResult().getFieldErrors()){
          err.addError(f.getField(),f.getDefaultMessage());
      }
        return ResponseEntity.status(status).body(err);
    }


}
