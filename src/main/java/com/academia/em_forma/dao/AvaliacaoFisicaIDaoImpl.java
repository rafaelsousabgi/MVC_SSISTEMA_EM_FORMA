package com.academia.em_forma.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;

@Repository
public  class AvaliacaoFisicaIDaoImpl extends AbstractDao<AvaliacaoFisica, Long> implements AvaliacaoFisicaDao{

	



	

	
	
/**

	 * 	@Override
	public List<Aluno> findByDataSaida(LocalDate saida) {
		String jpql = new StringBuilder("select a from Aluno a ")
				.append("where a.dataSaida = ?1 ")
				.append("order by a.dataEntrada asc")
				.toString();
		return createQuery(jpql, saida);
	}
	**/
	
}
