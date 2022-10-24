package com.tiagodeveloper.enums;

import lombok.Getter;

@Getter
public enum VotoEnum {
	
	SIM(1, "Sim"),
	NAO(2, "Não");
	
	Integer id;
	String descricao;
	
	VotoEnum(Integer id, String descricao){
		this.id = id;
		this.descricao = descricao;
	}
}
