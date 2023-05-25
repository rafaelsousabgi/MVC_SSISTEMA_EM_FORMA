package com.academia.em_forma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.domain.Instrutor;


public interface AvaliacaoFisicaRepository extends JpaRepository<AvaliacaoFisica, Long >{
	
	
	
	/**@Query("select a from AvaliacaoFisica a "
			+ "where "
			+ "	(a.id = :id and a.aluno.usuario.email like :email) "
			)
			
			*
			*@Query("select af from AvaliacaoFisica af where af.aluno.id = :id and af.aluno.usuario.email like :email ")
			**/
	
	
	
	@Query("SELECT a FROM AvaliacaoFisica a "
	        + "WHERE "
	        + "    ((a.aluno.id = :id AND a.aluno.usuario.email LIKE :email) "
	        + "    OR "
	        + "    (a.instrutor.id = :id AND a.instrutor.usuario.email LIKE :email))")
	List<AvaliacaoFisica> findAvaliacoesFisicasByAlunoId( Long id,String email); /**,**/

	
}
