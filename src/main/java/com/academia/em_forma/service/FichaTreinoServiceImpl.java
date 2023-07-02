package com.academia.em_forma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.FichaTreinoDao;
import com.academia.em_forma.domain.FichaTreino;
import com.academia.em_forma.repository.FichaTreinoRepository;


@Service
@Transactional
public class FichaTreinoServiceImpl implements FichaTreinoService {

	@Autowired
	public FichaTreinoDao dao;
	
	@Autowired
	public FichaTreinoRepository fichaTreinoRepository;
	
	
	
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
	public List<FichaTreino> buscarTodosDao() {
		
		return dao.findAll();
	}
	
	

	@Override
	@Transactional(readOnly = true)
	public Page<FichaTreino> buscarTodos(Pageable pageable) {
	    return fichaTreinoRepository.findAll(pageable);
	}

	@Override
	public boolean fichaTemExercicios(Long id) {
	    FichaTreino ficha = buscarPorId(id);
	    return !ficha.getExercicios().isEmpty();
	}
	

	

	

	

}