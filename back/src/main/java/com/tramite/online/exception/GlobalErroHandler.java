package com.tramite.online.exception;

import java.net.URI;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


/**
 * GlobalErroHandler centralizes exception handling, providing meaningful 
 * error responses with appropriate HTTP status codes and detailed descriptions.
 */

@RestControllerAdvice
public class GlobalErroHandler extends ResponseEntityExceptionHandler{

    private final Logger logger = LoggerFactory.getLogger(GlobalErroHandler.class);

  private static final URI NOT_FOUND_TYPE = URI.create("https://api.bookstore.com/errors/not-found");
  private static final URI ISE_FOUND_TYPE = URI.create("https://api.bookstore.com/errors/server-error");
  private static final URI BAD_REQUEST_TYPE = URI.create("https://api.bookstore.com/errors/bad-request");
  private static final String SERVICE_NAME = "order-service";
   
  


    @ExceptionHandler(ResourceNotFound.class)
    ResponseEntity<ProblemDetail> handleResourceNotFound(ResourceNotFound ex){
      logger.info("Handle Resource Not Found {}", ex.getMessage());
      ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
      problemDetail.setTitle(ex.getMessage());
      problemDetail.setProperty("time", Instant.now());
      problemDetail.setType(NOT_FOUND_TYPE);
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(problemDetail);

    }

    @ExceptionHandler(ResourceFound.class)
    ResponseEntity<ProblemDetail> handleResourceFound(ResourceFound ex){
      logger.info("HandlerResourceFound  {}", ex.getMessage());
      ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.CONFLICT,
      ex.getMessage());
      problemDetail.setTitle(ex.getMessage());
      problemDetail.setProperty("time", Instant.now());
      problemDetail.setType(ISE_FOUND_TYPE);
      return ResponseEntity.status(HttpStatus.CONFLICT).body(problemDetail);
    }

    @ExceptionHandler(OAuth2AuthenticationProcessingException.class)
    ResponseEntity<ProblemDetail> handleOAuth2AuhtenticationProcessException(OAuth2AuthenticationProcessingException ex){
      logger.info("Handle OAuth2AuthenticationProcessException {}", ex.getMessage());
      ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR,
      ex.getMessage());
      problemDetail.setTitle(ex.getMessage());
      problemDetail.setProperty("time", Instant.now());
      problemDetail.setType(ISE_FOUND_TYPE);
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(problemDetail);
    }

    @ExceptionHandler(BadRequestException.class) 
    protected ResponseEntity<Object> handleBadRequest(BadRequestException ex){
      logger.info("BanRequestException : {}" , ex.getMessage());
      ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST ,ex.getMessage());
      problemDetail.setTitle(ex.getMessage());
      problemDetail.setProperty("time", Instant.now());
      problemDetail.setType(ISE_FOUND_TYPE);
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }


    /**
     * Puesto existe el metodo MethodArgumentNotValidException
     * en el clase ResponseEntityExceptionHandler
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        logger.info("HandlerArgumentNotValid  {}", ex.getMessage());
        
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, "Validation failed");
        problemDetail.setTitle("Validation Error");
        problemDetail.setProperty("time", Instant.now());
        problemDetail.setType(URI.create("https://api.bookstore.com/errors/validation"));
    
        List<String> errors = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(fieldName + ":" + errorMessage);
        });
        problemDetail.setProperty("errors", errors);
    
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(problemDetail);
    }

    
         
  

    
}
