package com.academia.em_forma.service;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.AvaliacaoFisicaDao;
import com.academia.em_forma.datatables.DataTables;
import com.academia.em_forma.datatables.DatatablesColunas;
import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.repository.AvaliacaoFisicaRepository;
import com.academia.em_forma.util.HistoricoAlunoAvaliacao;

import jakarta.servlet.http.HttpServletRequest;


@Service
@Transactional(readOnly = false)
public class AvaliacaoFisicaServiceImpl implements AvaliacaoFisicaService{

	@Autowired
	public AvaliacaoFisicaDao dao;
	
	@Autowired
	AvaliacaoFisicaRepository avaliacaoFisicaRepository;
	
	@Autowired
	private DataTables dataTables;
	
	
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

	/**
	@Transactional(readOnly = true)
	public Map<String, Object> buscarAvaliacoesFisicasByAlunoId(String email, HttpServletRequest request) {
		dataTables.setRequest(request);
		dataTables.setColunas(DatatablesColunas.AVALIACOES);		
		Page<HistoricoAlunoAvaliacao> page = avaliacaoFisicaRepository.findHistoricoByAlunoEmail(email, dataTables.getPageable());
		return dataTables.getResponse(page);
	}

	
	@Transactional(readOnly = true)
	public  Map<String, Object> buscarAvaliacoesFisicasByInstrutorId(String email, HttpServletRequest request) {
		dataTables.setRequest(request);
		dataTables.setColunas(DatatablesColunas.AVALIACOES);		
		Page<AvaliacaoFisica> page = avaliacaoFisicaRepository.findListaAvaliacoesByInstrutorEmail(email, dataTables.getPageable());
		return dataTables.getResponse(page);
	}


	
	@Override
	@Transactional(readOnly = true)
	public List<AvaliacaoFisica> buscarAvaliacoesFisicasByAlunoId(Long id, String email) {
		
		return  avaliacaoFisicaRepository.findAvaliacoesFisicasByAlunoId(id, email);
				
		
	}

/**
	
	
	
	@Transactional(readOnly = true)
	public List<AvaliacaoFisica> buscarAvaliacoesFisicasByAlunoId(String email, Pageable pageable) {
	    return avaliacaoFisicaRepository.findListaAvaliacoesByAlunoEmail(email, pageable);
	}

	@Transactional(readOnly = true)
	public List<AvaliacaoFisica> buscarAvaliacoesFisicasByInstrutorId(String email, Pageable pageable) {
	    return avaliacaoFisicaRepository.findListaAvaliacoesByInstrutorEmail(email, pageable);
	}
	
	

	@Transactional(readOnly = true)
	public List<AvaliacaoFisica> buscarAvaliacoesFisicasByAlunoId(String email) {
		// TODO Auto-generated method stub
		return avaliacaoFisicaRepository.findListaAvaliacoesByAlunoEmail(email);
	}

	@Transactional(readOnly = true)
	public List<AvaliacaoFisica> buscarAvaliacoesFisicasByInstrutorId(String email) {
		// TODO Auto-generated method stub
		return avaliacaoFisicaRepository.findListaAvaliacoesByInstrutorEmail(email);
	}**/
	
	@Transactional(readOnly = true)
	public Page<AvaliacaoFisica> buscarAvaliacoesFisicasByAlunoIdPaginado(String email, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);
	    return avaliacaoFisicaRepository.findListaAvaliacoesByAlunoEmail(email, pageable);
	}

	@Transactional(readOnly = true)
	public Page<AvaliacaoFisica> buscarAvaliacoesFisicasByInstrutorIdPaginado(String email, int page, int size) {
	    Pageable pageable = PageRequest.of(page , size);
	    return avaliacaoFisicaRepository.findListaAvaliacoesByInstrutorEmail(email, pageable);
	}

	@Override
	@Transactional(readOnly = true)	
	public Page<AvaliacaoFisica> buscarPorNomeAluno(String nome, int page, int size) {
		Pageable pageable = PageRequest.of(page , size);
	    return avaliacaoFisicaRepository.buscarPorNomeAluno(nome, pageable);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<AvaliacaoFisica> buscarPorDatas(LocalDate dataInicio, LocalDate dataFim, int page, int size) {
	    Pageable pageable = PageRequest.of(page, size);

	    if (dataInicio != null && dataFim != null) {
	        return avaliacaoFisicaRepository.findByDataEntradaDataFim(dataInicio, dataFim, pageable);
	    } else if (dataInicio != null) {
	        return avaliacaoFisicaRepository.findByDataInicio(dataInicio, pageable);
	    } else if (dataFim != null) {
	        return avaliacaoFisicaRepository.findByDataFim(dataFim, pageable);
	    } else {
	        return Page.empty();
	    }
	}

	
	
	
}
