package com.tiagodeveloper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tiagodeveloper.dto.PautaDTO;
import com.tiagodeveloper.repository.PautaRepository;
import com.tiagodeveloper.service.PautaService;
import com.tiagodeveloper.service.converter.PautaConverter;

@Service
public class PautaServiceImpl implements PautaService {

	@Autowired
	private PautaRepository pautaRepository;
	
	@Override
	public Page<PautaDTO> getAll(Pageable pageable) {
		return PautaConverter.convert(pautaRepository.findAll(pageable));
	}
	
	@Override
	public PautaDTO create(PautaDTO dto) {
		var entity = pautaRepository.save(PautaConverter.convert(dto));
		return PautaConverter.convert(entity);
	}


}
