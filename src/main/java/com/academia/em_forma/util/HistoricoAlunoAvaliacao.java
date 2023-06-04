package com.academia.em_forma.util;

import com.academia.em_forma.domain.Aluno;
import com.academia.em_forma.domain.Instrutor;

public interface HistoricoAlunoAvaliacao {
	
	Long getId();
	
	Aluno getAluno();
	
	String getDatasAvaliacao();
	
	Instrutor getInstrutor();
	
	Double getAltura();
	
	Double getPeito();
	
	Double getPeso();
	
	Double getCintura();
	
	Double getPanturrilhaDireita();
	
	Double getPanturrilhaEsquerda();
	
	Double getCoxaDireita();
	
	Double getCoxaEsquerda();
	
	Double getBracoEsquerdo();
	
	Double getBracoDireito();
	
	Double getAntebracoEsquerdo();
	
	Double getAntebracoDireito();
	
	Double getGluteo();
	
	Double getImc();
	
	String datasAvaliacao();

}