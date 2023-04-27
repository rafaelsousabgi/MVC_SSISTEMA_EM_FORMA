package com.academia.em_forma.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "TB_AVALIACAOFISICA")
public class AvaliacaoFisica implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private BigDecimal peso;
	private BigDecimal altura;
	private BigDecimal peito;
	private BigDecimal cintura;
	private BigDecimal panturrilhaDireita;
	private BigDecimal panturrilhaEsquerda;
	private BigDecimal coxaDireita;
	private BigDecimal coxaEsqueda;
	private BigDecimal bracoEsquedo;
	private BigDecimal bracoDireito;
	private BigDecimal antebracoEsquedo;
	private BigDecimal antebracoDireito;
	private BigDecimal gluteo;
	private BigDecimal imc;
	
	@Column(name = "data_inicio", nullable = false, columnDefinition = "DATE")
	private LocalDate dataInicio;
	@Column(name = "data_fim", columnDefinition = "DATE")
	private LocalDate dataFim;
	
	@ManyToOne
	@JoinColumn(name="id_aluno")
	private Aluno aluno;
	
	@ManyToOne
	@JoinColumn(name="id_instrutor")
	private Instrutor instrutor;

	@OneToMany(mappedBy = "avaliacao")
	private List<FichaTreino> fichaTreino;
	
	public AvaliacaoFisica() {
		
	}

	public AvaliacaoFisica(Long id, BigDecimal peso, BigDecimal altura, BigDecimal peito, BigDecimal cintura,
			BigDecimal panturrilhaDireita, BigDecimal panturrilhaEsquerda, BigDecimal coxaDireita, BigDecimal coxaEsqueda,
			BigDecimal bracoEsquedo, BigDecimal bracoDireito, BigDecimal antebracoEsquedo, BigDecimal antebracoDireito, BigDecimal gluteo,
			BigDecimal imc, LocalDate dataInicio, LocalDate dataFim, Aluno aluno, Instrutor instrutor,
			List<FichaTreino> fichaTreino) {
		super();
		this.id = id;
		this.peso = peso;
		this.altura = altura;
		this.peito = peito;
		this.cintura = cintura;
		this.panturrilhaDireita = panturrilhaDireita;
		this.panturrilhaEsquerda = panturrilhaEsquerda;
		this.coxaDireita = coxaDireita;
		this.coxaEsqueda = coxaEsqueda;
		this.bracoEsquedo = bracoEsquedo;
		this.bracoDireito = bracoDireito;
		this.antebracoEsquedo = antebracoEsquedo;
		this.antebracoDireito = antebracoDireito;
		this.gluteo = gluteo;
		this.imc = imc;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.aluno = aluno;
		this.instrutor = instrutor;
		this.fichaTreino = fichaTreino;
	}
	
	

	public LocalDate getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}

	public LocalDate getDataFim() {
		return dataFim;
	}

	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}

	public Instrutor getInstrutor() {
		return instrutor;
	}

	public void setInstrutor(Instrutor instrutor) {
		this.instrutor = instrutor;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getPeito() {
		return peito;
	}

	public void setPeito(BigDecimal peito) {
		this.peito = peito;
	}

	public BigDecimal getCintura() {
		return cintura;
	}

	public void setCintura(BigDecimal cintura) {
		this.cintura = cintura;
	}

	public BigDecimal getPanturrilhaDireita() {
		return panturrilhaDireita;
	}

	public void setPanturrilhaDireita(BigDecimal panturrilhaDireita) {
		this.panturrilhaDireita = panturrilhaDireita;
	}

	public BigDecimal getPanturrilhaEsquerda() {
		return panturrilhaEsquerda;
	}

	public void setPanturrilhaEsquerda(BigDecimal panturrilhaEsquerda) {
		this.panturrilhaEsquerda = panturrilhaEsquerda;
	}

	public BigDecimal getCoxaDireita() {
		return coxaDireita;
	}

	public void setCoxaDireita(BigDecimal coxaDireita) {
		this.coxaDireita = coxaDireita;
	}

	public BigDecimal getCoxaEsqueda() {
		return coxaEsqueda;
	}

	public void setCoxaEsqueda(BigDecimal coxaEsqueda) {
		this.coxaEsqueda = coxaEsqueda;
	}

	public BigDecimal getBracoEsquedo() {
		return bracoEsquedo;
	}

	public void setBracoEsquedo(BigDecimal bracoEsquedo) {
		this.bracoEsquedo = bracoEsquedo;
	}

	public BigDecimal getBracoDireito() {
		return bracoDireito;
	}

	public void setBracoDireito(BigDecimal bracoDireito) {
		this.bracoDireito = bracoDireito;
	}

	public BigDecimal getAntebracoEsquedo() {
		return antebracoEsquedo;
	}

	public void setAntebracoEsquedo(BigDecimal antebracoEsquedo) {
		this.antebracoEsquedo = antebracoEsquedo;
	}

	public BigDecimal getAntebracoDireito() {
		return antebracoDireito;
	}

	public void setAntebracoDireito(BigDecimal antebracoDireito) {
		this.antebracoDireito = antebracoDireito;
	}

	public BigDecimal getGluteo() {
		return gluteo;
	}

	public void setGluteo(BigDecimal gluteo) {
		this.gluteo = gluteo;
	}

	public BigDecimal getImc() {
		return imc;
	}

	public void setImc(BigDecimal imc) {
		this.imc = imc;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	
}
