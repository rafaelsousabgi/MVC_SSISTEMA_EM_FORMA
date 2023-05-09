package com.academia.em_forma.dao;

import java.time.LocalDate;
import java.util.List;

import com.academia.em_forma.domain.Aluno;

public interface AlunoDao {
	
    void save(Aluno aluno);
	
	void update(Aluno aluno);
	
	void delete(Long id);
	
	Aluno findById(Long id);
	
	List<Aluno> findAll();

	List<Aluno> finByNome(String nome);

	List<Aluno> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);

	List<Aluno> findByDataEntrada(LocalDate entrada);

	List<Aluno> findByDataSaida(LocalDate saida);

}
