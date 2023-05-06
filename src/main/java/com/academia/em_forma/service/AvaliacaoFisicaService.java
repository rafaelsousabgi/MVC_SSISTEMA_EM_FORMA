package com.academia.em_forma.service;

import java.util.List;

import com.academia.em_forma.domain.AvaliacaoFisica;

public interface AvaliacaoFisicaService {
	
	void salvar(AvaliacaoFisica avaliacaoFisica);
	void editar(AvaliacaoFisica avaliacaoFisica);
	void excluir(Long id);
	AvaliacaoFisica buscarPorId(Long id);
	List<AvaliacaoFisica> buscarTodos();
	
	
	boolean avaliacaoTemFichaTreino(Long id);

}
