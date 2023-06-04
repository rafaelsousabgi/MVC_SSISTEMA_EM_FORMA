package com.academia.em_forma.util;

import java.util.List;

public class PaginacaoUtil<T> {
	
	private int tamanho;
	private int pagina;
	private long totalDepaginas;
	private List<T>registros;
	
	
	public PaginacaoUtil(int tamanho, int pagina, long totalDepaginas, List<T> registros) {
		super();
		this.tamanho = tamanho;
		this.pagina = pagina;
		this.totalDepaginas = totalDepaginas;
		this.registros = registros;
	}


	public int getTamanho() {
		return tamanho;
	}


	public int getPagina() {
		return pagina;
	}


	public long getTotalDepaginas() {
		return totalDepaginas;
	}


	public List<T> getRegistros() {
		return registros;
	}
	
	
	
	
	
	

}
