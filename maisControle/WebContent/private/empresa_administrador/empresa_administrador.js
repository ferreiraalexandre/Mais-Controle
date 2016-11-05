MAISCONTROLE.empresa = new Object();
MAISCONTROLE.usuario = new Object();

$(document).ready(function() {

	MAISCONTROLE.empresa.getEmpresa = function(){
		return {
			nome_fantasia : $("#nomeFantasia").val(),
			razao_social : $("#razaoSocial").val(),
			cnpj : $("#cnpj").val(),
			email :$("#email").val(),
			telefone :$("#telefone").val(),
			pais :$("#pais").val(),
			estado :$("#estado").val(),
			cidade : $("#cidade").val(),
			endereco :$("#endereco").val(),
			bairro : $("#bairro").val(),
			numero : $("#numero").val(),
			complemento :$("#complemento").val()
			
		};
			
		}
	MAISCONTROLE.usuario.getUsuario= function(){
		
		return {
			nome : $("#nomeAdm").val(),
			email :$("#emailAdm").val(),
			telefone :$("#telefoneAdm").val(),
			pais : $("#paisAdm").val(),
			estado :$("#estadoAdm").val(),
			cidade : $("#cidadeAdm").val(),
			endereco :$("#enderecoAdm").val(),
			bairro : $("#bairroAdm").val(),
			numero : $("#numeroAdm").val(),
			senha : btoa($("#senhaAdm").val()),
			confirmaSenha :btoa($("#confirmaSenhaAdm").val()),									
			complemento : $("#complementoAdm").val(),
			tipo:"master"			
		};
		}
	
					MAISCONTROLE.empresa.cadastrar = function() {
						var empresa = MAISCONTROLE.empresa.getEmpresa();
			
                        var valido = MAISCONTROLE.validar(empresa,"empresa");
						
						if (valido){
									bootbox.alert("Empresa Cadastrado Com Sucesso",
									function() {
												if ($("#cadastro_emp").css("display","block")) 
												{   $("#cadastro_emp").css("display","none");
												    $("#cadastro_adm").css("display","block"); }
												});
								}

					};// fecha o cadastrar empresa

					
					MAISCONTROLE.usuario.cadastrar = function() {
						var usuario = MAISCONTROLE.usuario.getUsuario();
						var valido = MAISCONTROLE.validar(usuario,"usuario");
						
						// Validações formulário Administrador
						if (valido) {
							bootbox.alert("Administrador Cadastrado Com Sucesso");
		
							var empresa = MAISCONTROLE.empresa.getEmpresa();
							MAISCONTROLE.empresaService.cadastrar({
									data : empresa,
									success : function(id) {
									usuario.empresa = {id_empresa : id};
							
				
									
									MAISCONTROLE.administradorService.cadastrar({
										data : usuario,
										success : function(suc) {
											bootbox.alert(suc, function() {
												$("body").load("private/produto/produto.html");
									});
										},// Fecha success
										error : function(err) {
											bootbox.alert(err.responseText);

										}// Fechar error
									});// Fecha estrutura Service
								},// Fecha success
								error : function(err) {
									bootbox.alert(err.responseText);
								}// Fechar error
							});// Fecha estrutura Service
						}// fecha o else
					};// Fecha a function
					});
