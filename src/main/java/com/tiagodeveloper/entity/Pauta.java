package com.tiagodeveloper.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Pauta {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pauta_sequence_generator")
    @SequenceGenerator(name = "pauta_sequence_generator", sequenceName = "pauta_id_seq", initialValue = 1,allocationSize = 1)
	private Integer id;

	private String titulo;
	private String descricao;
	private LocalDateTime dataInicio;
	private LocalDateTime dataFim;
}
