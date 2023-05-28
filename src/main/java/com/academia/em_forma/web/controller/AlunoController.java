package com.academia.em_forma.web.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.domain.Exercicio;
import com.academia.em_forma.domain.FichaTreino;
import com.academia.em_forma.domain.TIPOFISICO;
import com.academia.em_forma.domain.UF;
import com.academia.em_forma.domain.Usuario;
import com.academia.em_forma.service.AlunoService;
import com.academia.em_forma.service.AvaliacaoFisicaService;
import com.academia.em_forma.service.ExercicioService;
import com.academia.em_forma.service.FichaTreinoService;
import com.academia.em_forma.service.UsuarioServiceImpl;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/alunos")
public class AlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	private AvaliacaoFisicaService avaliacaoFisicaService;
	
	@Autowired 
	FichaTreinoService fichaTreinoService;
	
	@Autowired
	ExercicioService exercicioService;

	@GetMapping("/cadastrar")
	public String Cadastrar(Aluno aluno, ModelMap model, @AuthenticationPrincipal User user) {
		
		aluno = alunoService.buscarPorUsuarioEmail(user.getUsername());
		
		if (aluno.hasNotId()) {
			aluno.setUsuario(new Usuario(user.getUsername()));
		}		
		model.addAttribute("aluno",aluno);
				
		return "/aluno/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
			model.addAttribute("alunos", alunoService.buscarTodos());
		return "/aluno/lista";
	}
	
	/**salvar o form de dados pessoais do aluno com verificação de senha**/
	 
	 @PostMapping("/salvar")
	public String salva(Aluno aluno, ModelMap model, @AuthenticationPrincipal User user) {
		
		 Usuario u = usuarioServiceImpl.buscarPorEmail(user.getUsername());
		 if(UsuarioServiceImpl.isSenhaCorreta(aluno.getUsuario().getSenha(), u.getSenha())) {
			 aluno.setUsuario(u);
			 alunoService.salvar(aluno);
			 model.addAttribute("sucesso","Seus dados foram inseridos com sucesso.");
		 }else {
			 model.addAttribute("falha", "Sua senha não confere, tente novamente.");
		 } 
						
		
		return "aluno/cadastro";
	}
	/**
	//**@PostMapping("/salvar")
	public String salva(Aluno aluno, RedirectAttributes attr) {
		alunoService.salvar(aluno);
		
		attr.addFlashAttribute("success","Aluno salvo com sucesso.");
		
		return "redirect:/alunos/cadastrar";
	} **/
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("aluno", alunoService.buscarPorId(id));
		
		return "/aluno/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(Aluno aluno, RedirectAttributes attr) {
		alunoService.editar(aluno);
		attr.addFlashAttribute("success","Aluno Editado com sucesso.");
		return "redirect:/alunos/cadastrar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (alunoService.temAvaliacoes(id)) {
			model.addAttribute("fail","Aluno não removido. Possui Ficha de Treino vinculado(s)");
		}else {
			
			alunoService.excluir(id);
			model.addAttribute("success","Aluno removido com sucesso.");
		}
		
		return listar(model);
	}
	
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {		
		model.addAttribute("alunos", alunoService.buscarPorNome(nome));
		return "/aluno/lista";
	}
	
	@GetMapping("/buscar/data")
    public String getPorDatas(@RequestParam(name="entrada", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
                              @RequestParam(name="saida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
                              ModelMap model) {

        model.addAttribute("alunos", alunoService.buscarPorDatas(entrada, saida));
        return "/aluno/lista";
    }
	
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

