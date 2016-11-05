package br.com.maisControle.service;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import br.com.maisControle.bd.conexao.Conexao;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.exemploCriptografia.ConvertMD5;
import br.com.maisControle.jdbc.JDBCClienteDAO;
import br.com.maisControle.jdbc.JDBCPedidoDAO;
import br.com.maisControle.jdbc.JDBCProdutoDAO;
import br.com.maisControle.objetos.Cliente;
import br.com.maisControle.objetos.ItemPedido;
import br.com.maisControle.objetos.Pedido;
import br.com.maisControle.objetos.Produto;
import br.com.maisControle.validar.PedidoValidar;

public class PedidoService {

	public List<Produto> addPedido(Pedido pedido) throws ProdutoException {
		Produto produtos = new Produto();
		Conexao conec = new Conexao();

		List<Produto> listEstoque = new ArrayList<Produto>();
		try {
			Connection conexao = conec.abrirConexao();
			JDBCPedidoDAO jdbcPedido = new JDBCPedidoDAO(conexao);
			JDBCProdutoDAO jdbcProduto = new JDBCProdutoDAO(conexao);

			// listPedido = jdbcPedido.validarEstoque(pedido);

			for (int i = 0; i < pedido.getItens().length; i++) {

				produtos = jdbcProduto.buscarPorId(pedido.getItens()[i].getProduto().getId_produto());
				int estoque = produtos.getQuantidade() - pedido.getItens()[i].getQuantidade();
				
				if (estoque < 0) {
					return null;
				}
				if (estoque < produtos.getEstoque_minimo()) {
					produtos.setQuantidade(estoque);
					listEstoque.add(produtos);
				}
			}

			pedido = jdbcPedido.inserirPedido(pedido);
			jdbcPedido.inserirItens(pedido);
			jdbcPedido.atualizarEstoque(pedido);

		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e);
		} finally {
			conec.fecharConexao();

		}
		return listEstoque;

	}

	public List<Pedido> buscarPedido(String id, String cliente,int idEmpresa) throws ProdutoException {
		Conexao conec = new Conexao();
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			Connection conexao = conec.abrirConexao();
			JDBCPedidoDAO jdbcPedido = new JDBCPedidoDAO(conexao);
			pedidos = jdbcPedido.buscarPedido(id, cliente,idEmpresa);

		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();

		}
		return pedidos;
	}

	public void deletarPedido(int idPedido) throws ProdutoException {
		Conexao conec = new Conexao();

		try {
			Connection conexao = conec.abrirConexao();
			JDBCPedidoDAO jdbcPedido = new JDBCPedidoDAO(conexao);
			jdbcPedido.deletarPedido(idPedido);
		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();
		}
	}

	public List<Pedido> visualizarPedido(int idPedido, String produto) throws ProdutoException {
		Conexao conec = new Conexao();
		List<Pedido> pedidos = new ArrayList<Pedido>();
		try {
			Connection conexao = conec.abrirConexao();
			JDBCPedidoDAO jdbcPedido = new JDBCPedidoDAO(conexao);
			pedidos = jdbcPedido.visualizarPedido(idPedido, produto);

		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();

		}
		return pedidos;
	}

}
