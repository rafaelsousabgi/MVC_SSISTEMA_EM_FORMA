package com.academia.em_forma.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.InstrutorDao;
import com.academia.em_forma.domain.Instrutor;
import com.academia.em_forma.repository.InstrutorRepository;

@Service
@Transactional
public class InstrutorServiceImpl implements InstrutorService {

	@Autowired
	public InstrutorDao dao;
	
	@Autowired
	private InstrutorRepository instrutorRepository;
	
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
		
		return dao.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Instrutor> buscarTodos() {
		
		return dao.findAll();
	}

	@Override
	public boolean temAvaliacoes(Long id) {
		if(buscarPorId(id).getAvaliacoes().isEmpty()) {
			return false;
		}
		return true;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Instrutor> buscarPorNome(String nome) {
		
		return dao.finByNome(nome) ;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Instrutor> buscarPorDatas(LocalDate entrada, LocalDate saida) {
		
		 if (entrada != null && saida != null) {	    	
	            return dao.findByDataEntradaDataSaida(entrada, saida);
	        } else if (entrada != null) {        	
		        return dao.findByDataEntrada(entrada);
	        } else if (saida != null) {        	
		        return dao.findByDataSaida(saida);
	        } else {
	        	return new ArrayList<Instrutor>();
	        }
	    }

	@Transactional(readOnly = true)
	public Instrutor buscarPorUsuarioId(Long id) {
		return 	instrutorRepository.findByUsuarioId(id).orElse(new Instrutor());
	}

	
	@Transactional(readOnly = true)
	public Instrutor buscarPorEmail(String email) {
		
		return instrutorRepository.findByUsuarioEmail(email).orElse(new Instrutor());
	}
	
	
	
	
}
