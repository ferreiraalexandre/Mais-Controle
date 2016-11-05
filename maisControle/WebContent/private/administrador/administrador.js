MAISCONTROLE.administrador = new Object();
$(document).ready(function() {

	MAISCONTROLE.administrador.getUsuario = function(){
		return {
			nome : $("#nomeAdm").val(),
			email : $("#emailAdm").val(),
			telefone : $("#telefoneAdm").val(),
			pais : $("#paisAdm").val(),
			estado : $("#estadoAdm").val(),
			cidade : $("#cidadeAdm").val(),
			endereco : $("#enderecoAdm").val(),
			bairro : $("#bairroAdm").val(),
			numero : $("#numeroAdm").val(),
			senha : btoa($("#senhaAdm").val()),
			confirmaSenha : btoa($("#confirmaSenhaAdm").val()),
			complemento : $("#complementoAdm").val(),
			tipo : "administrador",
			id:""	
		};
		
	}
	
			MAISCONTROLE.administrador.cadastrar = function(id_botao) {
				var usuario = MAISCONTROLE.administrador.getUsuario();
				
				var valido = MAISCONTROLE.validar(usuario,"usuario");

				
				if (valido) {
				
					MAISCONTROLE.administradorService.cadastrar({
						data : usuario,
						success : function(msg) {
							bootbox.alert(msg, function() {
								if(id_botao==2)
								{
								MAISCONTROLE.administrador.limpar();
							}else{	
								$('#myModal').modal('hide');
								MAISCONTROLE.administrador.exibirAdministradores(undefined, "");
							}
								MAISCONTROLE.administrador.exibirAdministradores(undefined, "");
							});
						},// Fecha success
						error : function(err) {
							bootbox.alert(err.responseText);

						}// Fechar error
					});// Fecha estrutura Service
				}// fecha o else
			};// Fecha a function
});
			
			MAISCONTROLE.administrador.buscar = function() {
				var valorBusca = $("#consultarAdministrador").val();
				MAISCONTROLE.administrador.exibirAdministradores(undefined, valorBusca);
			};// Fecha método SENAI.contato.buscar()


			MAISCONTROLE.administrador.exibirAdministradores = function(listaDeAdministradores, valorBusca) {
	
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
								"<th>Tipo De Usuário</th>" +
								"<th>Ações</th>" +
							"</tr>" +
						"</thead>" +
						"<tbody>";
				if (listaDeAdministradores != undefined && listaDeAdministradores.length > 0 && listaDeAdministradores[0].id != undefined) {
					for (var i = 0; i < listaDeAdministradores.length; i++) {
						html += "<tr>"
								+
								"<td>"
								+ listaDeAdministradores[i].nome
								+ "</td>"
								+ "<td>"
								+ listaDeAdministradores[i].email
								+ "</td>"
								+ "<td>"
								+ listaDeAdministradores[i].telefone
								+ "</td>"
								+ "<td>"
								+ listaDeAdministradores[i].estado
								+ "</td>"
								+ "<td>"
								+ listaDeAdministradores[i].cidade
								+ "</td>"
								+ "<td>"
								+ listaDeAdministradores[i].endereco
								+ "</td>"
								+ "<td>"
								+ listaDeAdministradores[i].numero
								+ "</td>"
								+ "<td>"
								+ listaDeAdministradores[i].tipo
								+ "</td>"
								+ "<td class='actions'>"
								+ "<a class='btn btn-warning btn-xs' onclick='MAISCONTROLE.administrador.editarAdministrador("+ listaDeAdministradores[i].id+ ")'>Editar</a>"
								+ "<a class='btn btn-danger btn-xs' style='margin-left: 10px' onclick='MAISCONTROLE.administrador.deletarAdministrador("+ listaDeAdministradores[i].id+ ")'>Deletar</a>" + "</td>"
								+ "</tr>";
					}
				} else {
					if (listaDeAdministradores == undefined|| (listaDeAdministradores != undefined && listaDeAdministradores.length > 0)) {
						if (valorBusca == "") {
							valorBusca = null;
						}

						/*
						 * Destaque para a url que referencia o recurso Rest que fará a
						 * busca dos contatos, baseados no valor contido na variável
						 * valorBusca que, por sua vez, também encontra-se referenciada
						 * nesta.
						 */
						
						MAISCONTROLE.administradorService.buscarPorNome({
							data:{'valor1':valorBusca, 'valor2':"administrador"},
							success : function(listaDeAdministradores) {
								MAISCONTROLE.administrador.exibirAdministradores(listaDeAdministradores);
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
				$("#resultadoAdministradores").html(html);
			};// Fecha a declaração do método exibirContatos()
			$('#myModal').on('hidden.bs.modal', function () {
				MAISCONTROLE.administrador.limpar();
			})

			MAISCONTROLE.administrador.exibirAdministradores(undefined, "");


			MAISCONTROLE.administrador.deletarAdministrador = function(id){
				bootbox.dialog({
					  message: "Deseja deletar o Administrador?",
					  buttons: {
					    success: {
					      label: "Sim",
					      className: "btn-success",
					      callback: function(result) {
					    	  if(result){
									MAISCONTROLE.administradorService.deletarUsuario({
										data:id,
											success: function (data){
												MAISCONTROLE.administrador.buscar();												
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
					  
			MAISCONTROLE.administrador.editarAdministrador = function(id){

				$("#cadastrar_outro").hide();
				
				$("#myModal").modal();
				
				MAISCONTROLE.administradorService.editarUsuario({
						data: id,
						success: function (conta){
							conta.id=id;
							$("#nomeAdm").val(conta.nome);
							$("#emailAdm").val(conta.email);
							$("#telefoneAdm").val(conta.telefone);
							$("#paisAdm").val(conta.pais);
							$("#estadoAdm").val(conta.estado);
							$("#cidadeAdm").val(conta.cidade);
							$("#enderecoAdm").val(conta.endereco);
							$("#bairroAdm").val(conta.bairro);
							$("#numeroAdm").val(conta.numero);
							$("#senhaAdm").val(conta.senha);
							$("#confirmaSenhaAdm").val(conta.confirmaSenha);
							$("#complementoAdm").val(conta.complemento)
							
							$("#salvar").attr("onclick", "MAISCONTROLE.administrador.exibirEdicao('"+conta.id+"')");
						
						},
						error: function (err){
							alert(err.responseText);
						}
				});
			};


			MAISCONTROLE.administrador.exibirEdicao = function(id){

				var usuario = MAISCONTROLE.administrador.getUsuario();
				usuario.id=id;
				var valido = MAISCONTROLE.validar(usuario,"usuario");

				if(valido){

				MAISCONTROLE.administradorService.exibirEdicao({
					data: usuario,
					success: function (data){
						$('#myModal').modal('hide');
						//MAISCONTROLE.produto.limpar();
						bootbox.alert(data);
						MAISCONTROLE.administrador.buscar();//Atualiza a tabela dos contatos.
					},
					error: function(err){
						alert(err.responseText);
					}
				});
			};
			}
			
			MAISCONTROLE.administrador.limpar = function() {
				//$(':input').val('');
				$('#form-myform input').each(function(){
					$(this).val('');});
				
				
			};

			
			
		
