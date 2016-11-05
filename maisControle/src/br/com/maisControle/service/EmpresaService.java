package br.com.maisControle.service;

import java.sql.Connection;
import br.com.maisControle.bd.conexao.Conexao;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.jdbc.JDBCEmpresaDAO;
import br.com.maisControle.objetos.Empresa;
import br.com.maisControle.validar.EmpresaValidar;


public class EmpresaService {

	public Empresa addEmpresa(Empresa empresa) throws ProdutoException {
		EmpresaValidar valid = new EmpresaValidar();
		valid.validar(empresa);
		Conexao conec = new Conexao();

		
		try {
			Connection conexao = conec.abrirConexao();
			JDBCEmpresaDAO jdbcEmpresa = new JDBCEmpresaDAO(conexao);
			
			empresa=jdbcEmpresa.inserir(empresa);
		} catch (ProdutoException e) {
			throw e;
		} catch (Exception e) {
			throw new ProdutoException(e);
		} finally {
			conec.fecharConexao();

		}
		return empresa;
	}
}	
	
	 
	
	
