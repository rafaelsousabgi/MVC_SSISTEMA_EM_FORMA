package com.academia.em_forma.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/fichastreinos")
public class FichaTreinoController {

	@GetMapping("/cadastrar")
	public String Cadastrar() {
		return "/fichatreino/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/fichatreino/lista";
	}
}
