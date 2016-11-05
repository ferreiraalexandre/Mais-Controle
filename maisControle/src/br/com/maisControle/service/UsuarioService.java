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
import br.com.maisControle.jdbc.JDBCPedidoDAO;
import br.com.maisControle.jdbc.JDBCUsuarioDAO;
import br.com.maisControle.objetos.Pedido;
import br.com.maisControle.objetos.Usuario;
import br.com.maisControle.validar.UsuarioValidar;




public class UsuarioService {

	public String addUsuario(Usuario usuario) throws ProdutoException {
	
		UsuarioValidar valid = new UsuarioValidar();
		valid.validar(usuario);
		String senha = convertPassword(usuario.getSenha());
		usuario.setSenha(senha);
		
		
		Conexao conec = new Conexao();

		try {
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			boolean retorno =	jdbcUsuario.inserir(usuario);
	String msg="";
			if(retorno==true){
		msg="Usuario cadastrado com sucesso";
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

	public List<Usuario> buscarUsuarioPorNome(String nome, String tipo, int id) throws ProdutoException {
		Conexao conec = new Conexao();
		List<Usuario> usuario = new ArrayList<Usuario>();
		try {
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			usuario = jdbcUsuario.buscarPorNome(nome,tipo,id);

		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();

		}
		return usuario;
	}
	
	public boolean deletarUsuario(int id_usuario, int idEmpresa) throws ProdutoException {
		Conexao conec = new Conexao();
		
		List<Pedido> pedidos = new ArrayList<Pedido>();
		
		try {
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			JDBCPedidoDAO jdbcPedido = new JDBCPedidoDAO(conexao);
			
			pedidos = jdbcPedido.validarDelete("usuario", id_usuario, idEmpresa);
			
			if (pedidos.size()>0) {
				return false;
			} else {
				
			}
				jdbcUsuario.deletarUsuario(id_usuario);
		
		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();
		}
		return true;
	}

	public Usuario buscarUsuarioPeloCodigo(int id_usuario) throws ProdutoException {
		Usuario usuario = new Usuario();
		Conexao conec = new Conexao();
		try {

			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario= new JDBCUsuarioDAO(conexao);
			usuario = jdbcUsuario.buscarPorCodigo(id_usuario);

		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();

		}
		return usuario;
	}
	
	public void editarUsuario(Usuario usuario) throws ProdutoException {
		
		UsuarioValidar valid = new UsuarioValidar();
		valid.validar(usuario);
		String senha = convertPassword(usuario.getSenha());
		usuario.setSenha(senha);
		Conexao conec = new Conexao();

		try {
			Connection conexao = conec.abrirConexao();
			JDBCUsuarioDAO jdbcUsuario = new JDBCUsuarioDAO(conexao);
			jdbcUsuario.atualizar(usuario);
		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();
		}

	}

	private String convertPassword(String senha){

		System.out.println("Senha base64: " + senha);
		
		
		byte[] decoded = Base64.getDecoder().decode(senha);
		senha= new String(decoded, StandardCharsets.UTF_8);
		System.out.println("Senha base64 Decod: "+ senha);
		
		String senhaMD5 = "";
		try {
			senhaMD5 = ConvertMD5.convertMD5(senha);
			System.out.println("Senha MD5 : " + senhaMD5);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return senhaMD5;
	}

	
	
}	
	
	 
	
	
