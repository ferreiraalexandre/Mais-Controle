MAISCONTROLE.cliente = new Object();
$(document).ready(function() {

	MAISCONTROLE.cliente.getCliente = function(){
		return{
			cnpj : $("#cnpj").val(),
			razaoSocial : $("#razaoSocial").val(),
			nomeFantasia : $("#nomeFantasia").val(),
			email : $("#email").val(),
			telefone : $("#telefone").val(),
			idCliente:""
		};
	}
			MAISCONTROLE.cliente.cadastrar = function(idBotao) {
			var cliente = MAISCONTROLE.cliente.getCliente();
			
			var valido = MAISCONTROLE.validar(cliente,"cliente");

				if (valido) {
										
					MAISCONTROLE.clienteService.cadastrar({
						data : cliente,
						success : function(msg) {
							bootbox.alert(msg, function() {
								if(idBotao==2)
								{
								MAISCONTROLE.cliente.limpar();
							}else{	
								$('#myModal').modal('hide');
								MAISCONTROLE.cliente.exibirClientes(undefined, "");
							}
								MAISCONTROLE.cliente.exibirClientes(undefined, "");
							});
						},// Fecha success
						error : function(err) {
							bootbox.alert(err.responseText);

						}// Fechar error
					});// Fecha estrutura Service
				}// fecha o else
			};// Fecha a function
});
	


MAISCONTROLE.cliente.buscar = function() {
	var valorBusca = $("#consultarCliente").val();
	MAISCONTROLE.cliente.exibirClientes(undefined, valorBusca);
};// Fecha método SENAI.contato.buscar()


MAISCONTROLE.cliente.exibirClientes = function(listaDeClientes, valorBusca) {

	var html = "";			
	html += "<thead>" +
				"<tr>" +
					"<th>Nome Fantasia</th>" +
					"<th>Razão Social</th>" +
					"<th>CNPJ</th>" +
					"<th>Email</th>" +
					"<th>Telefone</th>" +
					"<th>Ações</th>" +
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
					+ "</td>"
					+ "<td class='actions'>"
					+ "<a class='btn btn-warning btn-xs' onclick='MAISCONTROLE.cliente.editarCliente("+ listaDeClientes[i].idCliente+ ")'>Editar</a>"
					+ "<a class='btn btn-danger btn-xs' style='margin-left: 10px' onclick='MAISCONTROLE.cliente.deletarCliente("+ listaDeClientes[i].idCliente+ ")'>Deletar</a>" + "</td>"
					+ "</tr>";
		}
	} else {
		if (listaDeClientes == undefined|| (listaDeClientes != undefined && listaDeClientes.length > 0)) {
			if (valorBusca == "") {
				valorBusca = null;
			}

			/*
			 * Destaque para a url que referencia o recurso Rest que fará a
			 * busca dos contatos, baseados no valor contido na variável
			 * valorBusca que, por sua vez, também encontra-se referenciada
			 * nesta.
			 */
			
			MAISCONTROLE.clienteService.buscarPorNome({
				data:valorBusca,
				success : function(listaDeClientes) {
					MAISCONTROLE.cliente.exibirClientes(listaDeClientes);
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
$('#myModal').on('hidden.bs.modal', function () {
	MAISCONTROLE.cliente.limpar();
})

MAISCONTROLE.cliente.exibirClientes(undefined, "");


MAISCONTROLE.cliente.deletarCliente = function(idCliente){
	bootbox.dialog({
		  message: "Deseja deletar o Cliente?",
		  buttons: {
		    success: {
		      label: "Sim",
		      className: "btn-success",
		      callback: function(result) {
		    	  if(result){
						MAISCONTROLE.clienteService.deletarCliente({
							data:idCliente,
								success: function (data){
									bootbox.alert(data);
									MAISCONTROLE.cliente.buscar();												
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
		  
MAISCONTROLE.cliente.editarCliente = function(idCliente){

	$("#cadastrar_outro").hide();
	
	$("#myModal").modal();
	
	MAISCONTROLE.clienteService.editarCliente({
			data: idCliente,
			success: function (conta){
				conta.idCliente=idCliente
				$("#cnpj").val(conta.cnpj);
				$("#razaoSocial").val(conta.razaoSocial);
				$("#nomeFantasia").val(conta.nomeFantasia);
				$("#email").val(conta.email);
				$("#telefone").val(conta.telefone);
								
				$("#salvar").attr("onclick", "MAISCONTROLE.cliente.exibirEdicao('"+conta.idCliente+"')");
			
			},
			error: function (err){
				alert(err.responseText);
			}
	});
};


MAISCONTROLE.cliente.exibirEdicao = function(idCliente){
	
	var cliente = MAISCONTROLE.cliente.getCliente();
	
		cliente.idCliente=idCliente;
		var valido = MAISCONTROLE.validar(cliente,"cliente");

	if(valido){

	MAISCONTROLE.clienteService.exibirEdicao({
		data: cliente,
		success: function (data){
			$('#myModal').modal('hide');
			//MAISCONTROLE.produto.limpar();
			bootbox.alert(data);
			MAISCONTROLE.cliente.buscar();//Atualiza a tabela dos contatos.
		},
		error: function(err){
			alert(err.responseText);
		}
	});
};
}

MAISCONTROLE.cliente.limpar = function() {
	//$(':input').val('');
	$('#form-myform input').each(function(){
		$(this).val('');});
	
	
};

	