package com.academia.em_forma.web.controller;

import java.lang.ProcessBuilder.Redirect;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.domain.Exercicio;
import com.academia.em_forma.domain.FichaTreino;
import com.academia.em_forma.domain.Instrutor;
import com.academia.em_forma.domain.Usuario;
import com.academia.em_forma.service.AlunoService;
import com.academia.em_forma.service.AvaliacaoFisicaService;
import com.academia.em_forma.service.ExercicioService;
import com.academia.em_forma.service.FichaTreinoService;
import com.academia.em_forma.service.InstrutorService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/fichastreinos")
public class FichaTreinoController {
	
	@Autowired
	private FichaTreinoService fichaTreinoService;
	
	@Autowired
	private ExercicioService exercicioService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private AvaliacaoFisicaService avaliacaoFisicaService;

	@GetMapping("/cadastrar")
	public String Cadastrar(FichaTreino fichaTreino) {
		return "/fichatreino/cadastro";
	}
	
	@GetMapping(value = "/listar/{id}")
	public ResponseEntity<FichaTreino> findById(@AuthenticationPrincipal User user, @PathVariable Long id) {
	    
	   


	    return ResponseEntity.ok(fichaTreinoService.buscarPorId(id));	
	
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model ) {
		model.addAttribute("fichasTreinos", fichaTreinoService.buscarTodos());
		return "/fichatreino/lista";
	}
	
	@PostMapping("/salvar")
	public String salva(@Valid FichaTreino fichaTreino, BindingResult result, RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "/fichatreino/cadastro";
		}
		
		fichaTreinoService.salvar(fichaTreino);
		attr.addFlashAttribute("success","Ficha de Treino salva com sucesso.");
		
		return "redirect:/fichastreinos/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("fichaTreino", fichaTreinoService.buscarPorId(id));
		return "/fichatreino/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(FichaTreino fichaTreino, RedirectAttributes attr) {
		fichaTreinoService.editar(fichaTreino);
		attr.addFlashAttribute("success","Ficha de Treino Editada com sucesso.");
		return "redirect:/fichastreinos/cadastrar";
	}
	 /**
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.temexercicios(id)) {
			model.addAttribute("fail","Ficha de Treino não removida. Possui Exercicios(s) vinculado(s)");
		}else {
			
			service.excluir(id);
			model.addAttribute("success","Ficha de Treino removida com sucesso.");
		}
		
		return listar(model);
	} **/
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if(fichaTreinoService.fichaTemExercicios(id)) {
			model.addAttribute("fail","Ficha de Treino não removida. Possui Exercicios(s) vinculado(s)");
		}else {
			fichaTreinoService.excluir(id);
			model.addAttribute("success","Ficha de Treino excluido com sucesso.");
		}
		return listar(model);
	}
	
	
	
	/**listaDeExercicio**/
	@ModelAttribute("exercicios")
	public List<Exercicio> geExercicios(){
		return exercicioService.buscarTodos();
	}
	
	@ModelAttribute("alunos")
	public List<Aluno> listaDeAlunos(){
		return alunoService.buscarTodos();
	}
	
	@ModelAttribute("avaliacoes")
	public List<AvaliacaoFisica> listaDeAvaliacoes(){
		return avaliacaoFisicaService.buscarTodos();
	}
}
