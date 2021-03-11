package com.leverx.mediator.controller.exceptionhandler;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leverx.mediator.exception.RepositoryException;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(RepositoryException.class)
  protected ResponseEntity<RepositoryException> handleRuntimeException(
      final RepositoryException exception) {
    return new ResponseEntity<>(exception, INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(HttpClientErrorException.class)
  protected ResponseEntity<HttpClientErrorException> handleHttpClientErrorException(
      final HttpClientErrorException exception) {
    return new ResponseEntity<>(exception, exception.getStatusCode());
  }

  @ExceptionHandler(HttpServerErrorException.class)
  protected ResponseEntity<HttpServerErrorException> handleHttpServerErrorException(
      final HttpServerErrorException exception) {
    return new ResponseEntity<>(exception, exception.getStatusCode());
  }
}
