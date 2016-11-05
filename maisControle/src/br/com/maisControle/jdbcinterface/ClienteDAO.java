package br.com.maisControle.jdbcinterface;

import java.sql.SQLException;
import java.util.List;

import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Cliente;



public interface ClienteDAO {
	
	public boolean inserir(Cliente cliente) throws ProdutoException, SQLException;
	public List<Cliente> buscarPorNome(String nome,int id) throws ProdutoException;
	public boolean deletarCliente(int id_usuario) throws ProdutoException;
}
