package com.academia.em_forma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.AvaliacaoFisicaDao;
import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;

@Service
@Transactional(readOnly = false)
public class AvaliacaoFisicaServiceImpl implements AvaliacaoFisicaService{

	@Autowired
	public AvaliacaoFisicaDao dao;
	
	@Override
	public void salvar(AvaliacaoFisica avaliacaoFisica) {
		
		dao.save(avaliacaoFisica);
	}

	@Override
	public void editar(AvaliacaoFisica avaliacaoFisica) {
		dao.update(avaliacaoFisica);
		
	}

	@Override
	public void excluir(Long id) {
		dao.delete(id);
		
	}

	@Override
	@Transactional(readOnly = true)
	public AvaliacaoFisica buscarPorId(Long id) {
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<AvaliacaoFisica> buscarTodos() {
		
		return dao.findAll();
	}

	@Override
	
	public boolean avaliacaoTemFichaTreino(Long id) {
		if(buscarPorId(id).getFichaTreinos().isEmpty()) {
			return false;
		}
		return true;
	}


	 
	

}
