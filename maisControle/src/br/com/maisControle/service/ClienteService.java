package br.com.maisControle.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.maisControle.bd.conexao.Conexao;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.jdbc.JDBCClienteDAO;
import br.com.maisControle.jdbc.JDBCPedidoDAO;
import br.com.maisControle.objetos.Cliente;
import br.com.maisControle.objetos.Pedido;
import br.com.maisControle.validar.ClienteValidar;



public class ClienteService {

	public String addCliente(Cliente cliente) throws ProdutoException {
		ClienteValidar valid = new ClienteValidar();
		valid.validar(cliente);
		
		
		
		Conexao conec = new Conexao();

		try {
			Connection conexao = conec.abrirConexao();
			JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
			boolean retorno =	jdbcCliente.inserir(cliente);
	String msg="";
			if(retorno==true){
		msg="Cliente cadastrado com sucesso";
	}else{
		msg="Email ja existente";
	}
			return msg;
			
		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e);
		} finally {
			conec.fecharConexao();

		}
	}

	public List<Cliente> buscarclientesPorNome(String nome,int id)
			throws ProdutoException {
		Conexao conec = new Conexao();
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			Connection conexao = conec.abrirConexao();
			JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
			clientes = jdbcCliente.buscarPorNome(nome,id);

		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();

		}
		return clientes;
	}
	
	public boolean deletarCliente(int idCliente, int idEmpresa) throws ProdutoException {
		Conexao conec = new Conexao();
		List<Pedido> pedidos = new ArrayList<Pedido>();

		try {
			Connection conexao = conec.abrirConexao();
			JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
			JDBCPedidoDAO jdbcPedido = new JDBCPedidoDAO(conexao);

			pedidos = jdbcPedido.validarDelete("cliente", idCliente, idEmpresa);
			
			if (pedidos.size()>0) {
				return false;
			} else {

				jdbcCliente.deletarCliente(idCliente);
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

	public Cliente buscarClientePeloCodigo(int idCliente) throws ProdutoException {
		Cliente cliente = new Cliente();
		Conexao conec = new Conexao();
		try {

			Connection conexao = conec.abrirConexao();
			JDBCClienteDAO jdbcCliente= new JDBCClienteDAO(conexao);
			cliente = jdbcCliente.buscarPorCodigo(idCliente);

		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();

		}
		return cliente;
	}
	
	public void editarCliente(Cliente cliente) throws ProdutoException {
		
		Conexao conec = new Conexao();

		try {
			Connection conexao = conec.abrirConexao();
			JDBCClienteDAO jdbcCliente = new JDBCClienteDAO(conexao);
			jdbcCliente.atualizar(cliente);
		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();
		}

	}	
}	
	
	 
	
	
