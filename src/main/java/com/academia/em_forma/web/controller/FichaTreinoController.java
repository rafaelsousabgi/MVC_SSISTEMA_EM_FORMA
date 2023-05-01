package com.academia.em_forma.web.controller;

import java.lang.ProcessBuilder.Redirect;
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

import com.academia.em_forma.domain.Exercicio;
import com.academia.em_forma.domain.FichaTreino;
import com.academia.em_forma.service.ExercicioService;
import com.academia.em_forma.service.FichaTreinoService;

@Controller
@RequestMapping("/fichastreinos")
public class FichaTreinoController {
	
	@Autowired
	private FichaTreinoService service;
	
	@Autowired
	private ExercicioService exercicioService;

	@GetMapping("/cadastrar")
	public String Cadastrar(FichaTreino fichaTreino) {
		return "/fichatreino/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model ) {
		model.addAttribute("fichasTreinos", service.buscarTodos());
		return "/fichatreino/lista";
	}
	
	@PostMapping("/salvar")
	public String salva(FichaTreino fichaTreino, RedirectAttributes attr) {
		service.salvar(fichaTreino);
		attr.addFlashAttribute("success","Ficha de Treino Editada com sucesso.");
		
		return "redirect:/fichastreinos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("fichaTreino", service.buscarPorId(id));
		return "/fichatreino/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(FichaTreino fichaTreino, RedirectAttributes attr) {
		service.editar(fichaTreino);
		attr.addFlashAttribute("success","Ficha de Treino Editada com sucesso.");
		return "redirect:/fichastreinos/cadastrar";
	}
	 
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.temexercicios(id)) {
			model.addAttribute("fail","Ficha de Treino não removida. Possui Exercicios(s) vinculado(s)");
		}else {
			
			service.excluir(id);
			model.addAttribute("success","Ficha de Treino removida com sucesso.");
		}
		
		return listar(model);
	}
	/**listaDeExercicio**/
	@ModelAttribute("Exercicios")
	public List<Exercicio> getExercicios(){
		return exercicioService.buscarTodos();
	}
}
