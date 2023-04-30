package com.academia.em_forma.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

import java.util.Set;


import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name ="TB_EXERCICIOS")
public class Exercicio implements Serializable{

	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String nomeEquipamento;
	private int serie;	
	private int repeticao;
	
	@Column(nullable = false, length = 3)
	@Enumerated(EnumType.STRING)
	private DIA dia;
	
	
	@ManyToMany
	@JoinTable(name = "TB_TREINO_EXERCICIOS",
	joinColumns = @JoinColumn(name="EXERCICIO_ID"),
	inverseJoinColumns = @JoinColumn(name="FICHATREINO_ID"))
	private List<FichaTreino> fichaTreinos;
	
	@ManyToMany
	@JoinTable(name = "TB_EXERCICIOS_GRUPOMUSCULAR",
			joinColumns = @JoinColumn(name="EXERCICIO_ID"),
			inverseJoinColumns = @JoinColumn(name="GRUPOMUSCULAR_ID"))
	private List<GrupoMuscular> gruposMusculares;
	
	public Exercicio() {
		
	}
	
	public Exercicio(Long id, String nomeEquipamento, int serie, int repeticao) {
		super();
		this.id = id;
		this.nomeEquipamento = nomeEquipamento;
		this.serie = serie;
		this.repeticao = repeticao;
		
		
		
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	public String getNomeEquipamento() {
		return nomeEquipamento;
	}
	public void setNomeEquipamento(String nomeEquipamento) {
		this.nomeEquipamento = nomeEquipamento;
	}
	public int getSerie() {
		return serie;
	}
	public void setSerie(int serie) {
		this.serie = serie;
	}
	public int getRepeticao() {
		return repeticao;
	}
	public void setRepeticao(int repeticao) {
		this.repeticao = repeticao;
	}	
	
	public DIA getDia() {
		return dia;
	}

	public void setDia(DIA dia) {
		this.dia = dia;
	}

	public List<FichaTreino> getFichaTreino() {
		return fichaTreinos;
	}
	
	public void setFichaTreino(List<FichaTreino> fichaTreinos) {
		this.fichaTreinos = fichaTreinos;
	}
	
	public List<GrupoMuscular> getGrupoMuscular() {
		return gruposMusculares;
	}
	
	
	public void setGrupoMuscular(List<GrupoMuscular> gruposMusculares) {
		this.gruposMusculares = gruposMusculares;
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
		Exercicio other = (Exercicio) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
