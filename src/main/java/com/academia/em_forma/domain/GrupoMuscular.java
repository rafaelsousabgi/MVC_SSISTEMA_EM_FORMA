package com.academia.em_forma.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import org.hibernate.mapping.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="TB_GRUPO_MUSCULAR")
public class GrupoMuscular implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "grupoMuscular")
	private List<Exercicio> exercicios;
	
	public GrupoMuscular() {
		
	}
	
	public GrupoMuscular(Long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public List<Exercicio> getExercicios() {
		return exercicios;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GrupoMuscular other = (GrupoMuscular) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
