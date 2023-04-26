package com.academia.em_forma.service;

import java.util.List;

import com.academia.em_forma.domain.Aluno;

public interface AlunoService {
	
	void salvar(Aluno aluno);
	void editar(Aluno aluno);
	void excluir(Long id);
	Aluno buscarPorId(Long id);
	List<Aluno> buscarTodos();


}
