package com.tiagodeveloper.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tiagodeveloper.dto.PautaDTO;
import com.tiagodeveloper.dto.UsuarioDTO;
import com.tiagodeveloper.feign.client.UsuarioClient;
import com.tiagodeveloper.feign.wrapper.PageWrapper;
import com.tiagodeveloper.service.PautaService;


@RestController
@RequestMapping("/pauta")
public class PautaController {

	@Autowired
	private UsuarioClient usuarioClient;
	
	@Autowired
	private PautaService pautaService;
	
	
	@GetMapping
	public ResponseEntity<Page<PautaDTO>> getAll(Pageable pageable) {
		return ResponseEntity.ok(pautaService.getAll(pageable));
	}
	
	@PostMapping
	public ResponseEntity<PautaDTO> create(@RequestBody PautaDTO pautaDTO) {
		return new ResponseEntity<>(pautaService.create(pautaDTO), HttpStatus.CREATED);
	}
	
	
	@GetMapping("/usuario")
	public ResponseEntity<PageWrapper<UsuarioDTO>> getusuario(Pageable pageable) {
		var response = usuarioClient.getUsuarios(pageable);
		return ResponseEntity.ok(response);
	}
	
	
}
