package es.ivks.geoapi.web.handler;

import es.ivks.geoapi.web.response.GeoApiExceptionResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author: guillem.casas
 * @version: 15/02/2021
**/
@Slf4j
@ControllerAdvice
public class MvcExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GeoApiExceptionResponse> methodNotSupportedExceptionHandler(HttpRequestMethodNotSupportedException exception){

        GeoApiExceptionResponse apiResponse = GeoApiExceptionResponse.builder()
                                                                        .status(HttpStatus.BAD_REQUEST.name())
                                                                        .error(exception.getMessage())
                                                                        .build();

        log.warn(exception.getMessage());

        return new ResponseEntity<>(apiResponse, HttpStatus.METHOD_NOT_ALLOWED);

    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<GeoApiExceptionResponse> missinRequestParameterExceptionHandler(MissingServletRequestParameterException exception){

        GeoApiExceptionResponse apiResponse = GeoApiExceptionResponse.builder()
                                                                        .status(HttpStatus.BAD_REQUEST.name())
                                                                        .error(exception.getMessage())
                                                                        .build();

        log.warn(exception.getMessage());

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<GeoApiExceptionResponse> argumentTypeMismatchExceptionHandler(MethodArgumentTypeMismatchException exception){

        String error = String.format("Invalid value for parameter %s", exception.getName());

        GeoApiExceptionResponse apiResponse = GeoApiExceptionResponse.builder()
                                                                        .status(HttpStatus.BAD_REQUEST.name())
                                                                        .error(error)
                                                                        .build();

        String logMessage = getMismatchExceptionLogMessage(exception, error);
        log.warn(logMessage);

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GeoApiExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException exception){

        FieldError fieldError = exception.getFieldError();
        String error = null;

        if(Objects.nonNull(fieldError)) {
            error = fieldError.getDefaultMessage();
        }

        GeoApiExceptionResponse apiResponse = GeoApiExceptionResponse.builder()
                                                                        .status(HttpStatus.BAD_REQUEST.name())
                                                                        .error(error)
                                                                        .build();

        log.warn(error);

        return new ResponseEntity<>(apiResponse, HttpStatus.BAD_REQUEST);

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
    public ResponseEntity<GeoApiExceptionResponse> notFoundExceptionHandler(NotFoundException exception){

        log.warn("Entity not found");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    private String getMismatchExceptionLogMessage(@NonNull MethodArgumentTypeMismatchException exception, String error) {

        Method method = exception.getParameter().getMethod();
        StringBuilder logMessage = new StringBuilder();

        if(Objects.nonNull(method) && Objects.nonNull(method.getDeclaringClass())){

            logMessage.append(method.getDeclaringClass().getSimpleName())
                      .append(" - ")
                      .append(method.getName()).append(": ")
                      .append(error)
                      .append(", ")
                      .append(exception.getCause());

        }

        return String.valueOf(logMessage);

    }

}
