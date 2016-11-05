MAISCONTROLE.representante = new Object();
$(document).ready(function() {

	MAISCONTROLE.representante.getUsuario = function(){
		return {
			nome : $("#nomeRepr").val(),
			email : $("#emailRepr").val(),
			telefone : $("#telefoneRepr").val(),
			pais : $("#paisRepr").val(),
			estado : $("#estadoRepr").val(),
			cidade : $("#cidadeRepr").val(),
			endereco : $("#enderecoRepr").val(),
			bairro : $("#bairroRepr").val(),
			numero : $("#numeroRepr").val(),
			senha : btoa($("#senhaRepr").val()),
			confirmaSenha : btoa($("#confirmaSenhaRepr").val()),
			complemento : $("#complementoRepr").val(),
			tipo:"representante",
			id:""
			
		};
	}
			
			MAISCONTROLE.representante.cadastrar = function(idBotao) {
				
				var usuario = MAISCONTROLE.representante.getUsuario();
				var valido = MAISCONTROLE.validar(usuario,"usuario");
				
	
				if (valido) {
					MAISCONTROLE.representanteService.cadastrar({
						data : usuario,
						success : function(msg) {
							bootbox.alert(msg, function() {
								if(idBotao==2)
								{
								MAISCONTROLE.representante.limpar();
							}else{	
								$('#myModal').modal('hide');
								MAISCONTROLE.representante.exibirRepresentantes(undefined, "");
							}
								MAISCONTROLE.representante.exibirRepresentantes(undefined, "");
							});
						},// Fecha success
						error : function(err) {
							bootbox.alert(err.responseText);

						}// Fechar error
					});// Fecha estrutura Service
				}// fecha o else
			};// Fecha a function
});
	
MAISCONTROLE.representante.buscar = function() {
	var valorBusca = $("#consultarRepresentante").val();
	MAISCONTROLE.representante.exibirRepresentantes(undefined, valorBusca);
};// Fecha método SENAI.contato.buscar()


MAISCONTROLE.representante.exibirRepresentantes = function(listaDeRepresentantes, valorBusca) {

	var html = "";			
	html += "<thead>" +
				"<tr>" +
					"<th>Nome</th>" +
					"<th>E-mail</th>" +
					"<th>Telefone</th>" +
					"<th>Estado</th>" +
					"<th>Cidade</th>" +
					"<th>Endereço</th>" +
					"<th>Número</th>" +
					"<th>Ações</th>" +
				"</tr>" +
			"</thead>" +
			"<tbody>";
	if (listaDeRepresentantes != undefined && listaDeRepresentantes.length > 0 && listaDeRepresentantes[0].id!= undefined) {
		for (var i = 0; i < listaDeRepresentantes.length; i++) {
			html += "<tr>"
					+
					"<td>"
					+ listaDeRepresentantes[i].nome
					+ "</td>"
					+ "<td>"
					+ listaDeRepresentantes[i].email
					+ "</td>"
					+ "<td>"
					+ listaDeRepresentantes[i].telefone
					+ "</td>"
					+ "<td>"
					+ listaDeRepresentantes[i].estado
					+ "</td>"
					+ "<td>"
					+ listaDeRepresentantes[i].cidade
					+ "</td>"
					+ "<td>"
					+ listaDeRepresentantes[i].endereco
					+ "</td>"
					+ "<td>"
					+ listaDeRepresentantes[i].numero
					+ "</td>"
					+ "<td class='actions'>"
					+ "<a class='btn btn-warning btn-xs' onclick='MAISCONTROLE.representante.editarRepresentante("+ listaDeRepresentantes[i].id+ ")'>Editar</a>"
					+ "<a class='btn btn-danger btn-xs' style='margin-left: 10px' onclick='MAISCONTROLE.representante.deletarRepresentante("+ listaDeRepresentantes[i].id+ ")'>Deletar</a>" + "</td>"
					+ "</tr>";
		}
	} else {
		if (listaDeRepresentantes == undefined|| (listaDeRepresentantes != undefined && listaDeRepresentantes.length > 0)) {
			if (valorBusca == "") {
				valorBusca = null;
			}

			/*
			 * Destaque para a url que referencia o recurso Rest que fará a
			 * busca dos contatos, baseados no valor contido na variável
			 * valorBusca que, por sua vez, também encontra-se referenciada
			 * nesta.
			 */
			
			MAISCONTROLE.representanteService.buscarPorNome({
				data:{'valor1':valorBusca, 'valor2':"representante"},
				success : function(listaDeRepresentantes) {
					console.log(listaDeRepresentantes);
					MAISCONTROLE.representante.exibirRepresentantes(listaDeRepresentantes);
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
	$("#resultadoRepresentantes").html(html);
};// Fecha a declaração do método exibirContatos()
$('#myModal').on('hidden.bs.modal', function () {
	MAISCONTROLE.representante.limpar();
})

MAISCONTROLE.representante.exibirRepresentantes(undefined, "");


MAISCONTROLE.representante.deletarRepresentante= function(id){
	bootbox.dialog({
		  message: "Deseja deletar o Representante?",
		  buttons: {
		    success: {
		      label: "Sim",
		      className: "btn-success",
		      callback: function(result) {
		    	  if(result){
						MAISCONTROLE.representanteService.deletarUsuario({
							data:id,
								success: function (data){
									MAISCONTROLE.representante.buscar();												
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
		  
MAISCONTROLE.representante.editarRepresentante = function(id){

	$("#cadastrar_outro").hide();
	
	$("#myModal").modal();
	
	MAISCONTROLE.representanteService.editarUsuario({
			data: id,
			success: function (conta){
				conta.id=id
				$("#nomeRepr").val(conta.nome);
				$("#emailRepr").val(conta.email);
				$("#telefoneRepr").val(conta.telefone);
				$("#paisRepr").val(conta.pais);
				$("#estadoRepr").val(conta.estado);
				$("#cidadeRepr").val(conta.cidade);
				$("#enderecoRepr").val(conta.endereco);
				$("#bairroRepr").val(conta.bairro);
				$("#numeroRepr").val(conta.numero);
				$("#senhaRepr").val(conta.senha);
				$("#confirmaSenhaRepr").val(conta.confirmaSenha);
				$("#complementoRepr").val(conta.complemento)
				
				$("#salvar").attr("onclick", "MAISCONTROLE.representante.exibirEdicao('"+conta.id+"')");
			
			},
			error: function (err){
				alert(err.responseText);
			}
	});
};


MAISCONTROLE.representante.exibirEdicao = function(id){

	var usuario = MAISCONTROLE.representante.getUsuario();
	usuario.id=id;
	var valido = MAISCONTROLE.validar(usuario,"usuario");
	

	if(valido){

	MAISCONTROLE.representanteService.exibirEdicao({
		data: usuario,
		success: function (data){
			$('#myModal').modal('hide');
			//MAISCONTROLE.produto.limpar();
			bootbox.alert(data);
			MAISCONTROLE.representante.buscar();//Atualiza a tabela dos contatos.
		},
		error: function(err){
			alert(err.responseText);
		}
	});
};
}

MAISCONTROLE.representante.limpar = function() {
	
	//$(':input').val('');
	$('#form-myform input').each(function(){
		$(this).val('');});
	
	
};

	