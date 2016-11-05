$(document).ready(function() {
	
	
	MAISCONTROLE.validar = function (newConta,valor){	
	
	var msgTel="";
	var msg="";
	var msgEmail="";
	var msgCnpj="";
	var msgSenha="";
	var msgEstoque="";
	var validaFone= /^\(?\d{2}\)?[\s-]?\d{4}-?\d{4}$/;
	var validaEmail= /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
	var validaCnpj=  /^(\d{2}\.?\d{3}\.?\d{3}\/?\d{4}-?\d{2})$/;

	if (valor=="empresa"){
		msg += MAISCONTROLE.validaMsg(newConta.nome_fantasia, 'Nome');
		msg += MAISCONTROLE.validaMsg(newConta.razao_social, 'Razão Social');
		msg += MAISCONTROLE.validaMsg(newConta.cnpj, 'CNPJ');
		msg += MAISCONTROLE.validaMsg(newConta.email, 'E-mail');
		msg += MAISCONTROLE.validaMsg(newConta.telefone, 'Telefone');
		msg += MAISCONTROLE.validaMsg(newConta.pais, 'País');
		msg += MAISCONTROLE.validaMsg(newConta.estado, 'Estado');
		msg += MAISCONTROLE.validaMsg(newConta.cidade, 'Cidade');
		msg += MAISCONTROLE.validaMsg(newConta.endereco, 'Endereço');
		msg += MAISCONTROLE.validaMsg(newConta.bairro, 'Bairro');
		msg += MAISCONTROLE.validaMsg(newConta.numero, 'Número');
		if(newConta.telefone==""){ msg+=msgTel;}
		else if(!validaFone.test(newConta.telefone))
		{ msg+='<br/>Número de telefone inválido'; }
		
		if(newConta.email==""){ msg+=msgEmail; }
		else if(!validaEmail.test(newConta.email))
		{ msg+='<br/>E-mail inválido';}

		if(newConta.cnpj==""){ msg+=msgCnpj; }
		else if(!validaCnpj.test(newConta.cnpj))
		{ msg+='<br/>CNPJ inválido';}

		if(msg != ""){ bootbox.alert('Preencha os campos:'+msg); return false }
		else{ return true; }
		
	}
	
	if (valor=="usuario"){

		msg += MAISCONTROLE.validaMsg(newConta.nome, 'Nome');
		msg += MAISCONTROLE.validaMsg(newConta.email, 'E-mail');
		msg += MAISCONTROLE.validaMsg(newConta.telefone, 'Telefone');
		msg += MAISCONTROLE.validaMsg(newConta.pais, 'País');
		msg += MAISCONTROLE.validaMsg(newConta.estado, 'Estado');
		msg += MAISCONTROLE.validaMsg(newConta.cidade, 'Cidade');
		msg += MAISCONTROLE.validaMsg(newConta.endereco, 'Endereço');
		msg += MAISCONTROLE.validaMsg(newConta.bairro, 'Bairro');
		msg += MAISCONTROLE.validaMsg(newConta.numero, 'Número');
		msg += MAISCONTROLE.validaMsg(newConta.senha, 'Senha');
		msg += MAISCONTROLE.validaMsg(newConta.confirmaSenha, 'Confirma Senha');
		
		if(newConta.telefone==""){ msg+=msgTel;}
		else if(!validaFone.test(newConta.telefone))
		{ msg+='<br/>Número de telefone inválido'; }
		
		if(newConta.email==""){ msg+=msgEmail; }
		else if(!validaEmail.test(newConta.email))
		{ msg+='<br/>E-mail inválido';}

		if(newConta.senha == newConta.confirmaSenha){ msg+=msgSenha; }
		else 
		{ msg+='<br/>Senhas são diferentes';}

		
		if(msg != ""){ bootbox.alert('Preencha os campos:'+msg); return false }
		else{ return true; }
	}
	
	if (valor=="produto"){
		
		msg += MAISCONTROLE.validaMsg(newConta.codigo, 'Codigo');
		msg += MAISCONTROLE.validaMsg(newConta.nome, 'Nome');
		msg += MAISCONTROLE.validaMsg(newConta.categoria, 'Categoria');
		msg += MAISCONTROLE.validaMsg(newConta.fornecedor, 'Fornecedor');
		msg += MAISCONTROLE.validaMsg(newConta.preco_venda, 'Preço Venda');
		msg += MAISCONTROLE.validaMsg(newConta.preco_custo, 'Preço Custo');
		msg += MAISCONTROLE.validaMsg(newConta.estoque_minimo, 'Estoque Mínimo');
		msg += MAISCONTROLE.validaMsg(newConta.estoque_maximo, 'Estoque Máximo');
		msg += MAISCONTROLE.validaMsg(newConta.quantidade, 'Quantidade');
		if(newConta.estoqueMinimo==""||newConta.estoqueMaximo==""){ msg+=msgEstoque;}
		//else if(newConta.estoqueMaximo <= newConta.estoqueMinimo)
		//{ msg+='<br/>Quantidade no estoque inválido'; }
				
		if(msg != ""){ bootbox.alert('Preencha os campos:'+msg); return false }
		else{ return true; }
	}
	

	if (valor=="cliente"){
		
		msg += MAISCONTROLE.validaMsg(newConta.cnpj, 'CNPJ');
		msg += MAISCONTROLE.validaMsg(newConta.razaoSocial, 'Razão Social');
		msg += MAISCONTROLE.validaMsg(newConta.nomeFantasia, 'Nome Fantasia');
		msg += MAISCONTROLE.validaMsg(newConta.email, 'E-mail');
		msg += MAISCONTROLE.validaMsg(newConta.telefone, 'Telefone');
		if(newConta.telefone==""){ msg+=msgTel;}
		else if(!validaFone.test(newConta.telefone))
		{ msg+='<br/>Número de telefone inválido'; }
		
		if(newConta.email==""){ msg+=msgEmail; }
		else if(!validaEmail.test(newConta.email))
		{ msg+='<br/>E-mail inválido';}

		if(newConta.cnpj==""){ msg+=msgCnpj; }
		else if(!validaCnpj.test(newConta.cnpj))
		{ msg+='<br/>CNPJ inválido';}

		if(msg != ""){ bootbox.alert('Preencha os campos:'+msg); return false }
		else{ return true; }
	
}
	
	}
	
	
	
	
	
		
	

	



	
	MAISCONTROLE.validaMsg = function(valor, campo){
		var msg='';
		
		if(($.trim(valor))==""){ msg +='<br/>'+campo; }
		return msg;
	};
});
