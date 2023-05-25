package com.academia.em_forma.service;

import java.time.LocalDate;
import java.util.List;

import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.domain.Endereco;
import com.academia.em_forma.domain.Instrutor;

public interface AlunoService {
	
	void salvar(Aluno aluno);
	void editar(Aluno aluno);
	void excluir(Long id);
	Aluno buscarPorId(Long id);
	List<Aluno> buscarTodos();
	boolean temAvaliacoes(Long id);
   List<Aluno> buscarPorNome(String nome);
   List<Aluno> buscarPorDatas(LocalDate entrada, LocalDate saida);
   Aluno buscarPorUsuarioEmail(String email);



}
