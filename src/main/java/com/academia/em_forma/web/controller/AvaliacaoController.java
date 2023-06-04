package com.academia.em_forma.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.em_forma.dao.AvaliacaoFisicaIDaoImpl;
import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.domain.Exercicio;
import com.academia.em_forma.domain.FichaTreino;
import com.academia.em_forma.domain.Instrutor;
import com.academia.em_forma.domain.PerfilTipo;
import com.academia.em_forma.domain.Usuario;
import com.academia.em_forma.repository.AvaliacaoFisicaRepository;
import com.academia.em_forma.service.AlunoService;
import com.academia.em_forma.service.AvaliacaoDataTablesService;
import com.academia.em_forma.service.AvaliacaoFisicaService;
import com.academia.em_forma.service.InstrutorService;
import com.academia.em_forma.service.UsuarioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/avaliacoes")
public class AvaliacaoController {
	
	@Autowired
	private AvaliacaoFisicaService avaliacaoFisicaService;
	
	@Autowired
	private AvaliacaoFisicaIDaoImpl avaliacaoFisicaIDaoImpl;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired
	private InstrutorService instrutorService;
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;
	
	@Autowired
	AvaliacaoFisicaRepository avaliacaoFisicaRepository;
	
	
	
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
	public String salva(@Valid AvaliacaoFisica avaliacaoFisica,BindingResult result ,RedirectAttributes attr) {
		if(result.hasErrors()) {
			return "/avaliacao/cadastro";
		}
		
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
		
		
		return "/dadosavaliacoes/{id}";
	}
	
	
	@ModelAttribute("alunos")
	public List<Aluno> listaDeAlunos(){
		return alunoService.buscarTodos();
	}
	
	@ModelAttribute("instrutores")
	public List<Instrutor> listaDeInstrutores(){
		return instrutorService.buscarTodos();
	}
/**	
	@GetMapping("/dadosavaliacoes/{id}")
    public String  getAvaliacoesFisicasByUserId(@PathVariable("id") Long id, Aluno aluno, ModelMap model,
    																			@AuthenticationPrincipal User user) {			
			List<AvaliacaoFisica> avaliacoesFisicas = avaliacaoFisicaService.buscarAvaliacoesFisicasByAlunoId(id , user.getUsername());
			
			
		       model.addAttribute("avaliacoesFisicas", avaliacoesFisicas); 
        return "avaliacao/lista" ;
    }


	
	
	
	
	
	@GetMapping("/dadosavaliacoes")
	public String getAvaliacoesFisicasByUserId(ModelMap model, @AuthenticationPrincipal User user, Pageable pageable ) {
	    Page<AvaliacaoFisica> avaliacoesFisicas;

	    if (user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.ALUNO.getDesc()))) {
	        avaliacoesFisicas = avaliacaoFisicaService.buscarAvaliacoesFisicasByAlunoId(user.getUsername());
	    } else if (user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.INSTRUTOR.getDesc()))) {
	        avaliacoesFisicas = avaliacaoFisicaService.buscarAvaliacoesFisicasByInstrutorId(user.getUsername());
	    } else {
	        avaliacoesFisicas = new PageImpl<>(Collections.emptyList()); // Página vazia caso o perfil não seja reconhecido
	    }

	    model.addAttribute("pageAvaliacoesFisicas", avaliacoesFisicas);
  
	    return "/avaliacao/lista";
	}
	
	@GetMapping("/dadosavaliacoes")
    public String  getAvaliacoesFisicasByUserId( ModelMap model,  @AuthenticationPrincipal User user) {			
		if(user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.ALUNO.getDesc()))) {	
		List<AvaliacaoFisica> avaliacoesFisicas = avaliacaoFisicaService.buscarAvaliacoesFisicasByAlunoId(user.getUsername());
		model.addAttribute("avaliacoesFisicas",avaliacoesFisicas);	
		
		}
		
		if(user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.INSTRUTOR.getDesc()))) {
			List<AvaliacaoFisica> avaliacoesFisicas = avaliacaoFisicaService.buscarAvaliacoesFisicasByInstrutorId(user.getUsername());
			model.addAttribute("avaliacoesFisicas",avaliacoesFisicas);	
			return "avaliacao/lista";
		
		}
		     
        return "avaliacao/lista" ;
    }

**/	@GetMapping("/dadosavaliacoes")
public String getAvaliacoesFisicasByUserId(ModelMap model, @AuthenticationPrincipal User user,
        @RequestParam("page") Optional<Integer> page,
        @RequestParam("size") Optional<Integer> size,
        HttpServletRequest request) {
    
    int currentPage = page.orElse(1);
    int pageSize = size.orElse(3);
    
   

    Page<AvaliacaoFisica> avaliacoesPage;

    if (user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.ALUNO.getDesc()))) {
        avaliacoesPage = avaliacaoFisicaService.buscarAvaliacoesFisicasByAlunoIdPaginado(user.getUsername(), currentPage , pageSize);
    } else if (user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.INSTRUTOR.getDesc()))) {
        avaliacoesPage = avaliacaoFisicaService.buscarAvaliacoesFisicasByInstrutorIdPaginado(user.getUsername(), currentPage , pageSize);
    } else {
        return "avaliacao/lista";
    }

    model.addAttribute("avaliacoesFisicas", avaliacoesPage.getContent());
    model.addAttribute("avaliacoesPage", avaliacoesPage);
    model.addAttribute("request", request);

    return "avaliacao/lista";
}
	
}