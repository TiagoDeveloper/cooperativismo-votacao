package com.tiagodeveloper.service.validate;

import java.time.LocalDateTime;

import com.tiagodeveloper.dto.PautaDTO;
import com.tiagodeveloper.exception.BadRequestException;

public class PautaValidate {
	
	public static void validar(PautaDTO pautaDTO, LocalDateTime currentDate) {
		if(!(currentDate.isAfter(pautaDTO.getDataInicio().minusSeconds(1)) && 
				currentDate.isBefore(pautaDTO.getDataFim().plusMinutes(1)))) {
			throw new BadRequestException("Fora do período de votação!");
		}
	}
	
	public static boolean isVotacaoEmAndamento(PautaDTO pautaDTO, LocalDateTime currentDate) {
		return currentDate.isAfter(pautaDTO.getDataInicio().minusSeconds(1)) && 
				currentDate.isBefore(pautaDTO.getDataFim().plusMinutes(1));
	}
	
}
