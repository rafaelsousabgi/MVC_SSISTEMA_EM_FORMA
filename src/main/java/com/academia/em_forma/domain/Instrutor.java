package com.academia.em_forma.domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.persistence.Table;

@Entity
@SuppressWarnings("serial")
@Table(name="INSTRUTORES")
public class Instrutor extends Pessoa<Long> {
	
	private String cref;

	
	@OneToMany(mappedBy = "instrutor")
	private List<AvaliacaoFisica> avaliacoes;

	public Instrutor() {
		
		
	}



	



	public Instrutor(Long id, String nome, String sexo, String estadoCivil, String rg, String cpf, String email,
			String telefone, String profissao, LocalDate dataEntrada, LocalDate dataSaida, Endereco endereco) {
		super(id, nome, sexo, estadoCivil, rg, cpf, email, telefone, profissao, dataEntrada, dataSaida, endereco);
		this.cref= cref;
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
	
	

}
