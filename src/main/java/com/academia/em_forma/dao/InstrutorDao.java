package com.academia.em_forma.dao;

import java.util.List;

import com.academia.em_forma.domain.Instrutor;

public interface InstrutorDao {
	
void save(Instrutor instrutor);
	
	void update(Instrutor instrutor);
	
	void delete(Long id);
	
	Instrutor findById(Long id);
	
	List<Instrutor> findAll();

}
