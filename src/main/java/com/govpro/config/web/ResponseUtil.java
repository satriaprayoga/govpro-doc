package com.govpro.config.web;

import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public final class ResponseUtil {

	private ResponseUtil(){}
	
	public static <T> ResponseEntity<T> wrapOrNotFound(Optional<T> response){
		return wrapOrNotFound(response,null);
	}
	
	public static <T> ResponseEntity<T> wrapOrNotFound(Optional<T> response, HttpHeaders header){
		return response.map(resp->ResponseEntity.ok().headers(header).body(resp)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
}
