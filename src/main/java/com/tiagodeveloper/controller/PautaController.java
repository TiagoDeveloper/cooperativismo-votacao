package com.tiagodeveloper.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	
	
	@GetMapping
	public ResponseEntity<Map<String, String>> getValue() {
		var response = new HashMap<String, String>();
		response.put("value", "Pauta");
		return ResponseEntity.ok(response);
	}
	
	
}
