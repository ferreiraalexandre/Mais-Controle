package br.com.maisControle.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.jdbcinterface.ClienteDAO;
import br.com.maisControle.objetos.Cliente;


public class JDBCClienteDAO implements ClienteDAO {
	private Connection conexao;

	public JDBCClienteDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public boolean inserir(Cliente cliente) throws ProdutoException, SQLException {
		String comando = "select * from cliente ";
		comando += "where e_mail like '" + cliente.getEmail() + "%' ";
		
		String comando1 = "";
	
		java.sql.Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(comando);
		if (!rs.next()) {
			comando1 = "insert into cliente"+
					"(cnpj,razao_social,nome_fantasia,e_mail,telefone,empresa_id_empresa) "+
					"values(?,?,?,?,?,?)";
			PreparedStatement p;
			try{
				
			p = this.conexao.prepareStatement(comando1);
			p.setString(1, cliente.getCnpj());
			p.setString(2, cliente.getRazaoSocial());
			p.setString(3, cliente.getNomeFantasia());
			p.setString(4, cliente.getEmail());
			p.setString(5, cliente.getTelefone());
			p.setInt(6,cliente.getEmpresa().getId_empresa());
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
	

	
	
	
	public List<Cliente> buscarPorNome(String nome, int id) throws ProdutoException {
		String comando = "select * from cliente where empresa_id_empresa= " +id ;
		if (!nome.equals("null") && !nome.equals("")) {
			comando += " and nome_fantasia like '" + nome + "%'";
		}

		List<Cliente> listCliente = new ArrayList<Cliente>();
		Cliente cliente = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				
				cliente = new Cliente();
				cliente.setIdCliente(rs.getInt("id_cliente"));
				cliente.setCnpj(rs.getString("cnpj"));
				cliente.setRazaoSocial(rs.getString("razao_social"));
				cliente.setNomeFantasia(rs.getString("nome_fantasia"));
				cliente.setEmail(rs.getString("e_mail"));
				cliente.setTelefone(rs.getString("telefone"));
				
				
				listCliente.add(cliente);
			}
		} catch (SQLException e) {
			throw new ProdutoException(e);
		}
		return listCliente;

	}

	public boolean deletarCliente(int idCliente) throws ProdutoException {

		String comando = "delete from cliente where id_cliente = " + idCliente;
		Statement p;

		try {
			p = this.conexao.createStatement();
			p.execute(comando);

		} catch (SQLException e) {
			throw new ProdutoException(e);

		}
		return true;
	}
	
	public Cliente buscarPorCodigo(int idCliente) throws ProdutoException {

		String comando = " select * from cliente where id_cliente = " + idCliente;
		Cliente cliente= new Cliente();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			if (rs.next()) {
				
				cliente.setIdCliente(rs.getInt("id_cliente"));
				cliente.setCnpj(rs.getString("cnpj"));
				cliente.setRazaoSocial(rs.getString("razao_social"));
				cliente.setNomeFantasia(rs.getString("nome_fantasia"));
				cliente.setEmail(rs.getString("e_mail"));
				cliente.setTelefone(rs.getString("telefone"));
				
						
			}
		} catch (Exception e) {
			throw new ProdutoException(e);
		}
		return cliente;
	}
	
	public boolean atualizar(Cliente cliente) throws ProdutoException {
		String comando = "Update cliente set " 
				+ "cnpj=?," + "razao_social=?,"
				+ "nome_fantasia=?," + "e_mail=?," + "telefone=? "
				+ "WHERE cliente.id_cliente=?";

		PreparedStatement p;
		try {
			// sSystem.out.println(produto.getCodigo());
			p = this.conexao.prepareStatement(comando);
			p.setString(1, cliente.getCnpj());
			p.setString(2, cliente.getRazaoSocial());
			p.setString(3, cliente.getNomeFantasia());
			p.setString(4, cliente.getEmail());
			p.setString(5, cliente.getTelefone());
			p.setInt(6,cliente.getIdCliente());	
			p.executeUpdate();
		} catch (SQLException e) {
			throw new ProdutoException(e);

		}
		return true;
	}

}
