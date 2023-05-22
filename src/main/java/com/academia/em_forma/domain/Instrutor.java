package com.academia.em_forma.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@SuppressWarnings("serial")
@Table(name="INSTRUTORES")
public class Instrutor extends Pessoa<Long> {
	
	@Column(name = "cref", unique = true, nullable = false)
	private String cref;

	@JsonIgnore
	@OneToMany(mappedBy = "instrutor")
	private List<AvaliacaoFisica> avaliacoes;
	
	@JsonIgnore
	@OneToMany(mappedBy = "instrutor")
	private List<Agendamento> agendamentos;
	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario")
	private Usuario usuario;
	
	public Instrutor() {
		super();
		}



	public Instrutor(Long id, String nome, String sexo, String estadoCivil, String rg, String cpf, String email,
			String telefone, String profissao, LocalDate dataEntrada, LocalDate dataSaida, Endereco endereco) {
		super(id, nome, sexo, estadoCivil, rg, cpf, email, telefone, profissao, dataEntrada, dataSaida, endereco);
		this.cref= cref;
		this.usuario = usuario;
		
	}





	public String getCref() {
		return cref;
	}



	public void setCref(String cref) {
		this.cref = cref;
	}


	public List<AvaliacaoFisica> getAvaliacoes() {
		return avaliacoes;
	}


	public void setAvaliacoes(List<AvaliacaoFisica> avaliacoes) {
		this.avaliacoes = avaliacoes;
	}



	public List<Agendamento> getAgendamentos() {
		return agendamentos;
	}



	public void setAgendamentos(List<Agendamento> agendamentos) {
		this.agendamentos = agendamentos;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	

}
