package com.academia.em_forma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.GrupoMuscularDao;
import com.academia.em_forma.domain.GrupoMuscular;

@Service
public class GrupoMuscularServiceImpl implements GrupoMuscularService{

	@Autowired
	public GrupoMuscularDao dao;
	
	@Override
	public void salvar(GrupoMuscular grupoMuscular) {
		dao.save(grupoMuscular);
		
	}

	@Override
	public void editar(GrupoMuscular grupoMuscular) {
		dao.update(grupoMuscular);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public GrupoMuscular buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<GrupoMuscular> buscarTodos() {
		
		return dao.findAll();
	}

}
