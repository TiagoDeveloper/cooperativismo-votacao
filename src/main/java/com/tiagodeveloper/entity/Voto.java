package com.tiagodeveloper.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import com.tiagodeveloper.enums.VotoEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Voto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "voto_sequence_generator")
    @SequenceGenerator(name = "voto_sequence_generator", sequenceName = "voto_id_seq", initialValue = 1,allocationSize = 1)
	private Integer id;
	
	@OneToOne
	@JoinColumn(name= "pauta_id", referencedColumnName = "id")
	private Pauta pauta;

	@Enumerated(EnumType.STRING)
	private VotoEnum voto;

	private Integer usuarioId;

}
