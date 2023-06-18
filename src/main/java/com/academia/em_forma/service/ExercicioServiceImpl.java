package com.academia.em_forma.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.ExercicioDao;
import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.domain.Exercicio;
import com.academia.em_forma.repository.ExercicioRepository;

@Service
@Transactional
public class ExercicioServiceImpl implements ExercicioService{

	@Autowired
	public ExercicioDao dao;
	
	@Autowired
	public ExercicioRepository exercicioRepository;
	
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

	@Override
	public boolean exercicioTemFichaTreino(Long id) {
		if(buscarPorId(id).getFichaTreino()==null) {
			return false;
		}
		return true;
	}
	
	
	
	@Transactional(readOnly = true)
	public Page<Exercicio> buscarExerciciosByAvaliacaoAlunoIdPaginado(String email, int page, int size) {
	    Pageable pageable = PageRequest.of(page -0, size);
	    return exercicioRepository.findByExercicioByAlunoEmail(email, pageable);
	}

	@Transactional(readOnly = true)
	public Page<Exercicio> buscarAvaliacoesFisicasByInstrutorIdPaginado( int page, int size) {
	    Pageable pageable = PageRequest.of(page -0, size);
	    return exercicioRepository.findAll(pageable);
	}
/**
 * findByExercicioByInstrutorEmail( pageable);
	}
 * 
	@Transactional(readOnly = true)
	public List<Exercicio> buscarExerciciosByAvaliacaoAlunoId(String email) {
		
		return exercicioRepository.findByExercicioByAlunoEmail(email) ;
	}

	@Transactional(readOnly = true)
	public List<Exercicio> buscarAvaliacoesFisicasByInstrutorId(String email) {
		
		return exercicioRepository.findByExercicioByInstrutorEmail(email) ;
	}

	

	
	@Override
	public boolean exerciciosTemtreino(Long id) {
		if(buscarPorId(id).getFichaTreino().i) {
			return false;
		}
		return true;
	}
	**/
	

}