package com.psk.bank.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExercisesConsumeProduceController {

	
	@PostMapping(value="/consumeProduceApi", consumes=MediaType.APPLICATION_XML_VALUE,produces=MediaType.APPLICATION_XML_VALUE)
	@ResponseBody
	public String consumeXml(@RequestBody String xml){
		return "Consume xml";
	}
	
	@PostMapping(value="/consumeProduceApi", consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
	public String consumeJson(@RequestBody String xml){
		return "Consume json";
	}
	
	@PostMapping(value="/consumeProduceApi", consumes=MediaType.TEXT_PLAIN_VALUE,produces=MediaType.TEXT_PLAIN_VALUE)
	public String consumeYaml(@RequestBody String xml){
		return "Consume text";
	}
	
}
