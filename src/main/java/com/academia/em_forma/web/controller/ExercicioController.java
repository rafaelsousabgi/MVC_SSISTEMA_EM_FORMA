package com.academia.em_forma.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/exercicios")
public class ExercicioController {
	
	@GetMapping("/cadastrar")
	public String Cadastrar() {
		return "/exercicio/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/exercicio/lista";
	}
}