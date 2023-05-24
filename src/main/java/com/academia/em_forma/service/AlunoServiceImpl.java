package com.academia.em_forma.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.AlunoDao;
import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.Endereco;
import com.academia.em_forma.repository.AlunoRepository;

@Service
@Transactional
public class AlunoServiceImpl implements AlunoService{

	@Autowired
	public AlunoDao dao;
	
	@Autowired
	private AlunoRepository repository;
	
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
		if(buscarPorId(id).getAvaliacaoFisica().isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Aluno> buscarPorNome(String nome) {
		
		return dao.finByNome(nome) ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Aluno> buscarPorDatas(LocalDate entrada, LocalDate saida) {
		
		if (entrada != null && saida != null) {	    	
            return dao.findByDataEntradaDataSaida(entrada, saida);
        } else if (entrada != null) {        	
	        return dao.findByDataEntrada(entrada);
        } else if (saida != null) {        	
	        return dao.findByDataSaida(saida);
        } else {
        	return new ArrayList<Aluno>();
        }
    }
	@Transactional(readOnly = true)
	public Aluno buscarPorUsuarioEmail(String email) {
		return repository.findByUsuarioEmail(email).orElse(new Aluno());
	}
	
	}


