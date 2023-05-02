package com.academia.em_forma.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.academia.em_forma.domain.Instrutor;
import com.academia.em_forma.service.InstrutorService;

@Component
public class StringToInstrutorConversor implements Converter<String, Instrutor> {

	@Autowired
	private InstrutorService instrutorService;
	
	@Override
	public Instrutor convert(String text) {
		if(text.isEmpty()) {
			return null;
		}		
		Long id = Long.valueOf(text);
		return instrutorService.buscarPorId(id);
	}
	

}
