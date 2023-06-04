package com.academia.em_forma.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.domain.Exercicio;

public interface ExercicioRepository  extends JpaRepository<Exercicio, Long>{

/**

	@Query("SELECT e FROM Exercicio e JOIN e.fichaTreinos ft JOIN ft.aluno a WHERE a.usuario.email LIKE :email")
	List<Exercicio> findByExercicioByAlunoEmail(String email);

	
	@Query("SELECT e FROM Exercicio e JOIN e.fichaTreinos ft JOIN ft.avaliacaoFisica af where af.instrutor.usuario.email LIKE :email")
	List<Exercicio> findByExercicioByInstrutorEmail(String email);
**/


    @Query("SELECT e FROM Exercicio e JOIN e.fichaTreinos ft JOIN ft.aluno a WHERE a.usuario.email LIKE :email")
    Page<Exercicio> findByExercicioByAlunoEmail(String email, Pageable pageable);

    @Query("SELECT e FROM Exercicio e JOIN e.fichaTreinos ft JOIN ft.avaliacaoFisica af WHERE af.instrutor.usuario.email LIKE :email")
    Page<Exercicio> findByExercicioByInstrutorEmail(String email, Pageable pageable);


}
