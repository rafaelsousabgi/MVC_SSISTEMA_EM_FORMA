package com.academia.em_forma.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.academia.em_forma.domain.Exercicio;
import com.academia.em_forma.service.ExercicioService;

@Component
public class StringToExercicioConversor  implements Converter<String, Exercicio>{

	@Autowired
	private ExercicioService service;
	
	@Override
	public Exercicio convert(String text) {
		if(text.isEmpty()) {
			return null;
		}
		
		Long id = Long.valueOf(text);
		return service.buscarPorId(id);
	}

}
