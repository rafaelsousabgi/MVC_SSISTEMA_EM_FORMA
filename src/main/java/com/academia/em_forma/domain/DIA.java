package com.academia.em_forma.domain;

public enum DIA {
	
	SEG("SEG", "Segunda"), 
	TER("TER", "Terça"),
	QUA("QUA", "Quarta"),
	QUI("QUI", "Quinta"),
	SEX("SEX", "Sexta"),
	SAB("SAB", "Sabado"),
	DOM("DOM", "Domindo");
	
	private String sigla;
	private String descricao;
	
	DIA(String sigla, String descricao) {
		this.sigla = sigla;
		this.descricao = descricao;
	}

	public String getSigla() {
		return sigla;
	}

	public String getDescricao() {
		return descricao;
	}


}
