package com.sale.shopping.error;

import com.sale.shopping.model.dto.CommonDTO;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalAccessException.class)
    public ResponseEntity<Object> illegalAccessException(IllegalArgumentException e){
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.BAD_REQUEST.value()).data(e.getMessage()).build(),HttpStatus.BAD_REQUEST);


    }


    //delete 에러
    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> emptyResultDataAccessException(EmptyResultDataAccessException e){
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.BAD_REQUEST.value()).data(e.getMessage()).build(),HttpStatus.BAD_REQUEST);


    }
    //select 에러
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElementException(NoSuchElementException e){
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.BAD_REQUEST.value()).data(e.getMessage()).build(),HttpStatus.BAD_REQUEST);


    }

    //insert에러
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> dataIntegrityViolationException(DataIntegrityViolationException e){
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.BAD_REQUEST.value()).data(e.getMessage()).build(),HttpStatus.BAD_REQUEST);


    }


    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> constraintViolationException(ConstraintViolationException e){
        return new ResponseEntity<>(CommonDTO.builder().statusCode(HttpStatus.BAD_REQUEST.value()).data(e.getMessage()).build(),HttpStatus.BAD_REQUEST);


    }
}
