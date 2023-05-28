package com.academia.em_forma.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.academia.em_forma.dao.UsuarioDao;
import com.academia.em_forma.datatables.DataTables;
import com.academia.em_forma.datatables.DatatablesColunas;
import com.academia.em_forma.domain.Perfil;
import com.academia.em_forma.domain.Usuario;
import com.academia.em_forma.repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
@Transactional
public class UsuarioServiceImpl implements UserDetailsService{

	@Autowired
	private UsuarioRepository repository;
	
	@Autowired
	private DataTables dataTables;

	
	@Transactional(readOnly = true)
	public Usuario buscarPorEmail(String email) {
		
		return repository.findByEmail(email);
	}

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = buscarPorEmailEAtivo(username).orElseThrow(()->new UsernameNotFoundException("Usuario"+ username +"não encontrado"));
		return new User(
				usuario.getEmail(),
				usuario.getSenha(),
				AuthorityUtils.createAuthorityList(getAtuthorities(usuario.getPerfis()))
				);
			}
			
			private String[] getAtuthorities(List<Perfil> perfis) {
				String[] authorities = new String[perfis.size()];
				for (int i = 0; i < perfis.size(); i++) {
					authorities[i] = perfis.get(i).getDesc();
				}
				return authorities;
			}

	@Transactional(readOnly = true)
	public Map<String, Object> buscarTodos(HttpServletRequest request) {
				dataTables.setRequest(request);
				dataTables.setColunas(DatatablesColunas.USUARIOS);
				
				Page<Usuario> page = dataTables.getSearch().isEmpty()
						? repository.findAll(dataTables.getPageable())
						: repository.findByEmailOrPerfil(dataTables.getSearch(),dataTables.getPageable());
System.out.println(dataTables);
				return dataTables.getResponse(page);
			}

	@Transactional(readOnly = false)
	public void salvarUsuario(Usuario usuario) {
		String crypt = new BCryptPasswordEncoder().encode(usuario.getSenha());
		usuario.setSenha(crypt);

		repository.save(usuario); 	 	
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorId(Long id) {
		
		return repository.findById(id).get();
	}

	@Transactional(readOnly = true)
	public Usuario buscarPorIdEPerfis(Long usuarioId, Long perfisId) {
		
		return repository.findByIdAndPerfis(usuarioId, perfisId)
				.orElseThrow(() -> new UsernameNotFoundException("usuário inexistente"));
	}

	public static boolean isSenhaCorreta(String senhaDigitada, String senhaArmazenada) {
		
		return new BCryptPasswordEncoder().matches(senhaDigitada, senhaArmazenada);
	}

	@Transactional(readOnly = false)
	public void alterarSenha(Usuario usuario, String senha) {
		usuario.setSenha(new BCryptPasswordEncoder().encode(senha));
		repository.save(usuario);
		
		
	}
	
	@Transactional(readOnly = true)
	public Optional<Usuario> buscarPorEmailEAtivo(String email){
		
		return repository.findByEmailAndAtivo(email);
	}

}
