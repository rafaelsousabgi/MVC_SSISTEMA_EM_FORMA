package com.academia.em_forma.service;

import java.util.List;

import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.domain.Exercicio;
import org.springframework.data.domain.Page;

public interface ExercicioService {
	
	void salvar(Exercicio exercicio);
	void editar(Exercicio exercicio);
	void excluir(Long id);
	Exercicio buscarPorId(Long id);
	List<Exercicio> buscarTodos();
	boolean exercicioTemFichaTreino(Long id);	
	Page<Exercicio> buscarExerciciosByAvaliacaoAlunoIdPaginado(String email, int currentPage, int pageSize);
	Page<Exercicio> buscarAvaliacoesFisicasByInstrutorIdPaginado(/**String email,**/int currentPage, int pageSize);
	//Page<AvaliacaoFisica> buscarPorNomeAluno(String nome, int currentPage, int pageSize);
	
	/**boolean exerciciosTemtreino(Long id);**/

}