package com.academia.em_forma.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.AvaliacaoFisicaDao;
import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.repository.AvaliacaoFisicaRepository;


@Service
@Transactional(readOnly = false)
public class AvaliacaoFisicaServiceImpl implements AvaliacaoFisicaService{

	@Autowired
	public AvaliacaoFisicaDao dao;
	
	@Autowired
	AvaliacaoFisicaRepository avaliacaoFisicaRepository;
	
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


	@Override
	@Transactional(readOnly = true)
	public List<AvaliacaoFisica> buscarAvaliacoesFisicasByAlunoId(Long id  ,String email) {
		
		return avaliacaoFisicaRepository.findAvaliacoesFisicasByAlunoId(id ,email);
				/****/
		
	}
	

}
