package com.tiagodeveloper.feign.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.tiagodeveloper.dto.UsuarioDTO;

@FeignClient(name = "usuario",url = "${usuario.client.url}")
public interface UsuarioClient {

	@GetMapping("/usuario")
    List<UsuarioDTO> getUsuarios();
	
	
}
