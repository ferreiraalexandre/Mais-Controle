$(document).ready(function(){
	var parameters = getParameters();
	if (parameters.login == "invalid") {
		$("#home").load("public/login.html",function(){
			$("<p>").addClass('alert alert-danger').text('Usuário e/ou senha inválidos').prependTo('#Login');
		});
	}
})

function carregar(caminho){
	  $("#home").load(caminho);
	  $("#telefone").mask('(99) 9999-9999');
	  $("#telefoneAdm").mask('(99) 9999-9999');
	  $("#telefoneRepr").mask('(99) 9999-9999');
	  $("#cnpj").mask('99.999.999/9999-99');
	  $("#numero").mask('99999999999999');
}

function recarregar(caminho){
	  $("body").load(caminho);
}


function botao(){
	$("#cadastrar_outro").show();
	$("#salvar").attr("onclick", "MAISCONTROLE.produto.cadastrar(1)");
	
}

function botao_adm(){
	$("#cadastrar_outro").show();
	$("#salvar").attr("onclick", "MAISCONTROLE.administrador.cadastrar(1)");
	
}

function botaoRepr(){
	$("#cadastrar_outro").show();
	$("#salvar").attr("onclick", "MAISCONTROLE.representante.cadastrar(1)");
	
}

function botaoClie(){
	$("#cadastrar_outro").show();
	$("#salvar").attr("onclick", "MAISCONTROLE.cliente.cadastrar(1)");
	
}

function botaoPed(){
	$("#cadastrar_outro").show();
	$("#salvar").attr("onclick", "MAISCONTROLE.pedido.cadastrar(1)");
	
}

function getParameters() {
	var parameters = {};
	if (window.location.search != "") {
		var arr = window.location.search.substring(1).split("&");
		arr.forEach(function(item) {
			var arr2 = item.split("=");
			parameters[arr2[0]] = arr2[1];
		});
	}
	return parameters;
}










