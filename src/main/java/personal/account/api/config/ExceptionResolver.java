package personal.account.api.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.LinkedHashMap;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class ExceptionResolver {
    private Logger log = LoggerFactory.getLogger(ExceptionResolver.class);

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e, WebRequest request) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status",400);
        response.put("success",false);
        response.put("message", "Bad Request");
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status",404);
        response.put("success",false);
        response.put("message", "Not Found");
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity HttpMediaTypeNotAcceptableException(HttpMediaTypeNotAcceptableException e) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status",404);
        response.put("success",false);
        response.put("message", "Could not find required response format");
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity HttpMessageNotReadableExceptionMethod(HttpMessageNotReadableException e, WebRequest request) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();;
        response.put("status",400);
        response.put("success",false);
        response.put("message", "Provided data cannot be empty");
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e, WebRequest request) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status",400);
        response.put("success",false);
        response.put("message", "Method not allowed");
        return ResponseEntity.status(400).body(response);
    }

    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity NoSuchElementException(NoSuchElementException e, WebRequest request) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status",404);
        response.put("success",false);
        response.put("message", "Required data not found");
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(TransactionSystemException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity TransactionSystemException(TransactionSystemException e, WebRequest request) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status",400);
        response.put("success",false);
        response.put("message", "Max 9 digits are allowed for accountNumber and 7 digits for accountCode");
        return ResponseEntity.status(404).body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity NumberFormatException(Exception e, WebRequest request) {
        LinkedHashMap<String, Object> response = new LinkedHashMap<>();
        response.put("status",400);
        response.put("success",false);
        response.put("message", "Something went wrong please try again later...");
        log.error("handled exception resolver");
        e.printStackTrace();
        return ResponseEntity.status(404).body(response);
    }
}
