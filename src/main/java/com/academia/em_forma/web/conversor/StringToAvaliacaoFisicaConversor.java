package com.academia.em_forma.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.service.AvaliacaoFisicaService;

@Component
public class StringToAvaliacaoFisicaConversor implements Converter<String, AvaliacaoFisica>{
	
	@Autowired
	public AvaliacaoFisicaService avaliacaoFisicaService;

	@Override
	public AvaliacaoFisica convert(String text) {
		
		if(text.isEmpty()) {
			return null;
		}
		
		Long id = Long.valueOf(text);
		return avaliacaoFisicaService.buscarPorId(id);
	}
		

}
