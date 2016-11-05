package br.com.maisControle.jdbcinterface;

import java.sql.SQLException;
import java.util.List;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Pedido;



public interface PedidoDAO {
	
	public boolean inserir(Pedido pedido) throws ProdutoException, SQLException;
	public List<Pedido> buscarPedido(String id,String cliente,int idEmpresa) throws ProdutoException;
	public List<Pedido> visualizarPedido(int idPedido,String produto) throws ProdutoException;
	public boolean deletarPedido(int idPedido) throws ProdutoException;
	
}
