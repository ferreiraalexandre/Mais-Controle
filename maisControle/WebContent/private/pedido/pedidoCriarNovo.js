
MAISCONTROLE.pedido = new Object();
var total=0;
var pedido=new Object();


$(document).ready(function() {
var contatudo = [];

MAISCONTROLE.pedido.buscarProduto = function() {
	var valorBuscaProduto = $("#consultarProduto").val();
	var valorBuscaCodigo= $("#codigo").val();
	
	MAISCONTROLE.pedido.exibirProdutos(undefined, valorBuscaProduto,valorBuscaCodigo);
};


MAISCONTROLE.pedido.exibirProdutos = function(listaDeProdutos, valorBuscaProduto,valorBuscaCodigo) {
		
	
	var html = "";
	
	html += "<thead>" +
				"<tr>" +
					"<th style='display: none;'>IdProduto </th>"+
					"<th>Código</th>" +
					"<th>Nome</th>" +
					"<th>Categoria</th>" +
					"<th>Fornecedor</th>" +
					"<th>Preço</th>" +
					"<th>Qtd.Em.Estoque</th>" +
					"<th>Quantidade</th>" +
					"<th>Selecionar</th>" +
				"</tr>" +
			"</thead>" +
			"<tbody>";
	if (listaDeProdutos != undefined && listaDeProdutos.length > 0 && listaDeProdutos[0].codigo != undefined) {
		for (var i = 0; i < listaDeProdutos.length; i++) {
			html += "<tr>"
					+
					"<td style='display: none;'>"
					+ listaDeProdutos[i].id_produto
					+ "</td>"
					+ "<td>"
					+ listaDeProdutos[i].codigo
					+ "</td>"
					+ "<td>"
					+ listaDeProdutos[i].nome
					+ "</td>"
					+ "<td>"
					+ listaDeProdutos[i].categoria
					+ "</td>"
					+ "<td>"
					+ listaDeProdutos[i].fornecedor
					+ "</td>"
					+ "<td class='td-valor' >"
					+ listaDeProdutos[i].preco_venda 
					+ "</td>"
					+ "<td>"
					+ listaDeProdutos[i].quantidade 
					+ "</td>"
					+"<td class='td-input' id='quantidade'> <input type='text' disabled  style='width:50px;' class='inputqt' /></td>"
					+"<td> <input type='checkbox' id='check' name='opcaoProduto' class='checkclick' value='"+listaDeProdutos+"' /> </td>"

					+ "</tr>";
			
		}
	} else {
		if (listaDeProdutos == undefined|| (listaDeProdutos != undefined && listaDeProdutos.length > 0)) {
			if (valorBuscaProduto == "") {
				valorBuscaProduto = null;
			}
			if (valorBuscaCodigo == "") {
				valorBuscaCodigo = null;
			}
			
			/*
			 * Destaque para a url que referencia o recurso Rest que fará a
			 * busca dos contatos, baseados no valor contido na variável
			 * valorBusca que, por sua vez, também encontra-se referenciada
			 * nesta.
			 */
			MAISCONTROLE.pedidoService.buscarPorNomeProduto({
					data: {'valor1':valorBuscaProduto, 'valor2':valorBuscaCodigo},
					success : function(listaDeProdutos) {
						console.log(listaDeProdutos);
					MAISCONTROLE.pedido.exibirProdutos(listaDeProdutos);
				},
				error : function(err) {
					alert(err.responseText);
				}
			});
		} else {
			html += "<tr><td colspan='3'>Nenhum registro encontrado</td></tr>";
		}
	}
	html += "</tbody>";
	$("#resultadoProdutos").html(html);
	
		$('.checkclick').on('change',function(){
			if($(this).prop('checked')){
				$(this).parent().parent().find("input").removeAttr('disabled').val('1');
			}else{
				$(this).parent().parent().find("input[type='text']").attr('disabled','disabled').val('');
			}
			MAISCONTROLE.pedido.atualizaTotal();
		})
		$(".inputqt").on('change', function(){
			MAISCONTROLE.pedido.atualizaTotal();
		})
		

};// Fecha a declaração do método exibirContatos()

MAISCONTROLE.pedido.atualizaTotal = function(){
	
	var produtos = $("#resultadoProdutos>tbody>tr")
		.map(function(index,item){
			return {
				
				produto: {id_produto : parseInt($(item.children[0]).html())},
				preco : parseFloat($(item.children[5]).html()),
				quantidade : parseInt($(item.children[7]).children()[0].value),				
			};
		})
		.filter(function(index,item){
			return !isNaN(item.quantidade);
		});
	var total = 0;
	contatudo= [];
	
	produtos.each(function(index, item){
	
	total += item.preco * item.quantidade;
	
	//pedido =item;
	contatudo.push(item);
	});
	$("#total").val(total);	
	return contatudo;
	//return pedido;
}


MAISCONTROLE.pedido.exibirProdutos(undefined, "", "");

});



