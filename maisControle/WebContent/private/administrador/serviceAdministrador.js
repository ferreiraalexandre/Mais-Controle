var MAISCONTROLE = MAISCONTROLE == undefined ? {} : MAISCONTROLE;
MAISCONTROLE.administradorService = MAISCONTROLE.administradorService == undefined ? {} : MAISCONTROLE.administradorService;

MAISCONTROLE.administradorService = {
	url : 'rest/usuarioRest',
	
	cadastrar : function(cfg) {
		MAISCONTROLE.ajax.post({
			url : MAISCONTROLE.administradorService.url + "/addUsuario",
			data : cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},

	buscarPorNome : function(cfg) {
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.administradorService.url + "/buscarUsuarioPorNome?nome=" + cfg.data.valor1 + "&tipo=" + cfg.data.valor2,
			success : cfg.success,
			
		});
	},
	
	deletarUsuario : function(cfg) {
		MAISCONTROLE.ajax.del({
			url : MAISCONTROLE.administradorService.url + "/deletarUsuario/" + cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},

	editarUsuario : function(cfg) {
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.administradorService.url + "/buscarUsuarioPeloCodigo/" + cfg.data,
			success : cfg.success,
		});
	},
	
	exibirEdicao : function(cfg) {
		MAISCONTROLE.ajax.put({
			url : MAISCONTROLE.administradorService.url + "/editarUsuario",
			data: cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},
}
