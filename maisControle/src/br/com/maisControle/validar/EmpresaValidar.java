package br.com.maisControle.validar;

import br.com.maisControle.exception.CampoVazio;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Empresa;


public class EmpresaValidar {

	public void validar(Empresa empresa) throws ProdutoException {
		String msg = "";
		msg += validaString(empresa.getNome_fantasia(), "Nome Fantasia");
		msg += validaString(empresa.getRazao_social(), "Razão Social");
		msg += validaString(empresa.getEmail(), "Email");
		msg += validaString(empresa.getTelefone(), "Telefone");
		msg += validaString(empresa.getPais(), "País");
		msg += validaString(empresa.getEstado(), "Estado");
		msg += validaString(empresa.getCidade(), "Cidade");
		msg += validaString(empresa.getEndereco(), "Endereço");
		msg += validaString(empresa.getBairro(), "Bairro");
		msg += validaString(empresa.getNumero(), "Número");
		msg += validaString(empresa.getComplemento(), "Complemento");
		msg += validaString(empresa.getCnpj(), "CNPJ");
		

		
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

