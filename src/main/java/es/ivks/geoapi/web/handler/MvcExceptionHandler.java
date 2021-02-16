package es.ivks.geoapi.web.handler;

import es.ivks.geoapi.web.response.GeoApiExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: guillem.casas
 * @version: 15/02/2021
**/
@Slf4j
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GeoApiExceptionResponse> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception){

        log.error(exception.getMessage());

        GeoApiExceptionResponse apiResponse = GeoApiExceptionResponse.builder()
                                                                        .status(HttpStatus.BAD_REQUEST.name())
                                                                        .error(exception.getMessage())
                                                                        .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.METHOD_NOT_ALLOWED);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<GeoApiExceptionResponse> missinRequestParameterExceptionHandler(MissingServletRequestParameterException exception){

        log.error(exception.getMessage());

        GeoApiExceptionResponse apiResponse = GeoApiExceptionResponse.builder()
                                                                        .status(HttpStatus.BAD_REQUEST.name())
                                                                        .error(exception.getMessage())
                                                                        .build();

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GeoApiExceptionResponse> argumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException exception){

        String errorMethod = exception.getParameter().getMethod().getName();
        String error = String.format("Invalid value for parameter %s", exception.getName());

        GeoApiExceptionResponse apiResponse = GeoApiExceptionResponse.builder()
                                                                        .status(HttpStatus.BAD_REQUEST.name())
                                                                        .error(error)
                                                                        .build();

        log.error( errorMethod + " - " + error + ": "+ exception.getCause());

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeoApiExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){

        String error = exception.getFieldError().getDefaultMessage();
        GeoApiExceptionResponse apiResponse = GeoApiExceptionResponse.builder()
                                                                        .status(HttpStatus.BAD_REQUEST.name())
                                                                        .error(error)
                                                                        .build();

        return new ResponseEntity(apiResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<GeoApiExceptionResponse> constraintViolationExceptionHandler(ConstraintViolationException exception){

        List<String> errors = new ArrayList<>(exception.getConstraintViolations().size());

        exception.getConstraintViolations().forEach(
                error -> errors.add(error.getPropertyPath() + ": " + error.getMessage())
        );

        GeoApiExceptionResponse apiResponse = GeoApiExceptionResponse.builder()
                                                                        .status(HttpStatus.BAD_REQUEST.name())
                                                                        .error(errors)
                                                                        .build();

        log.error(errors.get(0));

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundExceptionHandler(NotFoundException exception){

        log.warn("Entity not found");
        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
