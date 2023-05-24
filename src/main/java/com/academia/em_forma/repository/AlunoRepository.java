/**
 * 
 */
package com.academia.em_forma.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.academia.em_forma.domain.Aluno;

/**
 * @author IKSOLUTION
 *
 */
@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
	
	
	@Query("select a from Aluno a where a.usuario.email like :email")
	Optional<Aluno> findByUsuarioEmail(String email);

}
