package br.com.maisControle.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;

import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Cliente;
import br.com.maisControle.objetos.Pedido;
import br.com.maisControle.objetos.Produto;
import br.com.maisControle.objetos.Usuario;

public class JDBCPedidoDAO {
	private Connection conexao;

	public JDBCPedidoDAO(Connection conexao) {
		this.conexao = conexao;
	}

	public Pedido inserirPedido(Pedido pedido) throws ProdutoException {
		String comando = "insert into pedidos " + "(cliente_id_cliente,usuario_id_usuario) " + "values(?,?)";

		PreparedStatement p;
		int idPedido = 0;
		try {

			p = this.conexao.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
			p.setInt(1, pedido.getCliente().getIdCliente());
			p.setInt(2, pedido.getUsuario().getId());
			p.execute();

			ResultSet rs = p.getGeneratedKeys();
			while (rs.next()) {
				idPedido = rs.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProdutoException(e);
		}
		pedido.setIdPedido(idPedido);
		return pedido;

	}

	public boolean inserirItens(Pedido pedido) throws ProdutoException {
		String comando = "insert into pedidos_has_produto " + "(pedidos_id_pedido,produto_id_produto,quantidade,preco) "
				+ "values(?,?,?,?)";

		PreparedStatement p;
		try {

			for (int i = 0; i < pedido.getItens().length; i++) {

				p = this.conexao.prepareStatement(comando);
				p.setInt(1, pedido.getIdPedido());
				p.setInt(2, pedido.getItens()[i].getProduto().getId_produto());
				p.setInt(3, pedido.getItens()[i].getQuantidade());
				p.setDouble(4, pedido.getItens()[i].getPreco());
				p.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProdutoException(e);
		}
		return true;
	}

	public boolean atualizarEstoque(Pedido pedido) throws ProdutoException {
		String comando = "Update produto set " + " quantidade=quantidade-?" + " WHERE produto.id_produto=?";

		PreparedStatement p;
		try {

			for (int i = 0; i < pedido.getItens().length; i++) {

				p = this.conexao.prepareStatement(comando);
				p.setInt(1, pedido.getItens()[i].getQuantidade());
				p.setInt(2, pedido.getItens()[i].getProduto().getId_produto());

				p.execute();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new ProdutoException(e);
		}
	return true;

	}

	
	public List<Pedido> buscarPedido(String id, String cliente,int idEmpresa) throws ProdutoException {

		String comando = "select id_pedido,cliente_id_cliente,usuario_id_usuario,nome_fantasia,nome, count(z.produto_id_produto) quantidade,sum(z.preco*z.quantidade) as total from  pedidos p"
				+ " join cliente e on (p.cliente_id_cliente = e.id_cliente)"
				+ " join usuario u on (p.usuario_id_usuario = u.id_usuario)"
				+ " join pedidos_has_produto z on (p.id_pedido = z.pedidos_id_pedido)"
                + " where u.empresa_id_empresa=" + idEmpresa ;

		if (!id.equals("null") && !id.equals("")) {
			comando += " and id_pedido like '" + id + "%'";
		}
		if (!cliente.equals("null") && !cliente.equals("")) {
			comando += " and nome_fantasia like '" + cliente + "%' group by p.id_pedido";
		} else {
			comando += " group by p.id_pedido";
		}

		List<Pedido> listPedido = new ArrayList<Pedido>();
		Pedido pedido = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {

				pedido = new Pedido();
				pedido.setIdPedido(rs.getInt("id_pedido"));
				pedido.setQuantidade(rs.getInt("quantidade"));
				pedido.setTotal(rs.getDouble("total"));

				pedido.setCliente(new Cliente());
				pedido.getCliente().setNomeFantasia(rs.getString("nome_fantasia"));

				pedido.setUsuario(new Usuario());
				pedido.getUsuario().setNome(rs.getString("nome"));

				listPedido.add(pedido);
			}
		} catch (SQLException e) {
			throw new ProdutoException(e);
		}
		return listPedido;

	}

	public boolean deletarPedido(int idPedido) throws ProdutoException {

		String comando = "delete from pedidos_has_produto where pedidos_id_pedido = " + idPedido;
		String comando1 = "delete from pedidos where id_pedido = " + idPedido;
		Statement p;

		try {
			p = this.conexao.createStatement();
			p.execute(comando);
			p.execute(comando1);

		} catch (SQLException e) {
			throw new ProdutoException(e);

		}
		return true;
	}

	public List<Pedido> visualizarPedido(int idPedido, String produto) throws ProdutoException {
		String comando = "select p.id_pedido,p.cliente_id_cliente,p.usuario_id_usuario,e.nome_fantasia,u.nome,x.preco_venda,x.nome,z.quantidade,x.nome,(x.preco_venda*z.quantidade) as total_item   from  pedidos p"
				+ " join cliente e on (p.cliente_id_cliente=e.id_cliente)"
				+ " join usuario u on (p.usuario_id_usuario=u.id_usuario)"
				+ " join pedidos_has_produto z on (p.id_pedido=z.pedidos_id_pedido)"
				+ " join produto x on (x.id_produto=z.produto_id_produto)" + " and id_pedido like '" + idPedido + "%'";

		if (!produto.equals("null") && !produto.equals("undefined")) {
			comando += " and x.nome like '" + produto + "%'";
		}

		List<Pedido> listPedido = new ArrayList<Pedido>();
		Pedido pedido = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {

				pedido = new Pedido();
				pedido.setIdPedido(rs.getInt("id_pedido"));
				pedido.setQuantidade(rs.getInt("z.quantidade"));
				pedido.setTotal(rs.getDouble("total_item"));

				pedido.setCliente(new Cliente());
				pedido.getCliente().setNomeFantasia(rs.getString("nome_fantasia"));

				pedido.setUsuario(new Usuario());
				pedido.getUsuario().setNome(rs.getString("u.nome"));

				pedido.setProduto(new Produto());
				pedido.getProduto().setNome(rs.getString("x.nome"));
				pedido.getProduto().setPreco_venda(rs.getFloat("preco_venda"));

				listPedido.add(pedido);
			}
		} catch (SQLException e) {
			throw new ProdutoException(e);
		}
		return listPedido;

	}

	public List<Pedido> validarDelete(String string, int id, int idEmpresa) throws ProdutoException {
		
		String comando= " select php.pedidos_id_pedido,php.produto_id_produto, ped.usuario_id_usuario,ped.cliente_id_cliente FROM usuario u" 
        + " join empresa e on  (u.empresa_id_empresa=" + idEmpresa + ")"
        + " join pedidos ped on  (ped.usuario_id_usuario=u.id_usuario)"
        + " join pedidos_has_produto  php on  (php.pedidos_id_pedido=ped.id_pedido)";
        		
		if (string=="produto") {
			comando += "  where php.produto_id_produto=" + id 
					+  " group by php.pedidos_id_pedido";
		}
		
		if (string=="usuario") {
			comando += "  where ped.usuario_id_usuario=" + id 
					+  " group by ped.usuario_id_usuario";
		}
		if (string=="cliente") {
			comando += "  where ped.cliente_id_cliente=" + id 
					+  " group by ped.cliente_id_cliente ";
		}
		
		
		List<Pedido> listPedido = new ArrayList<Pedido>();
		Pedido pedido = null;
		try {
			java.sql.Statement stmt = conexao.createStatement();
			ResultSet rs = stmt.executeQuery(comando);
			while (rs.next()) {

				pedido = new Pedido();
				pedido.setIdPedido(rs.getInt("php.pedidos_id_pedido"));

				listPedido.add(pedido);
			}
		} catch (SQLException e) {
			throw new ProdutoException(e);
		}
		return listPedido;

	}
		
	}


