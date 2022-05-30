package com.br.adv.processo.services.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.adv.processo.services.entity.Processo;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long>{

}
