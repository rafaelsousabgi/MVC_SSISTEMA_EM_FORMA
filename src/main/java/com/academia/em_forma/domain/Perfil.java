package com.academia.em_forma.domain;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.*;


@Entity
@Table(name = "perfis")
public class Perfil implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", nullable = false, unique = true)
	private String desc;
	
	//@ManyToMany(mappedBy = "perfis")
	//private List<Usuario> usuarios;
	
	//@Enumerated(EnumType.STRING)
	//private PerfilTipo perfilTipo;
	
	
	
	public Perfil() {
		super();
	}
	

	public Perfil(Long id, String desc) {
		super();
		this.id = id;
		this.desc = desc;
	}


	public Perfil(Long id) {
		
		this.id = id;
	}

	
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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
		Perfil other = (Perfil) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
}
