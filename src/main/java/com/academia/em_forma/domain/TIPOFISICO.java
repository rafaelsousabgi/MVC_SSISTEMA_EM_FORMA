package com.academia.em_forma.domain;

public enum TIPOFISICO {
	
	ECTOMORFO("ECTOMORFO","pessoas mais magras, com pequena porcentagem de gordura"),
	MESAMORFO("MESAMORFO", "Porte fisico mais esbelto e definido. Ombros largos, massa corporal bem distribuida."),
	ENDOMORFO("ENDOMORFO","O corpo é mais arredondado, com acumulo de gordura na região abdominal" );
	
	
	private String sigla;
	private String descricao;
	
	TIPOFISICO(String sigla, String descricao) {
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
