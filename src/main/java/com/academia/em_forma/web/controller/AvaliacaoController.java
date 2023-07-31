package com.academia.em_forma.web.controller;

import java.time.LocalDate;
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
import org.springframework.data.web.PageableDefault;
import org.springframework.format.annotation.DateTimeFormat;
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
import com.academia.em_forma.domain.TIPOFISICO;
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
	public String salva(@Valid AvaliacaoFisica avaliacaoFisica, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/avaliacao/cadastro";
		}

		avaliacaoFisicaService.salvar(avaliacaoFisica);
		attr.addFlashAttribute("success", "Ficha de Treino salva com sucesso.");

		return "redirect:/avaliacoes/cadastrar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("avaliacaoFisica", avaliacaoFisicaService.buscarPorId(id));
		return "/avaliacao/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid AvaliacaoFisica avaliacaoFisica, BindingResult result, RedirectAttributes attr) {
		if (result.hasErrors()) {
			return "/avaliacao/cadastro";
		}

		avaliacaoFisicaService.editar(avaliacaoFisica);
		attr.addFlashAttribute("success", "Avaliação Editada com sucesso.");
		return "redirect:/avaliacoes/cadastrar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (avaliacaoFisicaService.avaliacaoTemFichaTreino(id)) {
			model.addAttribute("fail", "Avaliação fisica não excluida, possui ficha de treinos cadastrada");
		} else {
			avaliacaoFisicaService.excluir(id);
			model.addAttribute("success", "Avaliação excluida com sucesso");
		}

		return "/dadosavaliacoes/{id}";
	}

	/**
	 * @GetMapping("/dadosavaliacoes") public String
	 * getAvaliacoesFisicasByUserId(ModelMap model, @AuthenticationPrincipal User
	 * user, @RequestParam("page") Optional<Integer> page, @RequestParam("size")
	 * Optional<Integer> size, HttpServletRequest request) {
	 * 
	 * int currentPage = page.orElse(0); int pageSize = size.orElse(3);
	 * 
	 * 
	 * Page<AvaliacaoFisica> avaliacoesPage;
	 * 
	 * if (user.getAuthorities().contains(new
	 * SimpleGrantedAuthority(PerfilTipo.ALUNO.getDesc()))) { avaliacoesPage =
	 * avaliacaoFisicaService.buscarAvaliacoesFisicasByAlunoIdPaginado(user.getUsername(),
	 * currentPage , pageSize); } else if (user.getAuthorities().contains(new
	 * SimpleGrantedAuthority(PerfilTipo.INSTRUTOR.getDesc()))) { avaliacoesPage =
	 * avaliacaoFisicaService.buscarAvaliacoesFisicasByInstrutorIdPaginado(user.getUsername(),
	 * currentPage , pageSize); } else { return "/avaliacao/lista"; }
	 * 
	 * model.addAttribute("avaliacoesFisicas", avaliacoesPage.getContent());
	 * model.addAttribute("avaliacoesPage", avaliacoesPage);
	 * model.addAttribute("request", request);
	 * 
	 * return "/avaliacao/listaconsultaFeitasPormim"; }
	 *
	 * 
	 * @GetMapping("/buscar/nome/feitaspormim") public String
	 * pesquisarAlunoInstrutor(@RequestParam("nome") String nome, ModelMap
	 * model, @AuthenticationPrincipal User user, @RequestParam("page")
	 * Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
	 * HttpServletRequest request) {
	 * 
	 * if (user.getAuthorities().contains(new
	 * SimpleGrantedAuthority(PerfilTipo.INSTRUTOR.getDesc()))) { int currentPage =
	 * page.orElse(0); int pageSize = size.orElse(2); Page<AvaliacaoFisica>
	 * avaliacoesPage = avaliacaoFisicaService.buscarPorNomeAlunoIns(nome,
	 * currentPage, pageSize, user.getUsername());
	 * model.addAttribute("avaliacoesPage", avaliacoesPage);
	 * model.addAttribute("request", request); return
	 * "/avaliacao/listaconsultaFeitasPormim"; } else { return
	 * "/avaliacao/listaconsultaFeitasPormim"; } }
	 *
	 **/
	/** Realiza a listagem de todas as avaliações para o instrutores **/
	@GetMapping("/todos/dadosavaliacoes")
	public String getAvaliacoesFisicasByUserInstrutor(ModelMap model, @AuthenticationPrincipal User user,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			HttpServletRequest request) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(3);

		Page<AvaliacaoFisica> avaliacoesPage;

		if (user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.INSTRUTOR.getDesc()))) {
			avaliacoesPage = avaliacaoFisicaService.buscarAvaliacoesFisicasPaginado(currentPage, pageSize);
		} else {
			return "/avaliacao/lista";
		}

		model.addAttribute("avaliacoesFisicas", avaliacoesPage.getContent());
		model.addAttribute("avaliacoesPage", avaliacoesPage);
		model.addAttribute("request", request);

		return "/avaliacao/lista";
	}

	@ModelAttribute("tipofisicos")
	public TIPOFISICO[] geTipofisicos() {
		return TIPOFISICO.values();
	}

	@ModelAttribute("alunos")
	public List<Aluno> listaDeAlunos() {
		return alunoService.buscarTodos();
	}

	@ModelAttribute("instrutores")
	public List<Instrutor> listaDeInstrutores() {
		return instrutorService.buscarTodos();
	}

	/** Consulta as avaliações por nome do aluno **/
	@GetMapping("/buscar/nome")
	public String pesquisarAluno(@RequestParam("nome") String nome, ModelMap model,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			HttpServletRequest request) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(2);

		Page<AvaliacaoFisica> avaliacoesPage = avaliacaoFisicaService.buscarPorNomeAluno(nome, currentPage, pageSize);
		model.addAttribute("avaliacoesPage", avaliacoesPage);
		model.addAttribute("request", request);
		return "/avaliacao/listaconsulta";
	}

	/**
	 * Gera lista de avaliações para instrutor e alunos individualmente para cada
	 * usuário
	 **/
	@GetMapping("/dadosavaliacoes")
	public String getAvaliacoesFisicasByUserId(ModelMap model, @AuthenticationPrincipal User user,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			HttpServletRequest request) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(2);

		Page<AvaliacaoFisica> avaliacoesPage;

		if (user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.ALUNO.getDesc()))) {
			avaliacoesPage = avaliacaoFisicaService.buscarAvaliacoesFisicasByAlunoIdPaginado(user.getUsername(),
					currentPage, pageSize);
		} else if (user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.INSTRUTOR.getDesc()))) {
			avaliacoesPage = avaliacaoFisicaService.buscarAvaliacoesFisicasByInstrutorIdPaginado(user.getUsername(),
					currentPage, pageSize);
		} else {
			return "/avaliacao/listaFeitasPormim";
		}

		model.addAttribute("avaliacoesFisicas", avaliacoesPage.getContent());
		model.addAttribute("avaliacoesPage", avaliacoesPage);
		model.addAttribute("request", request);

		return "/avaliacao/listaFeitasPormim";
	}

	/** Consultar as avaliações feitas pelo instrutor **/
	@GetMapping("/buscar/nome/feitaspormim")
	public String pesquisarAlunoInstrutor(@RequestParam("nome") String nome, ModelMap model,
			@AuthenticationPrincipal User user, @RequestParam("page") Optional<Integer> page,
			@RequestParam("size") Optional<Integer> size, HttpServletRequest request) {

		if (user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.INSTRUTOR.getDesc()))) {
			int currentPage = page.orElse(0);
			int pageSize = size.orElse(2);
			Page<AvaliacaoFisica> avaliacoesPage = avaliacaoFisicaService.buscarPorNomeAlunoEInstrutor(nome,
					currentPage, pageSize, user.getUsername());
			model.addAttribute("avaliacoesPage", avaliacoesPage);
			model.addAttribute("request", request);
			return "/avaliacao/listaconsultaFeitasPormim";
		} else {
			return "/avaliacao/listaconsultaFeitasPormim";
		}
	}

	@GetMapping("/buscar/data")
	public String getPorDatas(
			@RequestParam(name = "dataInicio", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam(name = "data_fim", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data_fim,
			ModelMap model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			HttpServletRequest request) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(3);

		Page<AvaliacaoFisica> avaliacoesPage = avaliacaoFisicaService.buscarPorDatas(dataInicio, data_fim, currentPage,
				pageSize);
		model.addAttribute("avaliacoesPage", avaliacoesPage);
		model.addAttribute("request", request);
		return "/avaliacao/listaconsulta";
	}

}