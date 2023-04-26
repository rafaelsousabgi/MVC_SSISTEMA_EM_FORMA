package com.academia.em_forma.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name ="TB_ALUNOS")
@SuppressWarnings("serial")
public class Aluno extends Pessoa<Long>{
	
	private String objetivo;
	
	@OneToMany(mappedBy ="aluno" )
	private List<AvaliacaoFisica> avaliacaoFisica;
	
	@OneToMany(mappedBy = "aluno")
	private List<FichaTreino> fichaTreino = new ArrayList<>();

	public Aluno() {
		
	}

	public Aluno(Long id, String nome, String sexo, String estadoCivil, String rg, String cpf, String email,
			String telefone, String profissao, LocalDate dataEntrada, LocalDate dataSaida, String objetivo) {
		super(id, nome, sexo, estadoCivil, rg, cpf, email, telefone, profissao, dataEntrada, dataSaida);
		this.objetivo = objetivo;
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
	
	
	

}
