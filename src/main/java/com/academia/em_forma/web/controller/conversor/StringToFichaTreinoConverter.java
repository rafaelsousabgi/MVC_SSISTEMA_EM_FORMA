package com.academia.em_forma.web.controller.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.academia.em_forma.domain.FichaTreino;
import com.academia.em_forma.service.FichaTreinoService;

@Component
public class StringToFichaTreinoConverter implements Converter<String, FichaTreino> {

	@Autowired
	private FichaTreinoService service;
	
	@Override
	public FichaTreino convert(String text) {
		
		if (text.isEmpty()) {
			return null;
		}
		
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}

}
