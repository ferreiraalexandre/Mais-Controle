
MAISCONTROLE.produto = new Object();

$(document).ready(
		function() {

			MAISCONTROLE.produto.getProduto = function() {
				return {
					codigo : $("#codigoProduto").val(),
					nome : $("#nomeProduto").val(),
					categoria : $("#categoria").val(),
					fornecedor : $("#fornecedor").val(),
					preco_venda : $("#precoVenda").val(),
					preco_custo : $("#precoCusto").val(),
					quantidade : $("#quantidade").val(),
					estoque_minimo : $("#estoque_minimo").val(),
					estoque_maximo : $("#estoque_maximo").val(),
					id_produto:""

				};
			}

			MAISCONTROLE.produto.cadastrar = function(id_botao) {

				var produto = MAISCONTROLE.produto.getProduto();
				var valido = MAISCONTROLE.validar(produto, "produto");

				if (valido) {

					MAISCONTROLE.produtoService.cadastrar({
						data : produto,
						success : function(msg) {
							// debugger;
							bootbox.alert(msg, function() {
								if (id_botao == 2) {
									MAISCONTROLE.produto.limpar();
								} else {
									$('#myModal').modal('hide');
									MAISCONTROLE.produto.exibirProdutos(
											undefined, "", "");
								}
							});
						},// Fecha success
						error : function(err) {
							bootbox.alert(err.responseText);
						}// Fechar error
					});
					MAISCONTROLE.produto.limpar();
				}
				;
			};// Fecha a function SENAI.contato.cadastrar()
		});

MAISCONTROLE.produto.buscar = function() {
	var valorBusca = $("#consultarProduto").val();
	var valorBuscaCodigo = $("#codigo").val();

	MAISCONTROLE.produto
			.exibirProdutos(undefined, valorBusca, valorBuscaCodigo);
};// Fecha método SENAI.contato.buscar()

MAISCONTROLE.produto.exibirProdutos = function(listaDeProdutos, valorBusca,
		valorBuscaCodigo) {

	// debugger;
	var html = "";

	html += "<thead>" + "<tr>" + "<th>Código</th>" + "<th>Nome</th>"
			+ "<th>Categoria</th>" + "<th>Fornecedor</th>"
			+ "<th>Preço Venda</th>" + "<th>Preço Custo</th>"
			+ "<th>Quantidade</th>" + "<th>Est.Mínimo</th>"
			+ "<th>Est.Máximo</th>" + "<th>Ações</th>" + "</tr>" + "</thead>"
			+ "<tbody>";
	if (listaDeProdutos != undefined && listaDeProdutos.length > 0
			&& listaDeProdutos[0].codigo != undefined) {
		for (var i = 0; i < listaDeProdutos.length; i++) {
			html += "<tr>" + "<td>"
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
					+ "<td>"
					+ listaDeProdutos[i].preco_venda
					+ "</td>"
					+ "<td>"
					+ listaDeProdutos[i].preco_custo
					+ "</td>"
					+ "<td>"
					+ listaDeProdutos[i].quantidade
					+ "</td>"
					+ "<td>"
					+ listaDeProdutos[i].estoque_minimo
					+ "</td>"
					+ "<td>"
					+ listaDeProdutos[i].estoque_maximo
					+ "</td>"
					+ "<td class='actions'>"
					+ "<a class='btn btn-warning btn-xs' onclick='MAISCONTROLE.produto.editarProduto("
					+ listaDeProdutos[i].id_produto
					+ ")'>Editar</a>"
					+ "<a class='btn btn-danger btn-xs' style='margin-left: 10px' onclick='MAISCONTROLE.produto.deletarProduto("
					+ listaDeProdutos[i].id_produto + ")'>Deletar</a>"
					+ "</td>" + "</tr>";
		}
	} else {
		if (listaDeProdutos == undefined
				|| (listaDeProdutos != undefined && listaDeProdutos.length > 0)) {
			if (valorBusca == "") {
				valorBusca = null;
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
			MAISCONTROLE.produtoService.buscarPorNome({
				data : {
					'valor1' : valorBusca,
					'valor2' : valorBuscaCodigo
				},
				success : function(listaDeProdutos) {debugger;
					if(listaDeProdutos[0].tipoUsuario="representante"){
						$("#opcaoRepresentante").css("display", "none") 
				}
					
					console.log(listaDeProdutos)
					MAISCONTROLE.produto.exibirProdutos(listaDeProdutos);
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
};// Fecha a declaração do método exibirContatos()
$('#myModal').on('hidden.bs.modal', function() {
	MAISCONTROLE.produto.limpar();
})

MAISCONTROLE.produto.exibirProdutos(undefined, "", "");

MAISCONTROLE.produto.deletarProduto = function(id_produto) {
	bootbox.dialog({
		message : "Deseja deletar o Produto?",
		buttons : {
			success : {
				label : "Sim",
				className : "btn-success",
				callback : function(result) {
					if (result) {
						MAISCONTROLE.produtoService.deletarProduto({
							data : id_produto,
							success : function(data) {
								bootbox.alert(data);
								
								MAISCONTROLE.produto.buscar();
							},
							error : function(err) {
								alert(err.reponseText);
							}
						});
					}
				}
			},
			danger : {
				label : "Não",
				className : "btn-danger",
				callback : function() {
				}
			},
		}
	});
};

MAISCONTROLE.produto.editarProduto = function(id_produto) {

	$("#cadastrar_outro").hide();

	$("#myModal").modal();

	MAISCONTROLE.produtoService.editarProduto({
		data : id_produto,
		success : function(conta) {
			$("#codigoProduto").val(conta.codigo);
			$("#nomeProduto").val(conta.nome);
			$("#categoria").val(conta.categoria);
			$("#fornecedor").val(conta.fornecedor);
			$("#precoVenda").val(conta.preco_venda);
			$("#precoCusto").val(conta.preco_custo);
			$("#quantidade").val(conta.quantidade);
			$("#idProduto").val(conta.id_produto);
			$("#estoque_minimo").val(conta.estoque_minimo);
			$("#estoque_maximo").val(conta.estoque_maximo);

			$("#salvar")
					.attr(
							"onclick",
							"MAISCONTROLE.produto.exibirEdicao('"
									+ conta.id_produto + "')");
		},
		error : function(err) {
			alert(err.responseText);
		}
	});
};

MAISCONTROLE.produto.exibirEdicao = function(id_produto) {
	var produto = MAISCONTROLE.produto.getProduto();
	produto.id_produto=id_produto;
	var valido = MAISCONTROLE.validar(produto, "produto");
	if (valido) {

	MAISCONTROLE.produtoService.exibirEdicao({
		data : produto,
		success : function(data) {
			console.log(data);
			$('#myModal').modal('hide');
			// MAISCONTROLE.produto.limpar();
			bootbox.alert(data);

			MAISCONTROLE.produto.buscar();// Atualiza a tabela dos contatos.

		},
		error : function(err) {
			alert(err.responseText);
		}
	});
	}
};

MAISCONTROLE.produto.limpar = function() {

	// $(':input').val('');
	$('#form-myform input').each(function() {
		$(this).val('');
	});

};
