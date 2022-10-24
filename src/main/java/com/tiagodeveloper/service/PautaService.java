package com.tiagodeveloper.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.tiagodeveloper.dto.PautaDTO;

public interface PautaService {
	
	public Page<PautaDTO> getAll(Pageable pageable);
	
	public PautaDTO create(PautaDTO dto);

}
