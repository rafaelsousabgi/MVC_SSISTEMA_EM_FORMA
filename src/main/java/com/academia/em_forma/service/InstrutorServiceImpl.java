package com.academia.em_forma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.InstrutorDao;
import com.academia.em_forma.domain.Instrutor;

@Service
@Transactional
public class InstrutorServiceImpl implements InstrutorService {

	@Autowired
	public InstrutorDao dao;
	
	@Override
	public void salvar(Instrutor instrutor) {
		dao.save(instrutor);
		
	}

	@Override
	public void editar(Instrutor instrutor) {
		dao.update(instrutor);
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public Instrutor buscarPorId(Long id) {
		// TODO Auto-generated method stub
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Instrutor> buscarTodos() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
