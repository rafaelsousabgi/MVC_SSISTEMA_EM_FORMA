package com.academia.em_forma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.academia.em_forma.dao.ExercicioDao;
import com.academia.em_forma.domain.DIA;
import com.academia.em_forma.domain.Exercicio;

@Service
@Transactional
public class ExercicioServiceImpl implements ExercicioService{

	@Autowired
	public ExercicioDao dao;
	
	@Override
	public void salvar(Exercicio exercicio) {
		dao.save(exercicio);
		
		
	}

	@Override
	public void editar(Exercicio exercicio) {
		dao.update(exercicio);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Exercicio buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Exercicio> buscarTodos() {
		
		return dao.findAll();
	}
	
	

}
