package com.leverx.mediator.controller.exceptionhandler;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.leverx.mediator.payload.response.MessageResponse;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

  // HttpMessageConversionException
  @ExceptionHandler(HttpClientErrorException.class)
  protected ResponseEntity<HttpClientErrorException> handleHttpClientErrorException(
      final HttpClientErrorException exception) {
    return new ResponseEntity<>(exception, BAD_REQUEST);
  }

  @ExceptionHandler(HttpServerErrorException.class)
  protected ResponseEntity<HttpServerErrorException> handleHttpServerErrorException(
      final HttpServerErrorException exception) {
    return new ResponseEntity<>(exception, BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      final MethodArgumentNotValidException ex,
      final HttpHeaders headers,
      final HttpStatus status,
      final WebRequest request) {

    StringBuffer stringBuffer = new StringBuffer();

    ex.getBindingResult()
        .getFieldErrors()
        .forEach(fieldError -> stringBuffer.append(fieldError.getDefaultMessage()).append("\n"));

    return ResponseEntity.badRequest()
        .header(new HttpHeaders().toString())
        .body(new MessageResponse(stringBuffer.toString()));
  }
}
