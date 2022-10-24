package com.tiagodeveloper.dto;

import java.io.Serializable;

import com.tiagodeveloper.enums.VotoEnum;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VotoDTO implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private PautaDTO pauta;
	
	private VotoEnum voto;

	private Integer usuarioId;
	
}
