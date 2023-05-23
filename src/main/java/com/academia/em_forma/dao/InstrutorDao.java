package com.academia.em_forma.dao;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.academia.em_forma.domain.Instrutor;

public interface InstrutorDao {
	
void save(Instrutor instrutor);
	
	void update(Instrutor instrutor);
	
	void delete(Long id);
	
	Instrutor  findById(Long id);
	
	Optional<Instrutor> findByUsuarioId(Long id);
	
	List<Instrutor> findAll();

	List<Instrutor> finByNome(String nome);

	List<Instrutor> findByDataSaida(LocalDate saida);

	List<Instrutor> findByDataEntrada(LocalDate entrada);

	List<Instrutor> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida);

}
