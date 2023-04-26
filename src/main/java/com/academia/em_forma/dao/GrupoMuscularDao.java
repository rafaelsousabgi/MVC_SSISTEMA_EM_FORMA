package com.academia.em_forma.dao;

import java.util.List;

import com.academia.em_forma.domain.GrupoMuscular;

public interface GrupoMuscularDao {
	
void save(GrupoMuscular grupoMuscular);
	
	void update(GrupoMuscular grupoMuscular);
	
	void delete(Long id);
	
	GrupoMuscular findById(Long id);
	
	List<GrupoMuscular> findAll();

}
