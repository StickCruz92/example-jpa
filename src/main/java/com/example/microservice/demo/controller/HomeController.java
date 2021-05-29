package com.example.microservice.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/hola")
	public String hello(@RequestParam(value = "name", defaultValue = "Mundo") String name) {
	return String.format("Hola %s!", name);
	
	}
}
