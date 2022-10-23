package com.tiagodeveloper.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

	
	private Integer id;
	private String nome;
	private String documento;

}
