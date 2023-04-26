package com.academia.em_forma.dao;

import java.util.List;

import com.academia.em_forma.domain.FichaTreino;

public interface FichaTreinoDao {
	
    void save(FichaTreino aluno);
	
	void update(FichaTreino aluno);
	
	void delete(Long id);
	
	FichaTreino findById(Long id);
	
	List<FichaTreino> findAll();

}
