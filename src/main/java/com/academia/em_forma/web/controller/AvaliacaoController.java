package com.academia.em_forma.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
	
	@GetMapping("/cadastrar")
	public String Cadastrar() {
		return "/avaliacao/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/avaliacao/lista";
	}
}
