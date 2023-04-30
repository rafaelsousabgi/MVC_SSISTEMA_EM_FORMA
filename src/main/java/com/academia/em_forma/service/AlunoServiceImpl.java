package com.academia.em_forma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.AlunoDao;
import com.academia.em_forma.domain.Aluno;

@Service
@Transactional
public class AlunoServiceImpl implements AlunoService{

	@Autowired
	public AlunoDao dao;
	
	@Override
	public void salvar(Aluno aluno) {
		dao.save(aluno);
		
	}

	@Override
	public void editar(Aluno aluno) {
		dao.update(aluno);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Aluno buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Aluno> buscarTodos() {
		
		return dao.findAll();
	}

	@Override
	public boolean temAvaliacoes(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

}
