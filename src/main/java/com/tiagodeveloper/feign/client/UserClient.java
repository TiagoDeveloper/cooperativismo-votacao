package com.tiagodeveloper.feign.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.tiagodeveloper.dto.UsuarioDTO;

@FeignClient(name = "users",url = "${usuario.client.url}")
public interface UserClient {

	
	@GetMapping("/users/{cpf}")
	UsuarioDTO getByDocument(@PathVariable("cpf") String cpf);
	
	
}
