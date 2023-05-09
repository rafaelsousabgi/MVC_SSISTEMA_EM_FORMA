package com.academia.em_forma.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.academia.em_forma.domain.Instrutor;

@Repository
public class InstrutorDaoImpl extends AbstractDao<Instrutor,Long> implements InstrutorDao {

	

	@Override
	public List<Instrutor> finByNome(String nome) {
		
		return createQuery("select i from Instrutor i where i.nome like concat('%',?1,'%') ", nome);
	}
	
	@Override
	public List<Instrutor> findByDataEntradaDataSaida(LocalDate entrada, LocalDate saida) {
		String jpql = new StringBuilder("select i from Instrutor i ")
				.append("where i.dataEntrada >= ?1 and i.dataSaida <= ?2 ")
				.append("order by i.dataEntrada asc")
				.toString();
		return createQuery(jpql, entrada, saida);
	}

	@Override
	public List<Instrutor> findByDataEntrada(LocalDate entrada) {
		String jpql = new StringBuilder("select i from Instrutor i ")
				.append("where i.dataEntrada = ?1 ")
				.append("order by i.dataEntrada asc")
				.toString();
		return createQuery(jpql, entrada);
	}

	@Override
	public List<Instrutor> findByDataSaida(LocalDate saida) {
		String jpql = new StringBuilder("select i from Instrutor i ")
				.append("where i.dataSaida = ?1 ")
				.append("order by i.dataEntrada asc")
				.toString();
		return createQuery(jpql, saida);
	}	

}
