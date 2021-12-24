package nl.hsleiden.IPRWC.controllers;

import nl.hsleiden.IPRWC.httpResponses.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;
import java.util.NoSuchElementException;

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSQLException(SQLException sqle) {
        return new ResponseEntity<>("SQLException " + sqle.getSQLState() + ": " + sqle.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> handleElementDoesNotExist(NoSuchElementException nsee) {
        return this.returnBadRequest("This item does not exist");
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return returnBadRequest("Body is empty or incorrect");
    }

    private ResponseEntity<Object> returnBadRequest(String error) {
        return ResponseEntity.badRequest().body(new Response(HttpStatus.BAD_REQUEST.value(), error));
    }

    private ResponseEntity<Object> returnUnauthorized(String error) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Response(HttpStatus.UNAUTHORIZED.value(), error));
    }
}
