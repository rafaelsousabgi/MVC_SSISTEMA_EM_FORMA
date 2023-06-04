package com.academia.em_forma.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.repository.AvaliacaoFisicaRepository;

import jakarta.servlet.http.HttpServletRequest;

public class AvaliacaoDataTablesService {

	private String[] cols = {"id", "identificacao", "altura", "peito", "peso","cintura","panturrilhaDireita","panturrilhaEsquerda","coxaDireita",
			"coxaEsqueda","bracoEsquedo","bracoDireito","antebracoEsquedo","antebracoDireito","gluteo","imc","dataInicio","dataFim"};
	
	
	public Map<String, Object> execute(AvaliacaoFisicaRepository repository, HttpServletRequest request){
	
		int start = Integer.parseInt(request.getParameter("start"));
		int length = Integer.parseInt(request.getParameter("length"));
		int draw = Integer.parseInt(request.getParameter("draw"));
		int current = currentPage(start, length);
		
		String column = columnName(request);
		
		Sort.Direction direction = orderBy(request);
		
		Pageable pageable = PageRequest.of(current, length, direction, column);
		
		Page<AvaliacaoFisica> page = queryBy(repository, pageable);
		
		Map<String, Object> json = new LinkedHashMap<>();
		json.put("draw", draw);
		json.put("recordsTotal", page.getTotalElements());
		json.put("recordsFiltered", page.getTotalElements());
		json.put("data", page.getContent());
		
		return json;
	}


	private Page<AvaliacaoFisica> queryBy(AvaliacaoFisicaRepository repository, Pageable pageable) {
		
		return repository.findAll(pageable);
	}


	private Direction orderBy(HttpServletRequest request) {
	      String order = request.getParameter("order[0][dir]"); 
	      Sort.Direction sort = Sort.Direction.ASC;
	      if (order.equalsIgnoreCase("desc")) {
	    	  sort = Sort.Direction.DESC;
	      }
		return sort ;
	}


	private String columnName(HttpServletRequest request) {
		int iCol = Integer.parseInt(request.getParameter("order[0][column]"));
		return cols[iCol];
	}


	private int currentPage(int start, int length) {
		
		return start / length;
	}
	
}