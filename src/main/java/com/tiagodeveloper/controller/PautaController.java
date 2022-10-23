package com.tiagodeveloper.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagodeveloper.dto.UsuarioDTO;
import com.tiagodeveloper.feign.client.UsuarioClient;

@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private UsuarioClient usuarioClient;
	
	
	@GetMapping
	public ResponseEntity<Map<String, String>> getValue() {
		var response = new HashMap<String, String>();
		response.put("value", "Pauta");
		return ResponseEntity.ok(response);
	}
	@GetMapping("/usuario")
	public ResponseEntity<List<UsuarioDTO>> getusuario() {
		var response =usuarioClient.getUsuarios();
		return ResponseEntity.ok(response);
	}
	
	
}
