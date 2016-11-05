package br.com.maisControle.jdbcinterface;


import java.util.List;

import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Relatorio;



public interface RelatorioDAO {
	
	
	public List<Relatorio> buscarProduto(int id) throws ProdutoException;
	public List<Relatorio> buscarCliente(int id) throws ProdutoException;
	public List<Relatorio> buscarRepresentante(int id) throws ProdutoException;
	
}
