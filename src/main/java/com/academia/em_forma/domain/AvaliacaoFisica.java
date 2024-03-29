package com.academia.em_forma.domain;

import java.io.Serializable;

import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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
@Table(name = "TB_AVALIACOESFISICAS")
public class AvaliacaoFisica implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false, length = 100)
	private String identificacao;

	@NotNull(message = "Informe o Aluno que foi avaliado")
	@ManyToOne
	@JoinColumn(name = "aluno_id_fk")
	private Aluno aluno;

	@NotNull(message = "Informe o Instrutor que realizou a avaliação")
	@ManyToOne
	@JoinColumn(name = "instrutor_id_fk")
	private Instrutor instrutor;

	@NotNull(message = "Informe o tipo fisico")
	@Column(nullable = false, length = 9)
	@Enumerated(EnumType.STRING)
	private TIPOFISICO tipofisico;

	/**
	 * @JsonIgnore
	 * @OneToMany(mappedBy = "avaliacaoFisica") private List<FichaTreino>
	 *                     fichaTreinos;
	 **/

	@JsonIgnore
	@OneToMany(mappedBy = "avaliacaoFisica", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<FichaTreino> fichaTreinos;

	@NumberFormat(style = Style.NUMBER) // , pattern ="##,##0.00" ,columnDefinition = "DECIMAL(7,2) DEFAULT 00.00" )
	@NotNull(message = "Informe o peso")
	@Column(nullable = false)
	private Double peso;

	@NumberFormat(style = Style.NUMBER) // , pattern ="##,##0.00", columnDefinition = "DECIMAL(7,2) DEFAULT 00.00")
	@NotNull(message = "Informe a altura")
	@Column(nullable = false)
	private Double alturaa;

	private String classificacaoIMC;

	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double peito;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double cintura;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double panturrilhaDireita;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double panturrilhaEsquerda;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double coxaDireita;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double coxaEsqueda;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double bracoEsquedo;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double bracoDireito;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double antebracoEsquedo;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double antebracoDireito;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(nullable = false, columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private Double gluteo;
	@NumberFormat(style = Style.NUMBER, pattern = "#,##0.00")
	@Column(columnDefinition = "DECIMAL(7,2) DEFAULT 0.00")
	private double imc;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_inicio", nullable = false, columnDefinition = "DATE")
	private LocalDate dataInicio;

	@DateTimeFormat(iso = ISO.DATE)
	@Column(name = "data_fim", columnDefinition = "DATE")
	private LocalDate dataFim;

	public AvaliacaoFisica() {

	}

	public AvaliacaoFisica(Long id, Double peso, Double alturaa, Double peito, Double cintura,
			Double panturrilhaDireita, Double panturrilhaEsquerda, Double coxaDireita, Double coxaEsqueda,
			Double bracoEsquedo, Double bracoDireito, Double antebracoEsquedo, Double antebracoDireito, Double gluteo,
			Double imc, String identificacao, LocalDate dataInicio, LocalDate dataFim) {
		super();
		this.id = id;
		this.peso = peso;
		this.alturaa = alturaa;
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
		// this.imc = imc = (peso /((alturaa * alturaa)/100));
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.identificacao = identificacao;
		// recalcularIMC();

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

	public Double getPeso() {
		return peso;

	}

	public void setPeso(Double peso) {
		this.peso = peso;
		calcularIMC();
	}

	public Double getAlturaa() {
		return alturaa;

	}

	public void setAlturaa(Double alturaa) {
		this.alturaa = alturaa;
		calcularIMC();
	}

	public Double getPeito() {
		return peito;
	}

	public void setPeito(Double peito) {
		this.peito = peito;
	}

	public Double getCintura() {
		return cintura;
	}

	public void setCintura(Double cintura) {
		this.cintura = cintura;
	}

	public Double getPanturrilhaDireita() {
		return panturrilhaDireita;
	}

	public void setPanturrilhaDireita(Double panturrilhaDireita) {
		this.panturrilhaDireita = panturrilhaDireita;
	}

	public Double getPanturrilhaEsquerda() {
		return panturrilhaEsquerda;
	}

	public void setPanturrilhaEsquerda(Double panturrilhaEsquerda) {
		this.panturrilhaEsquerda = panturrilhaEsquerda;
	}

	public Double getCoxaDireita() {
		return coxaDireita;
	}

	public void setCoxaDireita(Double coxaDireita) {
		this.coxaDireita = coxaDireita;
	}

	public Double getCoxaEsqueda() {
		return coxaEsqueda;
	}

	public void setCoxaEsqueda(Double coxaEsqueda) {
		this.coxaEsqueda = coxaEsqueda;
	}

	public Double getBracoEsquedo() {
		return bracoEsquedo;
	}

	public void setBracoEsquedo(Double bracoEsquedo) {
		this.bracoEsquedo = bracoEsquedo;
	}

	public Double getBracoDireito() {
		return bracoDireito;
	}

	public void setBracoDireito(Double bracoDireito) {
		this.bracoDireito = bracoDireito;
	}

	public Double getAntebracoEsquedo() {
		return antebracoEsquedo;
	}

	public void setAntebracoEsquedo(Double antebracoEsquedo) {
		this.antebracoEsquedo = antebracoEsquedo;
	}

	public Double getAntebracoDireito() {
		return antebracoDireito;
	}

	public void setAntebracoDireito(Double antebracoDireito) {
		this.antebracoDireito = antebracoDireito;
	}

	public Double getGluteo() {
		return gluteo;
	}

	public void setGluteo(Double gluteo) {
		this.gluteo = gluteo;
	}

	private void calcularIMC() {
		if (peso != null && alturaa != null) {
			double alturaEmMetros = alturaa / 100;
			imc = peso / (alturaEmMetros * alturaEmMetros);
		} else {
			imc = 0;
		}
	}

	public String getClassificacaoIMC() {

		if (imc != 0) {
			if (imc < 18.5) {
				return "Magreza";
			} else if (imc < 25.0) {
				return "Normal";
			} else if (imc < 30.0) {
				return "Sobrepeso";
			} else if (imc < 40.0) {
				return "Obesidade";
			} else {
				return "Obesidade Grave";
			}
		} else {
			return "Não calculado";
		}
	}

	public void setClassificacaoIMC(String classificacaoIMC) {
		this.classificacaoIMC = classificacaoIMC;
	}

	public Double getImc() {
		return imc;
	}

	public void setImc(Double imc) {
		this.imc = imc;
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public TIPOFISICO getTipofisico() {
		return tipofisico;
	}

	public void setTipofisico(TIPOFISICO tipofisico) {
		this.tipofisico = tipofisico;
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
		AvaliacaoFisica other = (AvaliacaoFisica) obj;
		return Objects.equals(id, other.id);
	}

	/**
	 * @Override public String toString() { return "AvaliacaoFisica [id=" + id + ",
	 *           identificacao=" + identificacao + ", aluno=" + aluno + ",
	 *           instrutor=" + instrutor + ", fichaTreinos=" + fichaTreinos + ",
	 *           peso=" + peso + ", alturaa=" + alturaa + ", peito=" + peito + ",
	 *           cintura=" + cintura + ", panturrilhaDireita=" + panturrilhaDireita
	 *           + ", panturrilhaEsquerda=" + panturrilhaEsquerda + ", coxaDireita="
	 *           + coxaDireita + ", coxaEsqueda=" + coxaEsqueda + ", bracoEsquedo="
	 *           + bracoEsquedo + ", bracoDireito=" + bracoDireito + ",
	 *           antebracoEsquedo=" + antebracoEsquedo + ", antebracoDireito=" +
	 *           antebracoDireito + ", gluteo=" + gluteo + ", imc=" + imc + ",
	 *           dataInicio=" + dataInicio + ", dataFim=" + dataFim + "]"; }
	 * 
	 **/
}
