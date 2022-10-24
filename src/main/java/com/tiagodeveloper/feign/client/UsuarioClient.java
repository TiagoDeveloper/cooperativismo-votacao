package com.tiagodeveloper.feign.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tiagodeveloper.dto.UsuarioDTO;
import com.tiagodeveloper.feign.wrapper.PageWrapper;

@FeignClient(name = "usuario",url = "${usuario.client.url}")
public interface UsuarioClient {

	@GetMapping("/usuario")
	PageWrapper<UsuarioDTO> getUsuarios();
	
	@GetMapping("/usuario")
	PageWrapper<UsuarioDTO> getUsuarios(Pageable pageable);
	
	@GetMapping("/usuario/{id}")
	UsuarioDTO getById(@PathVariable("id") Integer id);
	
	
}
