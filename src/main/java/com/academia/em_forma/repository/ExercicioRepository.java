package com.academia.em_forma.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.domain.Exercicio;

public interface ExercicioRepository  extends JpaRepository<Exercicio, Long>{

	/**@Query("SELECT e FROM Exercicio e WHERE e.fichaTreinos.aluno.usuario.email like :email")**/
	@Query("SELECT e FROM Exercicio e JOIN e.fichaTreinos ft JOIN ft.aluno a WHERE a.usuario.email LIKE :email")
	List<Exercicio> findByExercicioByAlunoEmail(String email);

	/**@Query("SELECT e FROM Exercicio e WHERE e.fichaTreinos.avaliacaoFisica.instrutor.usuario.email like :email")**/
	@Query("SELECT e FROM Exercicio e JOIN e.fichaTreinos ft JOIN ft.avaliacaoFisica af where af.instrutor.usuario.email LIKE :email")
	List<Exercicio> findByExercicioByInstrutorEmail(String email);

}
