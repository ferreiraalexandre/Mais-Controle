MAISCONTROLE.pedido = new Object();

var soma=0;
var idPedido=0;
$(document).ready(function() {


MAISCONTROLE.pedido.buscarPedido = function() {
	
	var valorBusca = $("#consultarPedido").val();
	var valorBuscaCliente = $("#consultarCliente").val();
	
	MAISCONTROLE.pedido.exibirPedidos(undefined, valorBusca,valorBuscaCliente);
};
	

MAISCONTROLE.pedido.exibirPedidos = function(listaDePedidos, valorBusca,valorBuscaCliente) {

	var html = "";			
	html += "<thead>" +
				"<tr>" +
					"<th>Pedido</th>" +
					"<th>Cliente</th>" +
					"<th>Usuário</th>" +
					"<th>Quant.Itens</th>" +
					"<th>Total</th>" +
					"<th>Ações</th>" +
				"</tr>" +
			"</thead>" +
			"<tbody>";
	if (listaDePedidos != undefined && listaDePedidos.length > 0 && listaDePedidos[0].idPedido != undefined) {
		for (var i = 0; i < listaDePedidos.length; i++) {
			html += "<tr>"
					+
					"<td>"
					+ listaDePedidos[i].idPedido
					+ "</td>"
					+ "<td>"
					+ listaDePedidos[i].cliente.nomeFantasia
					+ "</td>"
					+ "<td>"
					+ listaDePedidos[i].usuario.nome
					+ "</td>"
					+ "<td>"
					+ listaDePedidos[i].quantidade
					+ "</td>"
					+ "<td>"
					+ listaDePedidos[i].total
					+ "</td>"
					+ "<td class='actions'>"
					+ "<a class='btn btn-warning btn-xs' onclick='MAISCONTROLE.pedido.visualizarPedido("+ listaDePedidos[i].idPedido+ ")'>Visualizar</a>"
					+ "<a class='btn btn-danger btn-xs' style='margin-left: 10px' onclick='MAISCONTROLE.pedido.deletarPedido("+ listaDePedidos[i].idPedido+ ")'>Deletar</a>" + "</td>"
					+ "</tr>";
		}
	} else {
		if (listaDePedidos == undefined|| (listaDePedidos != undefined && listaDePedidos.length > 0)) {
			if (valorBusca == "") {
				valorBusca = null;
			}

						
			MAISCONTROLE.pedidoService.buscarPedido({			
				data: {'valor1':valorBusca, 'valor2':valorBuscaCliente},
				success : function(listaDePedidos) {
					console.log(listaDePedidos)
					MAISCONTROLE.pedido.exibirPedidos(listaDePedidos);
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
	$("#resultadoPedidos").html(html);
};// Fecha a declaração do método exibirContatos()

MAISCONTROLE.pedido.exibirPedidos(undefined, "","");


MAISCONTROLE.pedido.deletarPedido = function(idPedido){
	
	bootbox.dialog({
		  message: "Deseja deletar o Pedido?",
		  buttons: {
		    success: {
		      label: "Sim",
		      className: "btn-success",
		      callback: function(result) {
		    	  if(result){
		    		  MAISCONTROLE.pedidoService.deletarPedido({
		    			  		data:idPedido,
								success: function (data){
									MAISCONTROLE.pedido.buscarPedido();		
								},
								error: function (err){
									alert(err.reponseText);
								}
		    		  		});
		    		  }	
	      }
		    },
	    danger: {
	      label: "Não",
	      className: "btn-danger",
	      callback: function() {}
	    },
	  }
	});
};
});

MAISCONTROLE.pedido.visualizarPedido = function(valorBusca,listaDePedidos,produto) {
	
	var html = "";			
	html += "<thead>" +
				"<tr>" +
					"<th>Pedido</th>" +
					"<th>Cliente</th>" +
					"<th>Usuário</th>" +
					"<th>Produto</th>" +
					"<th>Preço</th>" +
					"<th>Quantidade</th>" +
					"<th>Total Produto</th>" +
				"</tr>" +
			"</thead>" +
			"<tbody>";
	if (listaDePedidos != undefined && listaDePedidos.length > 0 && listaDePedidos[0].idPedido != undefined) {
		
		for (var i = 0; i < listaDePedidos.length; i++) {
			 soma+= listaDePedidos[i].total;
			 idPedido=listaDePedidos[0].idPedido;
			 
			html += "<tr>"
					+ "<td id='id'>"
					+ listaDePedidos[i].idPedido 
					+ "</td>"
					+ "<td >"
					+ listaDePedidos[i].cliente.nomeFantasia
					+ "</td>"
					+ "<td>"
					+ listaDePedidos[i].usuario.nome
					+ "</td>"
					+ "<td>"
					+ listaDePedidos[i].produto.nome
					+ "</td>"
					+ "<td>"
					+ listaDePedidos[i].produto.preco_venda
					+ "</td>"
					+ "<td>"
					+ listaDePedidos[i].quantidade
					+ "</td>"
					+ "<td>"
					+ listaDePedidos[i].total
					+ "</td>"
					+ "</tr>";												
		}
			
	} else {
		if (listaDePedidos == undefined|| (listaDePedidos != undefined && listaDePedidos.length > 0)) {
			if (valorBusca == "") {
				valorBusca = null;
			}
					
			MAISCONTROLE.pedidoService.visualizarPedido({			
				data: {'valor1':valorBusca, 'valor2':produto},
				success : function(listaDePedidos) {
					console.log(listaDePedidos)
					MAISCONTROLE.pedido.visualizarPedido(undefined,listaDePedidos);
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
	$("#resultadoPedidos").html(html);
	$("#tot").css("display", "block") 
	$("#filtroPedido").css("display", "none")
	$("#filtroProduto").css("display", "block")
	$("#total").val(soma);
};// Fecha a declaração do método exibirContatos()

MAISCONTROLE.pedido.filtrarProduto = function() {
	
	var produto=$("#consultarProduto").val();
	var valorBusca=idPedido;
	MAISCONTROLE.pedido.visualizarPedido(valorBusca,undefined,produto); 
}

