package com.academia.em_forma.service;

import java.util.List;

import com.academia.em_forma.domain.GrupoMuscular;

public interface GrupoMuscularService {
	
	void salvar(GrupoMuscular grupoMuscular);
	void editar(GrupoMuscular grupoMuscular);
	void excluir(Long id);
	GrupoMuscular buscarPorId(Long id);
	List<GrupoMuscular> buscarTodos();

}
