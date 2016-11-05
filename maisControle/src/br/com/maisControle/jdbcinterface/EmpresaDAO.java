package br.com.maisControle.jdbcinterface;

import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Empresa;

public interface EmpresaDAO {
	
	public Empresa inserir(Empresa empresa) throws ProdutoException;
	
}
