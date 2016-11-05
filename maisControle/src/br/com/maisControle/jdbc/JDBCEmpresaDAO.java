package br.com.maisControle.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.jdbcinterface.EmpresaDAO;
import br.com.maisControle.objetos.Empresa;
import br.com.maisControle.objetos.Usuario;


public class JDBCEmpresaDAO implements EmpresaDAO {
	private Connection conexao;

	public JDBCEmpresaDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public Empresa inserir(Empresa empresa) throws ProdutoException {
		String comando = "insert into empresa "+
				"(nome_fantasia,razao_social,cnpj,email,telefone,pais,estado,cidade,endereco,bairro,numero,complemento) "+
				"values(?,?,?,?,?,?,?,?,?,?,?,?)";
	PreparedStatement p;
	int id= 0;
	try{
		
		p = this.conexao.prepareStatement(comando,PreparedStatement.RETURN_GENERATED_KEYS);
		p.setString(1, empresa.getNome_fantasia());
		p.setString(2, empresa.getRazao_social());
		p.setString(3, empresa.getCnpj());
		p.setString(4, empresa.getEmail());
		p.setString(5, empresa.getTelefone());
		p.setString(6, empresa.getPais());
		p.setString(7, empresa.getEstado());
		p.setString(8, empresa.getCidade());
		p.setString(9, empresa.getEndereco());
		p.setString(10, empresa.getBairro());
		p.setString(11, empresa.getNumero());
		p.setString(12, empresa.getComplemento());
		p.execute();
		
		ResultSet rs = p.getGeneratedKeys();
	while (rs.next()) {
		    id = rs.getInt(1);
		}
		
	}catch (SQLException e){
		e.printStackTrace();
		throw new ProdutoException(e);
	}
	empresa.setId_empresa(id);
	return empresa;
}
	
	public Usuario login(Usuario usuario) {
		Usuario resultado = null;
		String sqls = "SELECT e_mail, senha, empresa_id_empresa, id_usuario " + "FROM usuario "
				+ "where e_mail='" + usuario.getEmail() + "' and senha='"
				+ usuario.getSenha() + "'";
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(sqls);
			
			while(rs.next()){
				resultado = new Usuario();
				resultado.setEmpresa(new Empresa());
				resultado.getEmpresa().setId_empresa(rs.getInt("empresa_id_empresa"));
				resultado.setId(rs.getInt("id_usuario"));

		}
		} catch (SQLException e) {
			e.printStackTrace();
	
		}
		return resultado;
	}

	
}