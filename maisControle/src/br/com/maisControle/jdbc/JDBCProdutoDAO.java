package br.com.maisControle.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.jdbcinterface.ProdutoDAO;
import br.com.maisControle.objetos.Produto;

public class JDBCProdutoDAO implements ProdutoDAO {
	private Connection conexao;

	public JDBCProdutoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public boolean inserir(Produto produto) throws ProdutoException {
		String comando = "insert into produto "+
				"(codigo,nome,categoria,fornecedor,preco_venda,preco_custo,quantidade,estoque_minimo,estoque_maximo,empresa_id_empresa) "+
				"values(?,?,?,?,?,?,?,?,?,?)";
	PreparedStatement p;
	try{
		
		p = this.conexao.prepareStatement(comando);
		p.setString(1, produto.getCodigo());
		p.setString(2, produto.getNome());
		p.setString(3, produto.getCategoria());
		p.setString(4, produto.getFornecedor());
		p.setFloat(5, produto.getPreco_venda());
		p.setFloat(6, produto.getPreco_custo());
		p.setInt(7, produto.getQuantidade());
		p.setInt(8, produto.getEstoque_minimo());
		p.setInt(9, produto.getEstoque_maximo());
		p.setLong(10, produto.getEmpresa().getId_empresa());
		p.execute();
		
	}catch (SQLException e){
		e.printStackTrace();
		throw new ProdutoException(e);
	}
	return true;
}

	public List<Produto> buscarPorNome(String nome,String codigo,int id, String tipoUsuario) throws ProdutoException {
	
		String comando = "select * from produto where empresa_id_empresa= " +id  ;
		if (!nome.equals("null") && !nome.equals("")) {
		comando += " and nome like '" + nome + "%'";
		}
		
		if ( !codigo.equals("null") && !codigo.equals("")) {
			comando += " and codigo like '" + codigo + "%'";
		}
		
		
		List<Produto> listProduto = new ArrayList<Produto>();
		Produto produto = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			
			while (rs.next()) {
				produto = new Produto();
				produto.setId_produto(rs.getInt("id_produto"));
				produto.setCodigo(rs.getString("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setFornecedor(rs.getString("fornecedor"));
				produto.setPreco_venda(rs.getFloat("preco_venda"));
				produto.setPreco_custo(rs.getFloat("preco_custo"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setEstoque_minimo(rs.getInt("estoque_minimo"));
				produto.setEstoque_maximo(rs.getInt("estoque_maximo"));
				produto.setTipoUsuario(tipoUsuario);
				listProduto.add(produto);
			}
			
		} catch (SQLException e) {
			throw new ProdutoException(e);
		}
		return listProduto;

	}

	public boolean deletarProduto(int id_produto) throws ProdutoException {

		String comando = "delete from produto where id_produto = " + id_produto;
		Statement p;

		try {
			p = this.conexao.createStatement();
			p.execute(comando);

		} catch (SQLException e) {
			throw new ProdutoException(e);

		}
		return true;
	}

	public Produto buscarPorId(int id_produto) throws ProdutoException {

		String comando = " select * from produto where id_produto = " + id_produto;
		Produto produto = new Produto();
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			if (rs.next()) {

				produto.setCodigo(rs.getString("codigo"));
				produto.setNome(rs.getString("nome"));
				produto.setCategoria(rs.getString("categoria"));
				produto.setFornecedor(rs.getString("fornecedor"));
				produto.setPreco_venda(rs.getFloat("preco_venda"));
				produto.setPreco_custo(rs.getFloat("preco_custo"));
				produto.setQuantidade(rs.getInt("quantidade"));
				produto.setEstoque_minimo(rs.getInt("estoque_minimo"));
				produto.setEstoque_maximo(rs.getInt("estoque_maximo"));
				produto.setId_produto(rs.getInt("id_produto"));

			}
		} catch (Exception e) {
			throw new ProdutoException(e);
		}
		return produto;
	}

	public boolean atualizar(Produto produto) throws ProdutoException {
		String comando = "Update produto set " 
				+ " codigo=?," + "nome=?,"
				+ " categoria=?," + "fornecedor=?," + "preco_venda=?,"
				+ " preco_custo=?," + "quantidade=?,"
				+ " estoque_minimo=?," + "estoque_maximo=?"
				+ " WHERE produto.id_produto=?";

		PreparedStatement p;
		try {
			// sSystem.out.println(produto.getCodigo());
			p = this.conexao.prepareStatement(comando);
			p.setString(1, produto.getCodigo());
			p.setString(2, produto.getNome());
			p.setString(3, produto.getCategoria());
			p.setString(4, produto.getFornecedor());
			p.setFloat(5, produto.getPreco_venda());
			p.setFloat(6, produto.getPreco_custo());
			p.setInt(7, produto.getQuantidade());
			p.setInt(8,  produto.getEstoque_minimo());
			p.setInt(9,  produto.getEstoque_maximo());
			p.setLong(10, produto.getId_produto());
			p.executeUpdate();
		} catch (SQLException e) {
			throw new ProdutoException(e);

		}
		return true;
	}	
}
