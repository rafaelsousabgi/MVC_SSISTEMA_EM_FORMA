package com.academia.em_forma.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/gruposmusculares")
public class GrupoMuscularController {

	@GetMapping("/cadastrar")
	public String Cadastrar() {
		return "/grupomuscular/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/grupomuscular/lista";
	}
}
