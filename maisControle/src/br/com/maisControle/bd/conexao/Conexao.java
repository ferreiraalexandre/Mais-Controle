package br.com.maisControle.bd.conexao;

import java.sql.Connection;
import br.com.maisControle.exception.ProdutoException;

public class Conexao {

	private Connection conexao;
	public Connection abrirConexao() throws ProdutoException, NullPointerException, Exception {
		
		try{
			Class.forName("org.gjt.mm.mysql.Driver");
			conexao = java.sql.DriverManager.getConnection("jdbc:mysql://localhost:3306/maiscontrole","root","");
		}
		catch (Exception e){
			throw new ProdutoException(e.getCause());
			
		}
		return conexao;
	}

	public void fecharConexao() throws ProdutoException{
		try{
			conexao.close();
		}catch (Exception e){
			throw new ProdutoException(e);
		}
	}
}
	


