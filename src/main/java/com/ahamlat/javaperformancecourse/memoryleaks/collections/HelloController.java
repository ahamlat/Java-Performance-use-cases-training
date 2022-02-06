package com.ahamlat.javaperformancecourse.memoryleaks.collections;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HelloController {

	public List<String> listOfMessages = new ArrayList<>();

	@RequestMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping(value = "/greeting")
	String getId(@RequestParam String personName){
		String message = String.format("Greetings from Spring Boot to %s !", personName);
		listOfMessages.add(message);
		return message;
	}



}
