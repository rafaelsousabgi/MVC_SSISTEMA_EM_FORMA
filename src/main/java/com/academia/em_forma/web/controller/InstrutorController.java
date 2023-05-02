package com.academia.em_forma.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.academia.em_forma.domain.Instrutor;
import com.academia.em_forma.domain.UF;
import com.academia.em_forma.service.InstrutorService;



@Controller
@RequestMapping("/instrutores")
public class InstrutorController {
	
	@Autowired
	private InstrutorService instrutorservice;
	
	@GetMapping("/cadastrar")
	public String Cadastrar(Instrutor instrutor) {
		return "/instrutor/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/instrutor/lista";
	}
	
	@PostMapping("/salvar")
	public String salva(Instrutor instrutor, RedirectAttributes attr) {
		instrutorservice.salvar(instrutor);
		attr.addFlashAttribute("success","Ficha de Treino salva com sucesso.");
		
		return "redirect:/instrutores/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("instrutor", instrutorservice.buscarPorId(id));
		return "/instrutor/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Instrutor instrutor, RedirectAttributes attr) {
		instrutorservice.editar(instrutor);
		attr.addFlashAttribute("success","Ficha de Treino Editada com sucesso.");
		return "redirect:/fichastreinos/cadastrar";
	}
	
	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}

}
