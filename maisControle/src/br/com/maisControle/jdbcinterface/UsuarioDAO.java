package br.com.maisControle.jdbcinterface;

import java.sql.SQLException;
import java.util.List;

import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Usuario;



public interface UsuarioDAO {
	
	public boolean inserir(Usuario usuario) throws ProdutoException, SQLException;
	public List<Usuario> buscarPorNome(String nome) throws ProdutoException;
	public boolean deletarUsuario(int id_usuario) throws ProdutoException;
}
