package com.tiagodeveloper.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagodeveloper.dto.PageWrapper;
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
	public ResponseEntity<PageWrapper<UsuarioDTO>> getusuario(Pageable pageable) {
		var response = usuarioClient.getUsuarios(pageable);
		return ResponseEntity.ok(response);
	}
	
	
}
