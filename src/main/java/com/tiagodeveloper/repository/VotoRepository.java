package com.tiagodeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagodeveloper.entity.Voto;

@Repository
public interface VotoRepository extends JpaRepository<Voto, Integer> {
	
	public Boolean existsByPautaIdAndDocumento(Integer pautaId, String documento);
	
}
