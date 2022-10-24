package com.tiagodeveloper.service.converter;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.tiagodeveloper.dto.PautaDTO;
import com.tiagodeveloper.entity.Pauta;

public class PautaConverter {
	
	
	public static Pauta convert(PautaDTO dto) {
		return new Pauta(
			dto.getId(),
			dto.getTitulo(),
			dto.getDescricao(),
			dto.getDataInicio(),
			dto.getDataFim()
		);
	}
	
	public static PautaDTO convert(Pauta entity) {
		return new PautaDTO(
			entity.getId(),
			entity.getTitulo(),
			entity.getDescricao(),
			entity.getDataInicio(),
			entity.getDataFim()
		);
	}
	
	
	public static Page<PautaDTO> convert(Page<Pauta> pageEntity) {
		
		var listDTO = pageEntity.getContent().stream().map(entity -> 
			new PautaDTO(
				entity.getId(),
				entity.getTitulo(),
				entity.getDescricao(),
				entity.getDataInicio(),
				entity.getDataFim()
			)
		).collect(Collectors.toList());
		return new PageImpl<>(listDTO, pageEntity.getPageable(), pageEntity.getTotalElements());
	}

}
