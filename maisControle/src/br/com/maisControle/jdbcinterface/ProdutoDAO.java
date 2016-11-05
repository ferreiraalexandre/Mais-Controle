package br.com.maisControle.jdbcinterface;

import java.util.List;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Produto;

public interface ProdutoDAO {
	
	public boolean inserir(Produto produto) throws ProdutoException;
	public List<Produto> buscarPorNome(String nome,String codigo, int id) throws ProdutoException;
	public boolean deletarProduto(int id) throws ProdutoException;
	public Produto buscarPorId(int id_produto) throws ProdutoException;
	public boolean atualizar(Produto produto) throws ProdutoException;
}
