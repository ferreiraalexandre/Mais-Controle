package br.com.maisControle.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Usuario;


public class JDBCUsuarioDAO  {
	private Connection conexao;

	public JDBCUsuarioDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public boolean inserir(Usuario usuario) throws ProdutoException, SQLException {
		String comando = "select * from usuario ";
		comando += "where e_mail like '" + usuario.getEmail() + "%' ";
		
		String comando1 = "";
	
		java.sql.Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(comando);
		if (!rs.next()) {
			comando1 = "insert into usuario"+
					"(nome,e_mail,telefone,pais,estado,cidade,endereco,bairro,numero,complemento,senha,tipo_de_usuario,empresa_id_empresa) "+
					"values(?,?,?,?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement p;
			try{
				
			p = this.conexao.prepareStatement(comando1);
			p.setString(1, usuario.getNome());
			p.setString(2, usuario.getEmail());
			p.setString(3, usuario.getTelefone());
			p.setString(4, usuario.getPais());
			p.setString(5, usuario.getEstado());
			p.setString(6, usuario.getCidade());
			p.setString(7, usuario.getEndereco());
			p.setString(8, usuario.getBairro());
			p.setString(9, usuario.getNumero());
			p.setString(10, usuario.getComplemento());
			p.setString(11, usuario.getSenha());
			p.setString(12,usuario.getTipo());
			p.setInt(13,usuario.getEmpresa().getId_empresa());
			p.execute();
			
	}catch (SQLException e){
		e.printStackTrace();
		throw new ProdutoException(e);
	}
			return true;
}else{
	
return false;
}
	}
	

	
	
	
	public List<Usuario> buscarPorNome(String nome, String tipo, int id) throws ProdutoException {		

		//String comando = "select * from usuario where empresa_id_empresa='+ id ' and (tipo_de_usuario='master' or tipo_de_usuario='administrador')"  ;
	  String comando = "select * from usuario where empresa_id_empresa='"+ id + "'and tipo_de_usuario='"+tipo+"'"  ;

		if (!nome.equals("null") && !nome.equals("")) {
			comando += " and nome like '" + nome + "%'";
		}

		List<Usuario> listUsuario = new ArrayList<Usuario>();
		Usuario usuario = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				
				usuario = new Usuario();
				usuario.setId(rs.getInt("id_usuario"));
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("e_mail"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setEstado(rs.getString("estado"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setNumero(rs.getString("numero"));
				usuario.setComplemento(rs.getString("complemento"));
				usuario.setTipo(rs.getString("tipo_de_usuario"));
				
				listUsuario.add(usuario);
			}
		} catch (SQLException e) {
			throw new ProdutoException(e);
		}	
		System.out.println(listUsuario);
		return listUsuario;
		}
		

	public boolean deletarUsuario(int id_usuario) throws ProdutoException {

		String comando = "delete from usuario where id_usuario = " + id_usuario;
		Statement p;

		try {
			p = this.conexao.createStatement();
			p.execute(comando);

		} catch (SQLException e) {
			throw new ProdutoException(e);

		}
		return true;
	}
	
	public Usuario buscarPorCodigo(int id_usuario) throws ProdutoException {

		String comando = " select * from usuario where id_usuario = " + id_usuario;
		Usuario usuario= new Usuario();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			if (rs.next()) {
				
				usuario.setNome(rs.getString("nome"));
				usuario.setEmail(rs.getString("e_mail"));
				usuario.setTelefone(rs.getString("telefone"));
				usuario.setPais(rs.getString("pais"));
				usuario.setEstado(rs.getString("estado"));
				usuario.setCidade(rs.getString("cidade"));
				usuario.setEndereco(rs.getString("endereco"));
				usuario.setBairro(rs.getString("bairro"));
				usuario.setNumero(rs.getString("numero"));
				usuario.setComplemento(rs.getString("complemento"));
		
			}
		} catch (Exception e) {
			throw new ProdutoException(e);
		}
		return usuario;
	}
	
	public boolean atualizar(Usuario usuario) throws ProdutoException {
		String comando = "Update usuario set " 
				+ "nome=?," + "e_mail=?,"
				+ "telefone=?," + "pais=?," + "estado=?,"
				+ "cidade=?," + "endereco=?, "
				+ "bairro=?," + "numero=?,"
				+ "complemento=?," + "senha=? " 
				+ "WHERE usuario.id_usuario=?";

		PreparedStatement p;
		try {
			// sSystem.out.println(produto.getCodigo());
			p = this.conexao.prepareStatement(comando);
			p.setString(1, usuario.getNome());
			p.setString(2, usuario.getEmail());
			p.setString(3, usuario.getTelefone());
			p.setString(4, usuario.getPais());
			p.setString(5, usuario.getEstado());
			p.setString(6, usuario.getCidade());
			p.setString(7, usuario.getEndereco());
			p.setString(8, usuario.getBairro());
			p.setString(9, usuario.getNumero());
			p.setString(10, usuario.getComplemento());
			p.setString(11, usuario.getSenha());
			p.setInt(12,usuario.getId());	
			p.executeUpdate();
		} catch (SQLException e) {
			throw new ProdutoException(e);

		}
		return true;
	}
}
