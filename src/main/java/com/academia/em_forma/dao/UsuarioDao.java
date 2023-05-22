package com.academia.em_forma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.academia.em_forma.domain.Usuario;

public interface UsuarioDao  {

	/**
	@Query("select u from Usuario u where u.email like :email")
	Usuario findByEmail(String email);
**/
}