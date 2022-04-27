package ru.itis.hotel.controller.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.itis.hotel.exception.RusappServiceException;
import ru.itis.hotel.validation.dto.ValidationErrorDto;
import ru.itis.hotel.validation.dto.ValidationExceptionResponse;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ValidationExceptionResponse> handleException(MethodArgumentNotValidException exception){
        List<ValidationErrorDto> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {

            String errorMessage = error.getDefaultMessage();
            ValidationErrorDto errorDto = ValidationErrorDto.builder()
                    .message(errorMessage)
                    .build();

            if (error instanceof FieldError) {
                String fieldName = ((FieldError) error).getField();
                errorDto.setField(fieldName);
            } else {
                String objectName = error.getObjectName();
                errorDto.setObject(objectName);
            }
            errors.add(errorDto);
        });
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ValidationExceptionResponse.builder()
                .errors(errors)
                .build());

    }

    @ExceptionHandler(RusappServiceException.class)
    public final ResponseEntity<ExceptionResponse> onAccountExceptionExceptions(RusappServiceException rusappServiceException) {

        return ResponseEntity.status(rusappServiceException.getHttpStatus())
                .body(ExceptionResponse.builder()
                        .message(rusappServiceException.getMessage())
                        .exceptionName(rusappServiceException.getClass().getSimpleName())
                        .build());
    }

}
