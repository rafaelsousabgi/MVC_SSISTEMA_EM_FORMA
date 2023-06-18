package com.academia.em_forma.domain;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name ="TB_ALUNOS")
@SuppressWarnings("serial")
public class Aluno extends Pessoa<Long>{
	
	private String objetivo;
	
	
	
	@JsonIgnore
	@OneToMany(mappedBy ="aluno" )
	private List<AvaliacaoFisica> avaliacaoFisica;
	
	@JsonIgnore
	@OneToMany(mappedBy = "aluno")
	private List<FichaTreino> fichaTreinos;
	

	
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "id_usuario_fk")
	private Usuario usuario;

	public Aluno() {
		
	}

	
	public Aluno(Long id, String nome, String sexo, String estadoCivil, String rg, String cpf,
			String telefone, String profissao, LocalDate dataEntrada, LocalDate dataSaida , Endereco endereco) {
		super(id, nome, sexo, estadoCivil, rg, cpf, telefone, profissao, dataEntrada , dataSaida ,endereco);
		this.objetivo = objetivo;
	}


	public Aluno(Long id) {
		super.setId(id);
	}
	
	public Aluno(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}


	public List<AvaliacaoFisica> getAvaliacaoFisica() {
		return avaliacaoFisica;
	}


	public void setAvaliacaoFisica(List<AvaliacaoFisica> avaliacaoFisica) {
		this.avaliacaoFisica = avaliacaoFisica;
	}


	public List<FichaTreino> getFichaTreinos() {
		return fichaTreinos;
	}


	public void setFichaTreinos(List<FichaTreino> fichaTreinos) {
		this.fichaTreinos = fichaTreinos;
	}


	public Usuario getUsuario() {
		return usuario;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	

}
