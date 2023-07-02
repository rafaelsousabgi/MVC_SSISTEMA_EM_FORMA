package com.academia.em_forma.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.academia.em_forma.domain.FichaTreino;

public interface FichaTreinoDao {
	
    void save(FichaTreino aluno);
	
	void update(FichaTreino aluno);
	
	void delete(Long id);
	
	FichaTreino findById(Long id);
	
	List<FichaTreino> findAll();

	//Page<FichaTreino> findAll(Pageable pageable);

}
