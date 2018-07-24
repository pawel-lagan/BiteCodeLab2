package com.psk.bank.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.psk.bank.model.User;

@RestController
public class ConverterExercisesController {

	
	@GetMapping("/stringToUserUsingRequestParam")
	public ResponseEntity<Object> convertStringToUserRequestParam(@RequestParam("user") User user) {
		return ResponseEntity.ok(user);
	}

	@GetMapping("/stringToUserUsingPathVariable/{user}")
	public ResponseEntity<Object> convertStringToUserPathVariable(@PathVariable("user") User user) {
		return ResponseEntity.ok(user);
	}
	
}
