package com.academia.em_forma.service;

import java.util.List;

import com.academia.em_forma.domain.Exercicio;

public interface ExercicioService {
	
	void salvar(Exercicio exercicio);
	void editar(Exercicio exercicio);
	void excluir(Long id);
	Exercicio buscarPorId(Long id);
	List<Exercicio> buscarTodos();
	boolean exerciciosTemtreino(Long id);

}
