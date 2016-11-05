package br.com.maisControle.objetos;

import java.io.Serializable;

public class Relatorio implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	
	private String nome;
	private double total;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	
	
}