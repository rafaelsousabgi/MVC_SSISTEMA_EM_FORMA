package com.academia.em_forma.dao;

import java.util.List;

import com.academia.em_forma.domain.Endereco;

public interface EnderecoDao {

void save(Endereco endereco);
	
	void update(Endereco endereco);
	
	void delete(Long id);
	
	Endereco findById(Long id);
	
	List<Endereco> findAll();
	
}
