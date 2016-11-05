package br.com.maisControle.validar;

import br.com.maisControle.exception.CampoVazio;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Usuario;



public class PedidoValidar {

	public void validar(Usuario usuario) throws ProdutoException {
		String msg = "";
		msg += validaString(usuario.getNome(), "Nome");
		msg += validaString(usuario.getEmail(), "Email");
		msg += validaString(usuario.getTelefone(), "Telefone");
		msg += validaString(usuario.getPais(), "Pais");
		msg += validaString(usuario.getEstado(), "Estado");
		msg += validaString(usuario.getCidade(), "Cidade");
		msg += validaString(usuario.getEndereco(), "Endereco");
		msg += validaString(usuario.getBairro(), "Bairro");
		msg += validaString(usuario.getNumero(), "Numero");
		msg += validaString(usuario.getSenha(), "Senha");
		msg += validaString(usuario.getConfirmaSenha(), "Confirma Senha");
		

		
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

