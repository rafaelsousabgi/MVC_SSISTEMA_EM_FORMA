package com.academia.em_forma.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.format.annotation.NumberFormat;

import org.springframework.format.annotation.NumberFormat.Style;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.persistence.*;

@Entity
@Table(name = "TB_AVALIACAOFISICA")
public class AvaliacaoFisica implements Serializable {

	
	private static final long serialVersionUID = 1L;
	
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	@Column(nullable = false ,length = 100)
	private String identificacao;
	
	

	@NotNull(message = "Informe o Aluno que foi avaliado")
	@ManyToOne
	@JoinColumn(name="aluno_id")
	private Aluno aluno;
	
	@NotNull(message = "Informe o Instrutor que realizou a avaliação")
	@ManyToOne
	@JoinColumn(name="instrutor_id")
	private Instrutor instrutor;

	@JsonIgnore
	@OneToMany(mappedBy = "avaliacaoFisica")
	private List<FichaTreino> fichaTreinos;
	
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal peso;
	
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal altura;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal peito;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal cintura;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal panturrilhaDireita;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal panturrilhaEsquerda;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal coxaDireita;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal coxaEsqueda;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal bracoEsquedo;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal bracoDireito;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal antebracoEsquedo;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal antebracoDireito;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal gluteo;
	@NumberFormat(style = Style.NUMBER, pattern ="#,##0.00" )
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private BigDecimal imc;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_inicio", nullable = false, columnDefinition = "DATE")
	private LocalDate dataInicio;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_fim", columnDefinition = "DATE")
	private LocalDate dataFim;
	
	
	
	public AvaliacaoFisica() {
		
	}

	public AvaliacaoFisica(Long id, BigDecimal peso, BigDecimal altura, BigDecimal peito, BigDecimal cintura,
			BigDecimal panturrilhaDireita, BigDecimal panturrilhaEsquerda, BigDecimal coxaDireita, BigDecimal coxaEsqueda,
			BigDecimal bracoEsquedo, BigDecimal bracoDireito, BigDecimal antebracoEsquedo, BigDecimal antebracoDireito, BigDecimal gluteo,
			BigDecimal imc,String identificacao, LocalDate dataInicio, LocalDate dataFim) {
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
		this.identificacao = identificacao;
		
	}
	
	

	public List<FichaTreino> getFichaTreinos() {
		return fichaTreinos;
	}

	public void setFichaTreinos(List<FichaTreino> fichaTreinos) {
		this.fichaTreinos = fichaTreinos;
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
	

	public String getIdentificacao() {
		return identificacao;
	}

	public void setIdentificacao(String identificacao) {
		this.identificacao = identificacao;
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
