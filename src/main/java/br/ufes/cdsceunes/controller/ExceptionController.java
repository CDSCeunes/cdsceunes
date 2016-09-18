package br.ufes.cdsceunes.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.jsonwebtoken.ExpiredJwtException;

import org.springframework.http.HttpStatus;

@Controller
public class ExceptionController {

	@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "jwt token expired")
	@ExceptionHandler(ExpiredJwtException.class)
	public void expired() {
	}
	
}
