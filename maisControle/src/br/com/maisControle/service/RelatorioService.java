package br.com.maisControle.service;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import br.com.maisControle.bd.conexao.Conexao;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.jdbc.JDBCClienteDAO;
import br.com.maisControle.jdbc.JDBCRelatorioDAO;
import br.com.maisControle.objetos.Cliente;
import br.com.maisControle.objetos.Relatorio;
import br.com.maisControle.validar.ClienteValidar;



public class RelatorioService {

	

	public List<Relatorio> buscar(String tipoRelatorio,int id) throws ProdutoException {
		Conexao conec = new Conexao();
		List<Relatorio> relatorios= new ArrayList<Relatorio>();
		try {
			Connection conexao = conec.abrirConexao();
			JDBCRelatorioDAO jdbcRelatorio= new JDBCRelatorioDAO(conexao);
			
			if(tipoRelatorio.equals("1")){
				relatorios = jdbcRelatorio.buscarProduto(id);
			}
			if(tipoRelatorio.equals("2")){
				relatorios = jdbcRelatorio.buscarCliente(id);
				}
			if(tipoRelatorio.equals("3")){
				relatorios = jdbcRelatorio.buscarRepresentante(id);
				}
			
			
			
		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e.getMessage());
		} finally {
			conec.fecharConexao();

		}
		return relatorios;
	}
	
	}	
	
	 
	
	
