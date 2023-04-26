package com.academia.em_forma.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/instrutores")
public class InstrutorController {
	
	@GetMapping("/cadastrar")
	public String Cadastrar() {
		return "/instrutor/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/instrutor/lista";
	}

}
