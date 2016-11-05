package br.com.maisControle.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.maisControle.bd.conexao.Conexao;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.jdbc.JDBCPedidoDAO;
import br.com.maisControle.jdbc.JDBCProdutoDAO;
import br.com.maisControle.objetos.Pedido;
import br.com.maisControle.objetos.Produto;
import br.com.maisControle.validar.ProdutoValidar;

public class ProdutoService {

	public void addProduto(Produto produto) throws ProdutoException {
		ProdutoValidar valid = new ProdutoValidar();
		valid.validar(produto);
		Conexao conec = new Conexao();

		try {
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			jdbcProduto.inserir(produto);
		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e);
		} finally {
			conec.fecharConexao();

		}
	}

	public List<Produto> buscarProdutosPorNome(String nome, String codigo, int id) throws ProdutoException {
		Conexao conec = new Conexao();
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			produtos = jdbcProduto.buscarPorNome(nome, codigo, id);

		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();

		}
		return produtos;
	}

	public boolean deletarProduto(int id_produto, int idEmpresa) throws ProdutoException {
		Conexao conec = new Conexao();

		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			JDBCPedidoDAO jdbcPedido = new JDBCPedidoDAO(conexao);

			pedidos = jdbcPedido.validarDelete("produto", id_produto, idEmpresa);
			
			if (pedidos.size()>0) {
				return false;
			} else {

				jdbcProduto.deletarProduto(id_produto);
			}
		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();
		}
		return true;

	}

	public Produto buscarProdutoPeloId(int id_produto) throws ProdutoException {
		Produto produto = new Produto();
		Conexao conec = new Conexao();
		try {

			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			produto = jdbcProduto.buscarPorId(id_produto);

		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();

		}
		return produto;
	}

	public void editarProduto(Produto produto) throws ProdutoException {
		Conexao conec = new Conexao();

		try {
			Connection conexao = conec.abrirConexao();
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);
			jdbcProduto.atualizar(produto);
		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();
		}

	}

}
