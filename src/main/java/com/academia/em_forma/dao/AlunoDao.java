package com.academia.em_forma.dao;

import java.util.List;

import com.academia.em_forma.domain.Aluno;

public interface AlunoDao {
	
    void save(Aluno aluno);
	
	void update(Aluno aluno);
	
	void delete(Long id);
	
	Aluno findById(Long id);
	
	List<Aluno> findAll();

}
