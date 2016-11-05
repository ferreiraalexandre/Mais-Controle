package br.com.maisControle.validar;

import br.com.maisControle.exception.CampoVazio;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Cliente;



public class ClienteValidar {

	public void validar(Cliente cliente) throws ProdutoException {
		String msg = "";
		msg += validaString(cliente.getCnpj(), "Cnpj");
		msg += validaString(cliente.getRazaoSocial(), "Razão Social");
		msg += validaString(cliente.getNomeFantasia(), "Nome Fantasia");
		msg += validaString(cliente.getEmail(), "Email");
		msg += validaString(cliente.getTelefone(), "Telefone");
				

		
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
}

