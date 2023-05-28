package com.academia.em_forma.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.academia.em_forma.domain.PerfilTipo;
import com.academia.em_forma.service.UsuarioServiceImpl;

@Configuration
@EnableMethodSecurity
@EnableWebSecurity
public class SecurityConfig  {
	
	//constantes para não trabalhar com strings
	private static final String ADMIN = PerfilTipo.ADMIN.getDesc();
	private static final String INSTRUTOR = PerfilTipo.INSTRUTOR.getDesc();
	private static final String ALUNO = PerfilTipo.ALUNO.getDesc();

	@Bean
	public  SecurityFilterChain configure(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests((authorize)	 -> authorize
			// acessos públicos liberados
			.requestMatchers("/webjars/**", "/css/**", "/image/**", "/js/**").permitAll()
			/**preciso criar um index para direcionar para uma home limpa sem nada**/
			.requestMatchers("/","home").permitAll()
			
			
			// acessos privados admin
			.requestMatchers("/u/editar/senha","/u/confirmar/senha").hasAnyAuthority(INSTRUTOR,ALUNO)
			.requestMatchers("/u/**").hasAuthority(ADMIN)
			
			//ACESSOS PRIVADOS iNSTRUTOR
			.requestMatchers("/instrutores/cadastrar","/instrutores/salvar", "/instrutores/editar","/instrutores/editar/{id}","/instrutores/excluir/{id}","/instrutores/dados" ).hasAuthority(INSTRUTOR)
			.requestMatchers("/instrutores/**").hasAnyAuthority(ADMIN,INSTRUTOR )
			
			.requestMatchers("/fichastreinos/listar","/exercicios/listar" ,"/exercicios/listar/dadosexercicios","/avaliacoes/listar","/avaliacoes/dadosavaliacoes").hasAnyAuthority(ALUNO,INSTRUTOR)
			.requestMatchers("/fichastreinos/**","/exercicios/**","/avaliacoes/**").hasAuthority(INSTRUTOR)
			
			//ACESSOS PRIVADOS Aluno
			//.requestMatchers("/alunos/listar").hasAnyAuthority(ALUNO,INSTRUTOR)
			//.requestMatchers("avaliacoes/dadosavaliacoes/{Id}").hasAuthority(ALUNO)
			.requestMatchers("/alunos/**").hasAuthority(ALUNO)
			
			
			.anyRequest().authenticated()
			
			)
		.formLogin()
			.loginPage("/login")
			.defaultSuccessUrl("/", true)
			.failureUrl("/login-error")
			.permitAll()
	    .and()
			.logout()
			.logoutSuccessUrl("/")
			.and()
			.exceptionHandling()
			.accessDeniedPage("/acesso-negado");
		
		
		
		
		return http.build();
		
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http, 
													   PasswordEncoder passwordEncoder, 
													   UsuarioServiceImpl userDetailsService) throws Exception {
		
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetailsService)
				.passwordEncoder(passwordEncoder)
				.and()
				.build();
	}

}
