package com.academia.em_forma.service;

import java.util.List;
import java.util.Optional;

import com.academia.em_forma.dao.AvaliacaoFisicaDao;
import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;

public interface AvaliacaoFisicaService {
	
	void salvar(AvaliacaoFisica avaliacaoFisica);
	void editar(AvaliacaoFisica avaliacaoFisica);
	void excluir(Long id);
	AvaliacaoFisica buscarPorId(Long id);
	List<AvaliacaoFisica> buscarTodos();	
	boolean avaliacaoTemFichaTreino(Long id);
	//List<AvaliacaoFisica> getAvaliacoesFisicasByAlunoId(Long alunoId);
	//AvaliacaoFisica getAvaliacoesFisicasByAlunoId(Long id, String email);
	List<AvaliacaoFisica> buscarAvaliacoesFisicasByAlunoId(Long id , String email);
	//List<AvaliacaoFisica> buscarAvaliacoesFisicasByAlunoId(Long , String email);
	
	
	
}
