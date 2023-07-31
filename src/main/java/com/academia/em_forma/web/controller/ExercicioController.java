package com.academia.em_forma.web.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.AvaliacaoFisica;
import com.academia.em_forma.domain.DIA;
import com.academia.em_forma.domain.Exercicio;
import com.academia.em_forma.domain.FichaTreino;
import com.academia.em_forma.domain.PerfilTipo;
import com.academia.em_forma.service.ExercicioService;
import com.academia.em_forma.service.FichaTreinoService;

import jakarta.servlet.http.HttpServletRequest;

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
		attr.addFlashAttribute("success", "Exercicio inserido com sucesso");

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
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		if (exercicioService.exercicioTemFichaTreino(id)) {
			attr.addFlashAttribute("fail", "Exercicio não removido, Possui Ficha detreino vinculado");

		} else {
			exercicioService.excluir(id);
			attr.addFlashAttribute("success", "Exercicio Excluido com sucesso");
		}

		return "redirect:/exercicios/listar/dadosexercicios/individual";

	}

	@GetMapping("/listar/dadosexercicios/individual")
	public String getAvaliacoesFisicasByUserEmail(ModelMap model, @AuthenticationPrincipal User user,
			@RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size,
			HttpServletRequest request) {

		int currentPage = page.orElse(0);
		int pageSize = size.orElse(20);

		Page<Exercicio> exerciciosPage = null;

		if (user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.ALUNO.getDesc()))) {
			exerciciosPage = exercicioService.buscarExerciciosByAvaliacaoAlunoIdPaginado(user.getUsername(),
					currentPage, pageSize);
		} else if (user.getAuthorities().contains(new SimpleGrantedAuthority(PerfilTipo.INSTRUTOR.getDesc()))) {
			exerciciosPage = exercicioService.buscarAvaliacoesFisicasByInstrutorIdPaginado(currentPage, pageSize);
		}

		List<Exercicio> exercicios = exerciciosPage.getContent();
		List<List<Exercicio>> exerciciosPorFicha = groupExerciciosByFicha(exercicios);

		if (exerciciosPorFicha.size() == 0) {
			// Apenas uma ficha de treino, exibir todos os exercícios em uma única página
			model.addAttribute("exerciciosPage", new PageImpl<>(exercicios));
		} else {
			// Mais de uma ficha de treino, manter a paginação por ficha de treino
			model.addAttribute("exerciciosPage", exerciciosPage);
		}

		model.addAttribute("exerciciosPorFicha", exerciciosPorFicha);
		model.addAttribute("request", request);

		return "/exercicio/lista-individual";
	}

	private List<List<Exercicio>> groupExerciciosByFicha(List<Exercicio> exercicios) {
		List<List<Exercicio>> exerciciosPorFicha = new ArrayList<>();

		// Cria um mapa para agrupar os exercícios por ficha de treino
		Map<FichaTreino, List<Exercicio>> mapaExerciciosPorFicha = new HashMap<>();
		for (Exercicio exercicio : exercicios) {
			FichaTreino fichaTreino = exercicio.getFichaTreino();
			if (!mapaExerciciosPorFicha.containsKey(fichaTreino)) {
				mapaExerciciosPorFicha.put(fichaTreino, new ArrayList<>());
			}
			mapaExerciciosPorFicha.get(fichaTreino).add(exercicio);
		}

		// Ordena as fichas de treino pelo ID em ordem decrescente (da maior para a
		// menor)
		List<FichaTreino> fichasTreinoOrdenadas = new ArrayList<>(mapaExerciciosPorFicha.keySet());
		fichasTreinoOrdenadas.sort(Comparator.comparing(FichaTreino::getId).reversed());

		// Adiciona as listas de exercícios ao resultado final na ordem correta
		for (FichaTreino fichaTreino : fichasTreinoOrdenadas) {
			exerciciosPorFicha.add(mapaExerciciosPorFicha.get(fichaTreino));
		}

		return exerciciosPorFicha;
	}

	@ModelAttribute("fichasTreinos")
	public List<FichaTreino> listaDeFichaTreino() {
		return fichaTreinoService.buscarTodosDao();
	}

	@ModelAttribute("dias")
	public DIA[] getDIAS() {
		return DIA.values();
	}
	/**
	
	@GetMapping("/exercicios/buscar")
	public String buscarExerciciosPorNomeAluno(ModelMap model, @RequestParam("nomeAluno") String nomeAluno) {
	    List<Exercicio> exercicios = exercicioService.buscarExerciciosPorNomeAluno(nomeAluno);
	    List<List<Exercicio>> exerciciosPorFicha = groupExerciciosByFicha(exercicios);

	    model.addAttribute("exerciciosPorFicha", exerciciosPorFicha);
	    model.addAttribute("nomeAluno", nomeAluno);

	    return "/exercicio/lista-individual";
	}
	**/
}