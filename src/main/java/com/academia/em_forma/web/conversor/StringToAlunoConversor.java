package com.academia.em_forma.web.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.service.AlunoService;

@Component
public class StringToAlunoConversor implements Converter<String, Aluno> {

	@Autowired
	private AlunoService alunoService;
	
	@Override
	public Aluno convert(String text) {
		if(text.isEmpty()) {
			return null;
		}		
		Long id = Long.valueOf(text);
		return alunoService.buscarPorId(id);
	}
	
}
