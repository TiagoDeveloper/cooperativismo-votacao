package com.tiagodeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagodeveloper.entity.Pauta;

@Repository
public interface PautaRepository extends JpaRepository<Pauta, Integer> {

}
