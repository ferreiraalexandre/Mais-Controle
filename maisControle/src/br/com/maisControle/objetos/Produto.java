package br.com.maisControle.objetos;

import java.io.Serializable;

public class Produto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int id_produto;
	private String codigo;
	private String nome;
	private String categoria;
	private String fornecedor;
	private float preco_venda;
	private float preco_custo;
	private int quantidade;
	private int estoque_minimo;
	private int estoque_maximo;
	private Empresa empresa;
	
	public int getId_produto() {
		return id_produto;
	}
	public void setId_produto(int id_produto) {
		this.id_produto = id_produto;
	}
	
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getFornecedor() {
		return fornecedor;
	}
	public void setFornecedor(String fornecedor) {
		this.fornecedor = fornecedor;
	}
	public float getPreco_venda() {
		return preco_venda;
	}
	public void setPreco_venda(float preco_venda) {
		this.preco_venda = preco_venda;
	}
	public float getPreco_custo() {
		return preco_custo;
	}
	public void setPreco_custo(float preco_custo) {
		this.preco_custo = preco_custo;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}
	public int getEstoque_minimo() {
		return estoque_minimo;
	}
	public void setEstoque_minimo(int estoque_minimo) {
		this.estoque_minimo = estoque_minimo;
	}
	public int getEstoque_maximo() {
		return estoque_maximo;
	}
	public void setEstoque_maximo(int estoque_maximo) {
		this.estoque_maximo = estoque_maximo;
	}
	

	
}
