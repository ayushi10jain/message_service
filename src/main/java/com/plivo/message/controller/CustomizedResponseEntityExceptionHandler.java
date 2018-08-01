package com.plivo.message.controller;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.plivo.message.exception.BaseHttpException;

import com.plivo.message.model.ErrorDetails;
import com.plivo.message.model.Response;

@ControllerAdvice

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	  public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
	    ErrorDetails exceptionResponse = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(false));
	    return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	  }
	
	
	
	@ExceptionHandler(BaseHttpException.class)
    public ResponseEntity<Response> invalidInput(BaseHttpException ex) {
      //  BindingResult result = ex.getBindingResult();
		System.out.println("hi>>>>>>");
        Response response = new Response();
        response.setMessage(ex.getMessage());
        response.setError(ex.getError());
       // response.setErrors(ValidationUtil.fromBindingErrors(result));
        return new ResponseEntity<Response>(response, HttpStatus.BAD_REQUEST);
    }

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
			  HttpRequestMethodNotSupportedException ex, 
			  HttpHeaders headers, 
			  HttpStatus status, 
			  WebRequest request) {
			    StringBuilder builder = new StringBuilder();
			    builder.append(ex.getMethod());
			    builder.append(
			      " method is not supported for this request. Supported methods are ");
			    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
			    ErrorDetails exceptionResponse = new ErrorDetails(new Date(), ex.getMessage(),
				        request.getDescription(false),HttpStatus.METHOD_NOT_ALLOWED,HttpStatus.METHOD_NOT_ALLOWED.value());
			    return new ResponseEntity<Object>(
			    		exceptionResponse, HttpStatus.METHOD_NOT_ALLOWED);
}
	


	 
	 

}
