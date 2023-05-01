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
import com.academia.em_forma.domain.DIA;
import com.academia.em_forma.domain.FichaTreino;
import com.academia.em_forma.domain.TIPOFISICO;
import com.academia.em_forma.domain.UF;
import com.academia.em_forma.service.AlunoService;
import com.academia.em_forma.service.AvaliacaoFisicaService;
import com.academia.em_forma.service.FichaTreinoService;

@Controller
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AvaliacaoFisicaService avaliacaoFisicaService;
	
	@Autowired FichaTreinoService fichaTreinoService;

	@GetMapping("/cadastrar")
	public String Cadastrar(Aluno aluno) {
		return "/aluno/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
			model.addAttribute("alunos", alunoService.buscarTodos());
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
	
	@ModelAttribute("fichastreino")
	public List<FichaTreino> listaFichasTreinos(){
		return fichaTreinoService.buscarTodos();
	};
	
	@ModelAttribute("tipofisicos")
	public TIPOFISICO[] geTipofisicos(){
		return TIPOFISICO.values();
	}
	
	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}
	
	}

