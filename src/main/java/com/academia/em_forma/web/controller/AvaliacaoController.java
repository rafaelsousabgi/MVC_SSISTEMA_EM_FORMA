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
import com.academia.em_forma.domain.Exercicio;
import com.academia.em_forma.domain.FichaTreino;
import com.academia.em_forma.domain.Instrutor;
import com.academia.em_forma.service.AlunoService;
import com.academia.em_forma.service.AvaliacaoFisicaService;
import com.academia.em_forma.service.InstrutorService;

@Controller
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoFisicaService avaliacaoFisicaService;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private InstrutorService instrutorService;
	
	
	
	@GetMapping("/cadastrar")
	public String Cadastrar(AvaliacaoFisica avaliacaoFisica) {
		return "/avaliacao/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("avaliacoesFisicas", avaliacaoFisicaService.buscarTodos());
		return "/avaliacao/lista";
	}
	
	@PostMapping("/salvar")
	public String salva(AvaliacaoFisica avaliacaoFisica, RedirectAttributes attr) {
		avaliacaoFisicaService.salvar(avaliacaoFisica);
		attr.addFlashAttribute("success","Ficha de Treino salva com sucesso.");
		
		return "redirect:/avaliacoes/cadastrar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("avaliacaoFisica", avaliacaoFisicaService.buscarPorId(id));
		return "/avaliacao/cadastro";
	}
	
	

	@PostMapping("/editar")
	public String editar(AvaliacaoFisica avaliacaoFisica , RedirectAttributes attr) {
		avaliacaoFisicaService.editar(avaliacaoFisica);
		attr.addFlashAttribute("success","Avaliação Editada com sucesso.");
		return "redirect:/avaliacoes/cadastrar";
	}
	 
	
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable ("id")  Long id, ModelMap model) {
		if(avaliacaoFisicaService.avaliacaoTemFichaTreino(id)) {
			model.addAttribute("fail", "Avaliação fisica não excluida, possui ficha de treinos cadastrada");
		}else {
			avaliacaoFisicaService.excluir(id);
			model.addAttribute("success", "Avaliação excluida com sucesso");
		}
		
		
		return listar(model);
	}
	
	
	@ModelAttribute("alunos")
	public List<Aluno> listaDeAlunos(){
		return alunoService.buscarTodos();
	}
	
	@ModelAttribute("instrutores")
	public List<Instrutor> listaDeInstrutores(){
		return instrutorService.buscarTodos();
	}
}
