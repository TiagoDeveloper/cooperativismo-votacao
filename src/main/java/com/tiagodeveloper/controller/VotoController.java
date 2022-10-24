package com.tiagodeveloper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagodeveloper.dto.VotoDTO;
import com.tiagodeveloper.service.VotoService;

@RestController
@RequestMapping("/voto")
public class VotoController {
	
	@Autowired
	private VotoService votoService;
	
	@PostMapping
	public ResponseEntity<VotoDTO> create(@RequestBody VotoDTO votoDTO) {
		return new ResponseEntity<>(votoService.create(votoDTO), HttpStatus.CREATED);
	}

}
