package br.com.maisControle.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.jdbcinterface.RelatorioDAO;
import br.com.maisControle.objetos.Relatorio;


public class JDBCRelatorioDAO implements RelatorioDAO {
	private Connection conexao;

	public JDBCRelatorioDAO(Connection conexao) {
		this.conexao = conexao;
	}

		
	public List<Relatorio> buscarProduto(int id) throws ProdutoException {
		
		String comando= "select p.id_produto,p.nome,php.preco, sum(php.quantidade*php.preco) total  FROM produto p"
		+ " join pedidos_has_produto php on (php.produto_id_produto=p.id_produto)"
		+ " where (p.empresa_id_empresa=" + id + ")"
		+ " order by total desc limit 5" ;

		List<Relatorio> listRelatorio= new ArrayList<Relatorio>();
		Relatorio relatorio = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				
				relatorio = new Relatorio();
				relatorio.setNome(rs.getString("p.nome"));					
				relatorio.setTotal(rs.getDouble("total"));
				listRelatorio.add(relatorio);
			}
		} catch (SQLException e) {
			throw new ProdutoException(e);
		}
	
		return listRelatorio;

	}

public List<Relatorio> buscarCliente(int id) throws ProdutoException {
		
	String comando= "select c.nome_fantasia,c.id_cliente, sum(php.preco*php.quantidade) total FROM cliente c"
	 + " join pedidos ped on (ped.cliente_id_cliente=c.id_cliente)" 
	 + " join pedidos_has_produto php on (ped.id_pedido=php.pedidos_id_pedido)"
	 + " where (c.empresa_id_empresa=" + id + ")"
	 + " order by total desc limit 5" ;
	
		List<Relatorio> listRelatorio= new ArrayList<Relatorio>();
		Relatorio relatorio = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {
				
				relatorio = new Relatorio();
				relatorio.setNome(rs.getString("c.nome_fantasia"));					
				relatorio.setTotal(rs.getDouble("total"));
				listRelatorio.add(relatorio);
			}
		} catch (SQLException e) {
			throw new ProdutoException(e);
		}
	
		return listRelatorio;

	}

public List<Relatorio> buscarRepresentante(int id) throws ProdutoException {
	
	String comando="select u.id_usuario,u.nome,ped.id_pedido, sum(php.preco*php.quantidade) total FROM usuario u"
    + " join pedidos ped on (ped.usuario_id_usuario=u.id_usuario)"
    + " join pedidos_has_produto php on (ped.id_pedido=php.pedidos_id_pedido)"
    + " where (" + id + " =u.empresa_id_empresa)" 
    + " order by total desc limit 5";

	List<Relatorio> listRelatorio= new ArrayList<Relatorio>();
	Relatorio relatorio = null;
	try {
		java.sql.Statement stmt = conexao.createStatement();
		ResultSet rs = stmt.executeQuery(comando);
		while (rs.next()) {
			
			relatorio = new Relatorio();
			relatorio.setNome(rs.getString("nome"));					
			relatorio.setTotal(rs.getDouble("total"));
			listRelatorio.add(relatorio);
		}
	} catch (SQLException e) {
		throw new ProdutoException(e);
	}

	return listRelatorio;

}	
}
