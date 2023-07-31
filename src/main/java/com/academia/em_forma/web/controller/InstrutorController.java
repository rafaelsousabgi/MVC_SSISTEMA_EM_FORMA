package com.academia.em_forma.web.controller;

import java.time.LocalDate;

import org.springframework.security.core.userdetails.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.em_forma.domain.Instrutor;
import com.academia.em_forma.domain.UF;
import com.academia.em_forma.domain.Usuario;
import com.academia.em_forma.service.InstrutorService;
import com.academia.em_forma.service.InstrutorServiceImpl;
import com.academia.em_forma.service.UsuarioServiceImpl;

@Controller
@RequestMapping("instrutores")
public class InstrutorController {

	@Autowired
	private InstrutorService instrutorservice;

	// @Autowired
	// InstrutorServiceImpl instrutorServiceImpl;

	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	// abrir pagina de dados pessoais de medicos pelo MEDICO
	@GetMapping({ "/dados" })
	public String abrirPorInstrutor(Instrutor instrutor, ModelMap model, @AuthenticationPrincipal User user) {
		if (instrutor.hasNotId()) {
			instrutor = instrutorservice.buscarPorEmail(user.getUsername());
			model.addAttribute("instrutor", instrutor);
		}
		return "instrutor/cadastro";
	}

	/**@GetMapping("/cadastrar")
	public String Cadastrar(Instrutor instrutor) {
		return "instrutor/cadastro";
	}**/

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("instrutores", instrutorservice.buscarTodos());
		return "/instrutor/lista";
	}

	@GetMapping("/listarContatos")
	public String listarContatos(ModelMap model) {
		model.addAttribute("instrutores", instrutorservice.buscarTodos());
		return "/instrutor/listaContatos";
	}

	@GetMapping("/buscar/nomeContato")
	public String getPorNomeContato(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("instrutores", instrutorservice.buscarPorNome(nome));
		return "/instrutor/listaContatosPesquisa";
	}

	@PostMapping("/salvar")
	public String salva(Instrutor instrutor, RedirectAttributes attr, @AuthenticationPrincipal User user) {

		if (instrutor.hasNotId() && instrutor.getUsuario().hasNotId()) {
			Usuario usuario = usuarioServiceImpl.buscarPorEmail(user.getUsername());
			instrutor.setUsuario(usuario);
		}

		instrutorservice.salvar(instrutor);
		attr.addFlashAttribute("sucesso", "Instrutor salvo com sucesso.");
		attr.addFlashAttribute("instrutor", instrutor);

		return "redirect:/instrutores/dados";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("instrutor", instrutorservice.buscarPorId(id));
		return "/instrutor/cadastro";
	}

	@PostMapping("/editar")
	public String editar(Instrutor instrutor, RedirectAttributes attr) {
		instrutorservice.editar(instrutor);
		attr.addFlashAttribute("success", "Instrutor Editado com sucesso.");

		return "redirect:/instrutores/dados";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (instrutorservice.temAvaliacoes(id)) {
			model.addAttribute("fail", "Instrutor não removido. Possui Avaliação(s) Fisica vinculado(s)");
		} else {

			instrutorservice.excluir(id);
			model.addAttribute("success", "Instrutor removida com sucesso.");
		}

		return listar(model);
	}

	// @PreAuthorize("hasAnyAuthority('INSTRUTOR','ADMIN')")
	@GetMapping("/buscar/nome")
	public String getPorNome(@RequestParam("nome") String nome, ModelMap model) {
		model.addAttribute("instrutores", instrutorservice.buscarPorNome(nome));
		return "/instrutor/lista";
	}

	// @PreAuthorize("hasAnyAuthority('INSTRUTOR','ADMIN')")
	@GetMapping("/buscar/data")
	public String getPorDatas(
			@RequestParam(name = "entrada", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate entrada,
			@RequestParam(name = "saida", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate saida,
			ModelMap model) {

		model.addAttribute("instrutores", instrutorservice.buscarPorDatas(entrada, saida));
		return "/instrutor/lista";
	}

	@ModelAttribute("ufs")
	public UF[] getUfs() {
		return UF.values();
	}

}
