package com.academia.em_forma.domain;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="TB_ALUNOS")
@SuppressWarnings("serial")
public class Aluno extends Pessoa<Long>{
	
	private String objetivo;
	
	@Column(nullable = false, length = 9)
	@Enumerated(EnumType.STRING)
	private TIPOFISICO tipofisico;
	
	@OneToMany(mappedBy ="aluno" )
	private List<AvaliacaoFisica> avaliacaoFisica;
	
	@OneToMany(mappedBy = "aluno")
	private List<FichaTreino> fichaTreinos;

	public Aluno() {
		
	}

	
	public Aluno(Long id, String nome, String sexo, String estadoCivil, String rg, String cpf, String email,
			String telefone, String profissao, LocalDate dataEntrada, LocalDate dataSaida , Endereco endereco) {
		super(id, nome, sexo, estadoCivil, rg, cpf, email, telefone, profissao, dataEntrada , dataSaida ,endereco);
		this.objetivo = objetivo;
	}



	


	public TIPOFISICO getTipofisico() {
		return tipofisico;
	}

	public void setTipofisico(TIPOFISICO tipofisico) {
		this.tipofisico = tipofisico;
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
	
	
	
	

}
