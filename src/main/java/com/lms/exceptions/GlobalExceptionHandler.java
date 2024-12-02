package com.lms.exceptions;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<Map<String, String>> handleCustomException(CustomException exception) {
		Map<String, String> error = new HashMap<>();
		error.put("code", exception.getCode());
		error.put("message", exception.getMessage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);

	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<Map<String, String>> handleDataIntegrityException(DataIntegrityViolationException exception) {
		Map<String, String> error = new HashMap<>();
		error.put("code", "500");
		error.put("message", exception.getMessage());

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
	}

	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException exception) {
		Map<String, String> error = new HashMap<>();
		error.put("code", "401");
		error.put("message", exception.getMessage());

		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
	}
}
