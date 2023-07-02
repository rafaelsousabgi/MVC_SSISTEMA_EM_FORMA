package com.academia.em_forma.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.academia.em_forma.domain.Aluno;

@Repository
public class AlunoDaoImpl extends AbstractDao<Aluno, Long> implements AlunoDao {



	@Override
	public List<Aluno> finByNome(String nome) {
		
		return createQuery("select a from Aluno a where a.nome like concat('%',?1,'%') ", nome);
		
	}
	

	@Override
	public List<Aluno> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida) {
		String jpql = new StringBuilder("select a from Aluno a ")
				.append("where a.dataEntrada >= ?1 and a.dataSaida <= ?2 ")
				.append("order by a.dataEntrada asc")
				.toString();
		return createQuery(jpql, entrada, saida);
	}

	@Override
	public List<Aluno> findByDataEntrada(LocalDate entrada) {
		String jpql = new StringBuilder("select a from Aluno a ")
				.append("where a.dataEntrada = ?1 ")
				.append("order by a.dataEntrada asc")
				.toString();
		return createQuery(jpql, entrada);
	}

	@Override
	public List<Aluno> findByDataSaida(LocalDate saida) {
		String jpql = new StringBuilder("select a from Aluno a ")
				.append("where a.dataSaida = ?1 ")
				.append("order by a.dataEntrada asc")
				.toString();
		return createQuery(jpql, saida);
	}
	
	
	
}
