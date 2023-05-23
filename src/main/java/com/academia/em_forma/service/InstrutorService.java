package com.academia.em_forma.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.academia.em_forma.domain.Instrutor;

public interface InstrutorService {

	void salvar(Instrutor instrutor);
	void editar(Instrutor instrutor);
	void excluir(Long id);
	Instrutor buscarPorId(Long id);
	
	//Optional<Instrutor>  buscarPorUsuarioId(Long id);
	List<Instrutor> buscarTodos();
	boolean temAvaliacoes(Long id);
	List<Instrutor> buscarPorNome(String nome);
	List<Instrutor> buscarPorDatas(LocalDate entrada, LocalDate saida);
	Instrutor buscarPorEmail(String email);
}
