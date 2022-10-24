package com.tiagodeveloper.service.impl;

import static com.tiagodeveloper.service.converter.VotoConverter.convert;
import static com.tiagodeveloper.service.validate.PautaValidate.validar;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tiagodeveloper.dto.VotoDTO;
import com.tiagodeveloper.enums.UserStatus;
import com.tiagodeveloper.exception.BadRequestException;
import com.tiagodeveloper.feign.client.UserClient;
import com.tiagodeveloper.repository.VotoRepository;
import com.tiagodeveloper.service.PautaService;
import com.tiagodeveloper.service.VotoService;

@Service
public class VotoServiceImpl implements VotoService {

	@Autowired
	private VotoRepository votoRepository;

	@Autowired
	private PautaService pautaService;
	
	@Autowired
	private UserClient usuarioClient;
	
	@Override
	public VotoDTO create(VotoDTO votoDTO) {
		
		var pautaId = votoDTO.getPauta().getId();
		
		checarSeUsuarioJaVotou(pautaId, votoDTO.getDocumento());
		
		var pautaDTO = pautaService.getById(pautaId);
		
		validar(pautaDTO, LocalDateTime.now());
		
		votoDTO.setPauta(pautaDTO);
		
		checarUsuario(votoDTO.getDocumento());
		
		var entity = votoRepository.save(convert(votoDTO));
		
		return convert(entity);
	}
	
	
	private void checarUsuario(String documento) {
		var userStatus =  usuarioClient.getByDocument(documento);
		
		if(userStatus.getStatus().equals(UserStatus.UNABLE_TO_VOTE))
			throw new BadRequestException(String.format("O usuário com cpf %s não pode votar.", documento));
		
	}
	
	private void checarSeUsuarioJaVotou(Integer pautaId, String documento) {
		if(votoRepository.existsByPautaIdAndDocumento(pautaId, documento))
			throw new BadRequestException(String.format("O usuário de id %d já votou.", documento));
	}

}
