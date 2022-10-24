package com.tiagodeveloper.service.converter;

import com.tiagodeveloper.dto.VotoDTO;
import com.tiagodeveloper.entity.Voto;
public class VotoConverter {
	
	
	
	public static Voto convert(VotoDTO dto) {
		return new Voto(
			dto.getId(),
			PautaConverter.convert(dto.getPauta()),
			dto.getVoto(),
			dto.getUsuarioId()
		);
	}
	
	public static VotoDTO convert(Voto entity) {
		return new VotoDTO(
			entity.getId(),
			PautaConverter.convert(entity.getPauta()),
			entity.getVoto(),
			entity.getUsuarioId()
		);
	}
	

}
