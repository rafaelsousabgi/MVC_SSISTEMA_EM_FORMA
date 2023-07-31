/**
 * 
 */
package com.academia.em_forma.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

	@Query("select a from Aluno a where a.usuario.email like :email")
	Optional<Aluno> findByUsuarioEmail(String email);

	
	@Query("SELECT a FROM AvaliacaoFisica a " + "WHERE "
			+ "    (a.aluno.id = :id and a.aluno.usuario.email like :email) ")
	List<AvaliacaoFisica> findAvaliacoesFisicasByAlunoId(Long id, String email);

	// @Query("SELECT af FROM Aluno a JOIN a.avaliacoesFisicas af WHERE a.id =
	// :alunoId")
}
