package com.academia.em_forma.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.academia.em_forma.domain.FichaTreino;

public interface FichaTreinoService {

	void salvar(FichaTreino fichaTreino);
	void editar(FichaTreino fichaTreino);
	void excluir(Long id);
	FichaTreino buscarPorId(Long id);
	//List<FichaTreino> buscarTodos();
	
	boolean fichaTemExercicios(Long id);
	//List<FichaTreino> getFichasTreinoByAlunoemail();
	//List<FichaTreino> getFichasTreinoByAlunoemail(String email);
	//FichaTreino findBySessionId(Long sessionId);
	//List<FichaTreino> buscarAlunoId(Long alunoId);
	Page<FichaTreino> buscarTodos(Pageable pageable);
	List<FichaTreino> buscarTodosDao();
}