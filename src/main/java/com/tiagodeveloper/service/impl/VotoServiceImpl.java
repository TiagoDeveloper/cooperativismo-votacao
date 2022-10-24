package com.tiagodeveloper.service.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagodeveloper.dto.UsuarioDTO;
import com.tiagodeveloper.dto.VotoDTO;
import com.tiagodeveloper.exception.BadRequestException;
import com.tiagodeveloper.exception.NotFoundException;
import com.tiagodeveloper.feign.client.UsuarioClient;
import com.tiagodeveloper.repository.VotoRepository;
import com.tiagodeveloper.service.PautaService;
import com.tiagodeveloper.service.VotoService;
import static com.tiagodeveloper.service.converter.VotoConverter.convert;
import static com.tiagodeveloper.service.validate.PautaValidate.validar;

@Service
public class VotoServiceImpl implements VotoService {

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private UsuarioClient usuarioClient;
	
	@Override
	public VotoDTO create(VotoDTO votoDTO) {
		
		var pautaId = votoDTO.getPauta().getId();
		
		checarSeUsuarioJaVotou(pautaId, votoDTO.getUsuarioId());
		
		var pautaDTO = pautaService.getById(pautaId);
		
		validar(pautaDTO, LocalDateTime.now());
		
		votoDTO.setPauta(pautaDTO);
		
		checarUsuario(votoDTO.getUsuarioId());
		
		var entity = votoRepository.save(convert(votoDTO));
		
		return convert(entity);
	}
	
	
	private UsuarioDTO checarUsuario(Integer id) {
		try {
			return usuarioClient.getById(id);
		}catch(RuntimeException ex) {
			throw new NotFoundException(ex.getMessage());
		}
	}
	
	private void checarSeUsuarioJaVotou(Integer pautaId, Integer usuarioId) {
		if(votoRepository.existsByPautaIdAndUsuarioId(pautaId, usuarioId))
			throw new BadRequestException(String.format("O usuário de id %d já votou.", usuarioId));
	}

}
