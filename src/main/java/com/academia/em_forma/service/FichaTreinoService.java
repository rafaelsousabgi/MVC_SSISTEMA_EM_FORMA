package com.academia.em_forma.service;

import java.util.List;

import com.academia.em_forma.domain.FichaTreino;

public interface FichaTreinoService {

	void salvar(FichaTreino fichaTreino);
	void editar(FichaTreino fichaTreino);
	void excluir(Long id);
	FichaTreino buscarPorId(Long id);
	List<FichaTreino> buscarTodos();
	
	boolean fichaTemExercicios(Long id);
}
