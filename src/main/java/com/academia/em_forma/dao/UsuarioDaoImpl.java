package com.academia.em_forma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.academia.em_forma.domain.Usuario;

@Repository
public class UsuarioDaoImpl extends AbstractDao<Usuario,Long > implements UsuarioDao {
/**
	@Override
	public Usuario findByEmail(String email) {
		// TODO Auto-generated method stub
		return null;
	}



	

	
	
	@Query("select u from Usuario u where u.email like :email")
	Usuario findByEmail(@Param("email") String email);**/

	

}
