package com.academia.em_forma.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.service.AlunoService;
import com.academia.em_forma.service.AvaliacaoFisicaService;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AvaliacaoFisicaService avaliacaoFisicaService;

	@GetMapping("/cadastrar")
	public String Cadastrar(Aluno aluno) {
		return "/aluno/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar() {
		return "/aluno/lista";
	}
	
	@PostMapping("/salvar")
	public String salva(Aluno aluno, RedirectAttributes attr) {
		alunoService.salvar(aluno);
		attr.addFlashAttribute("success","Ficha de Treino Editada com sucesso.");
		
		return "redirect:/fichastreinos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("aluno", alunoService.buscarPorId(id));
		return "/fichatreino/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Aluno aluno, RedirectAttributes attr) {
		alunoService.editar(aluno);
		attr.addFlashAttribute("success","Ficha de Treino Editada com sucesso.");
		return "redirect:/fichastreinos/cadastrar";
	}
	 /**
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (alunoService.temAvaliacoes(id)) {
			model.addAttribute("fail","Ficha de Treino não removida. Possui Exercicios(s) vinculado(s)");
		}else {
			
			alunoService.excluir(id);
			model.addAttribute("success","Ficha de Treino removida com sucesso.");
		}
		
		return listar(model);
	}
	**/
	@ModelAttribute("Avaliacoes")
	public List<AvaliacaoFisica> listaDeAvaliacoes(){
		return avaliacaoFisicaService.buscarTodos();
	}
}
