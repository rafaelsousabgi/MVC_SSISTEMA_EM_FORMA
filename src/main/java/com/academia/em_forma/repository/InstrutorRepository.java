package com.academia.em_forma.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.academia.em_forma.domain.Instrutor;

@Repository
public interface InstrutorRepository extends JpaRepository<Instrutor, Long >{
	
	@Query("select i from Instrutor i where i.usuario.id = :id")
	Optional<Instrutor> findByUsuarioId(Long id);

	@Query("select i from Instrutor i where i.usuario.email like :email")
	Optional<Instrutor> findByUsuarioEmail(String email);

}
