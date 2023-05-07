package com.academia.em_forma.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.em_forma.domain.DIA;
import com.academia.em_forma.domain.Exercicio;
import com.academia.em_forma.domain.FichaTreino;
import com.academia.em_forma.service.ExercicioService;
import com.academia.em_forma.service.FichaTreinoService;

@Controller
@RequestMapping("/exercicios")
public class ExercicioController {
	
	@Autowired
	private ExercicioService exercicioService;
	@Autowired
	private FichaTreinoService fichaTreinoService;
	
	@GetMapping("/cadastrar")
	public String Cadastrar(Exercicio exercicio) {
		return "/exercicio/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("exercicios", exercicioService.buscarTodos());
		return "/exercicio/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(Exercicio exercicio, RedirectAttributes attr) {
		exercicioService.salvar(exercicio);
		attr.addFlashAttribute("success","Exercicio inserido com sucesso");
		
		return "redirect:/exercicios/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("exercicio", exercicioService.buscarPorId(id));
		return "/exercicio/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Exercicio exercicio, RedirectAttributes attr) {
		exercicioService.editar(exercicio);
		attr.addFlashAttribute("success", "Exercicio atualizado com sucesso");
		return "redirect:/exercicios/cadastrar";
	}
	

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id,ModelMap model ) {
		if(exercicioService.exercicioTemFichaTreino(id)) {			
			
			exercicioService.excluir(id);
			}
	
		
		return listar(model);
		
	}
	
	
	
	/**getFichaTreinos **/
	
	@ModelAttribute("fichasTreinos")
	public List<FichaTreino> listaDeFichaTreino(){
		return fichaTreinoService.buscarTodos();
	}
	
	@ModelAttribute("dias")
	public DIA[] getDIAS() {
		return DIA.values();
	}
}
