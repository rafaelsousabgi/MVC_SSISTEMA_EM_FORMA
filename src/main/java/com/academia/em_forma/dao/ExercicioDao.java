package com.academia.em_forma.dao;

import java.util.List;

import com.academia.em_forma.domain.Exercicio;

public interface ExercicioDao {
	
	
    void save(Exercicio exercicio);
	
	void update(Exercicio exercicio);
	
	void delete(Long id);
	
	Exercicio findById(Long id);
	
	List<Exercicio> findAll();

}
