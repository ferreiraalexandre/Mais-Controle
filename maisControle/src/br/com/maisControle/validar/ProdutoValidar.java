package br.com.maisControle.validar;

import br.com.maisControle.exception.CampoVazio;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Produto;

public class ProdutoValidar {

	public void validar(Produto produto) throws ProdutoException {
		String msg = "";
		msg += validaString(produto.getCodigo(), "Codigo");
		msg += validaString(produto.getNome(), "Nome");
		msg += validaString(produto.getCategoria(), "Categoria");
		msg += validaString(produto.getFornecedor(), "Fornecedor");
		msg += validaFloat(produto.getPreco_venda(), "Preço de Venda");
		msg += validaFloat(produto.getPreco_custo(), "Preço de Custo");
		msg += validaFloat(produto.getQuantidade(), "Quantidade");

		if (!msg.trim().equals("")) {
			throw new CampoVazio(msg);
		}
	}

	private String validaString(String valor, String campo) {

		String msg = "";

		if (valor.trim().equals("")) {
			msg += "<br/>Preencha o campo" + campo;
		}
		return msg;
	}

private String validaFloat(float valor, String campo) {

	String msg = "";
	
	if (valor<0 ) {
		msg += "<br/>Preencha o campo" + campo;
	}
	return msg;
}

}
