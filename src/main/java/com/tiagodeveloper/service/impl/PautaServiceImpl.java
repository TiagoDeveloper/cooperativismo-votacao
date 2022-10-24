package com.tiagodeveloper.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.tiagodeveloper.dto.PautaDTO;
import com.tiagodeveloper.exception.NotFoundException;
import com.tiagodeveloper.repository.PautaRepository;
import com.tiagodeveloper.service.PautaService;

import static com.tiagodeveloper.service.converter.PautaConverter.convert;

@Service
public class PautaServiceImpl implements PautaService {

	@Autowired
	private PautaRepository pautaRepository;
	
	@Override
	public Page<PautaDTO> getAll(Pageable pageable) {
		return convert(pautaRepository.findAll(pageable));
	}
	
	@Override
	public PautaDTO create(PautaDTO dto) {
		var entity = pautaRepository.save(convert(dto));
		return convert(entity);
	}

	@Override
	public PautaDTO getById(Integer id) {
		var optionalEntity = pautaRepository.findById(id);
		
		
		var entity = optionalEntity.orElseThrow(() -> 
			new NotFoundException(
				String.format("Pauta com id %d não encontrada.", id)
			)
		);
		
		return convert(entity);
	}


}
