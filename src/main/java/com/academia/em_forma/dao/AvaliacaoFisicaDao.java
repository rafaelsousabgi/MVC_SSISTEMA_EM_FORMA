package com.academia.em_forma.dao;

import java.util.List;


import com.academia.em_forma.domain.AvaliacaoFisica;

public interface AvaliacaoFisicaDao {
	
void save(AvaliacaoFisica avaliacaoFisica);
	
	void update(AvaliacaoFisica avaliacaoFisica);
	
	void delete(Long id);
	
	AvaliacaoFisica findById(Long id);
	
	List<AvaliacaoFisica> findAll();

}
