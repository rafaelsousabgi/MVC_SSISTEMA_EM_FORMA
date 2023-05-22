package com.academia.em_forma.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.academia.em_forma.domain.Perfil;
import com.academia.em_forma.domain.PerfilTipo;
import com.academia.em_forma.domain.Usuario;
import com.academia.em_forma.service.UsuarioServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/u")
public class UsuarioController {
	
	@Autowired
	private UsuarioServiceImpl usuarioServiceImpl;

	//abrir cadastro de usuarios (instrutor/admin/aluno)
	@GetMapping("/novo/cadastro/usuario")
	public String cadastroPorAdminParaAdminInstrutorAluno(Usuario usuario) {
		
		return "/usuario/cadastro";
	}
	
	
	//abrir a lista e usuários
	@GetMapping("/lista")
	public String listaUsuarios() {
		
		return "/usuario/lista";
	}
	
	//abrir a lista e usuários na datatables
	@GetMapping("/datatables/server/usuarios")
	public ResponseEntity<?> listarUsuariosDatatables(HttpServletRequest request) {
		
		return ResponseEntity.ok(usuarioServiceImpl.buscarTodos(request)) ;
	}
	
	 // salvar cadastro de usuarios por administrador
    @PostMapping("/cadastro/salvar")
    public String salvarUsuarios(Usuario usuario, RedirectAttributes attr) {
    	List<Perfil> perfis = usuario.getPerfis();
    	if (perfis.size() > 2 || 
    			perfis.containsAll(Arrays.asList(new Perfil(3L),new Perfil(1L) )) ||
    			perfis.containsAll(Arrays.asList(new Perfil(2L), new Perfil(3L)))) {
    		attr.addFlashAttribute("falha", "Aluno não pode ser Admin e/ou Instrutor.");
    		attr.addFlashAttribute("usuario", usuario);
    	} else {
    		try {
    			usuarioServiceImpl.salvarUsuario(usuario); 
    			attr.addFlashAttribute("sucesso", "Operação realizada com sucesso!");
    		} catch (DataIntegrityViolationException ex) {
    			attr.addFlashAttribute("falha", "Cadastro não realizado, email já existente.");
			}
    	}
    	return "redirect:/u/novo/cadastro/usuario";
    }
    
  //pre edição de credenciais de usuários
  	@GetMapping("/editar/credenciais/usuario/{id}")
  	public ModelAndView preEditarCredenciais(@PathVariable("id") Long id) {
  		
  		return new ModelAndView("usuario/cadastro","usuario", usuarioServiceImpl.buscarPorId(id)) ;
  	}
  	
  //pre edição de credenciais de usuários
  	@GetMapping("/editar/dados/usuario/{id}/perfis/{perfis}")
  	public ModelAndView preEditarCadastroDadosPessoais(@PathVariable("id") Long usuarioId,
  													   @PathVariable("perfis") Long perfisId) {
  		Usuario us = usuarioServiceImpl.buscarPorIdEPerfis(usuarioId, perfisId);
  		
  		if (us.getPerfis().contains(new Perfil(PerfilTipo.ADMIN.getCod()))&&
  		!us.getPerfis().contains(new Perfil(PerfilTipo.INSTRUTOR.getCod()))) {
  			
  			return new ModelAndView("usuario/cadastro", "usuario",us);
  		}else if(us.getPerfis().contains(new Perfil(PerfilTipo.INSTRUTOR.getCod()))) {
  			
  			return new ModelAndView("fichatreino/lista");
  			
  		}else if (us.getPerfis().contains(new Perfil(PerfilTipo.ALUNO.getCod()))) {
  			ModelAndView model = new ModelAndView("error");
  			model.addObject("status", 403);
			model.addObject("error", "Área Restrita");
			model.addObject("message", "Os dados de Aluno são restritos a ele.");
			return model;
  		}
  		
  		return new ModelAndView("redirect:/u/lista") ;
  	}
  	
  	
  	
}