//***********Cliente************




MAISCONTROLE.pedido.buscarCliente = function() {
	var valorBusca = $("#consultarCliente").val();
	MAISCONTROLE.pedido.exibirClientes( undefined,valorBusca);
};


MAISCONTROLE.pedido.exibirClientes = function(listaDeClientes, valorBusca) {
		
	var html = "";
	
	html += "<thead>" +
				"<tr>" +
				"<th>Nome Fantasia</th>" +
				"<th>Razão Social</th>" +
				"<th>CNPJ</th>" +
				"<th>Email</th>" +
				"<th>Telefone</th>" +
				"<th>Selecionar</th>" +
				"</tr>" +
			"</thead>" +
			"<tbody>";
	if (listaDeClientes != undefined && listaDeClientes.length > 0 && listaDeClientes[0].idCliente != undefined) {
		for (var i = 0; i < listaDeClientes.length; i++) {
			html += "<tr>"
					+
					"<td>"
					+ listaDeClientes[i].nomeFantasia
					+ "</td>"
					+ "<td>"
					+ listaDeClientes[i].razaoSocial
					+ "</td>"
					+ "<td>"
					+ listaDeClientes[i].cnpj
					+ "</td>"
					+ "<td>"
					+ listaDeClientes[i].email
					+ "</td>"
					+ "<td>"
					+ listaDeClientes[i].telefone
					+"<td> <input type='radio' name='opcao' class='checkclick' value='"+listaDeClientes[i].idCliente+"'  /> </td>"
					+ "</tr>";
			
		}
	} else {
		if (listaDeClientes == undefined|| (listaDeClientes!= undefined && listaDeClientes.length > 0)) {
			if (valorBusca == "") {
				valorBusca = null;
			}
			
			/*
			 * Destaque para a url que referencia o recurso Rest que fará a
			 * busca dos contatos, baseados no valor contido na variável
			 * valorBusca que, por sua vez, também encontra-se referenciada
			 * nesta.
			 */
			
		
			MAISCONTROLE.pedidoService.buscarPorNomeCliente({
					data:valorBusca,
					success : function(listaDeClientes) {
						MAISCONTROLE.pedido.exibirClientes(listaDeClientes);
				},
				error : function(err) {
					alert(err.responseText);
				}
			});
		} else {
			html += "<tr><td colspan='3'>Nenhum registro encontrado</td></tr>";
		}
	}
	html += "</tbody>";
	$("#resultadoClientes").html(html);
};// Fecha a declaração do método exibirContatos()


//***********Pedido************

MAISCONTROLE.produto.getProduto = function(){
	return{
		codigo : $("#codigoProduto").val(),
		nome : $("#nomeProduto").val(),
		categoria : $("#categoria").val(),
		fornecedor : $("#fornecedor").val(),
		preco_venda : $("#precoVenda").val(),
		preco_custo : $("#precoCusto").val(),
		quantidade : $("#quantidade").val()
		
	};
}

MAISCONTROLE.pedido.selecionarCliente = function() {

var  idCliente=$('input[name="opcao"]:checked').val();
var itensPedido= MAISCONTROLE.pedido.atualizaTotal();
var pedido = {
			cliente : { idCliente:idCliente},
			total : $("#total").val(),
			itens :itensPedido,
			
};
console.log(pedido);


if(idCliente!=undefined && itensPedido.length !=0){
	
	MAISCONTROLE.pedidoService.cadastrar({
		data: pedido,
		success : function(resultado) {
			console.log(resultado);
			if(resultado==null){
				bootbox.alert("Pedido não cadastrado.<br>Produto esta sem estoque!");
			}
			if(resultado.length==0){
				bootbox.alert("Pedido Cadastrado");
				$("#home").load("private/pedido/pedido.html");
			}
		
			else{
			//var html1 = "<label>Produto</label><br>";
			var html1 = "";
			html1 += "<thead>" +
						"<tr>" +
						"<th style='padding-right: 30px ;'> Produto </th>" +
						"<th style='padding-right: 30px ;'> Quantidade No Estoque</th>" +
						"<th style='padding-right: 30px ;'> Quantidade Mínimo </th>" +
						"</tr>" +
					"</thead>" +
					"<tbody>";
			
		
			for (var i = 0; i < resultado.length; i++) {		
		
				
				html1 += "<tr>"
					+
					"<td>"
					+ resultado[i].nome
					+ "</td>"
					+ "<td>"
					+ resultado[i].quantidade
					+ "</td>"
					+ "<td>"
					+ resultado[i].estoque_minimo
					+ "</td>"
					+ "</tr>"
					+ "</tbody>";
				
			}	
				
			bootbox.alert(html1); 
			$("#home").load("private/pedido/pedido.html");
			}
			},// Fecha success
		
		error : function(err) {
			bootbox.alert(err.responseText);

		}// Fechar error
	});// Fecha estrutura Service
}// fecha o else
};// Fecha a function
	



