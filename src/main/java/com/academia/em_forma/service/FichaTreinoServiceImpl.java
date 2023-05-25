package com.academia.em_forma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.FichaTreinoDao;
import com.academia.em_forma.domain.FichaTreino;


@Service
@Transactional
public class FichaTreinoServiceImpl implements FichaTreinoService {

	@Autowired
	public FichaTreinoDao dao;
	
	
	
	@Override
	public void salvar(FichaTreino fichaTreino) {
		dao.save(fichaTreino);
		
	}

	@Override
	public void editar(FichaTreino fichaTreino) {
		dao.update(fichaTreino);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public FichaTreino buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<FichaTreino> buscarTodos() {
		
		return dao.findAll();
	}

	

	@Override
	public boolean fichaTemExercicios(Long id) {
		if(buscarPorId(id).getExercicios().isEmpty()) {
			return false;
		}
		return true;
	}

	

	

}
