package com.example.ramesh.demo.ramesh.Exceptions;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import com.example.ramesh.demo.ramesh.Entity.ForException;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ForException except(ResourceNotFoundException excep,WebRequest webRequest )
	{
		ForException e=new ForException();
		e.setTimestamp(new Date());
		e.setMessage(excep.getMessage());
		e.setDetails(webRequest.getDescription(false));
		return e;

	}

	@ExceptionHandler(BlogApiException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ForException except(BlogApiException excep,WebRequest webRequest )
	{
		ForException e=new ForException();
		e.setTimestamp(new Date());
		e.setMessage(excep.getMessage());
		e.setDetails(webRequest.getDescription(false));
		return e;

	}


	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ForException except(Exception excep,WebRequest webRequest )
	{
		ForException e=new ForException();
		e.setTimestamp(new Date());
		e.setMessage(excep.getMessage());
		e.setDetails(webRequest.getDescription(false));
		return e;

	}

	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String,String> m(MethodArgumentNotValidException exception)
	{
		Map<String,String> error=new HashMap<>();
		
		List<FieldError> fieldErrors=exception.getBindingResult().getFieldErrors();		
		
		for(int i=0;i<fieldErrors.size();i++)
		{
			FieldError error1 = fieldErrors.get(i);
            error.put(error1.getField(), error1.getDefaultMessage());
		}
		return error;
		
	}


	@ExceptionHandler(AuthorizationDeniedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ForException except(AuthorizationDeniedException excep,WebRequest webRequest )
	{
		ForException e=new ForException();
		e.setTimestamp(new Date());
		e.setMessage(excep.getMessage());
		e.setDetails(webRequest.getDescription(false));
		return e;

	}


}
