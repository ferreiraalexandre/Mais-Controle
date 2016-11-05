var MAISCONTROLE = MAISCONTROLE == undefined ? {} : MAISCONTROLE;
MAISCONTROLE.representanteService = MAISCONTROLE.representanteService == undefined ? {} : MAISCONTROLE.representanteService;

MAISCONTROLE.representanteService = {
	url : 'rest/usuarioRest',
	
	cadastrar : function(cfg) {
		MAISCONTROLE.ajax.post({
			url : MAISCONTROLE.representanteService.url + "/addUsuario",
			data : cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},

	buscarPorNome : function(cfg) {
		debugger;
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.representanteService.url + "/buscarUsuarioPorNome?nome=" + cfg.data.valor1 + "&tipo=" + cfg.data.valor2,
			success : cfg.success,
			
		});
	},
	
	deletarUsuario : function(cfg) {
		MAISCONTROLE.ajax.del({
			url : MAISCONTROLE.representanteService.url + "/deletarUsuario/" + cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},

	editarUsuario : function(cfg) {
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.representanteService.url + "/buscarUsuarioPeloCodigo/" + cfg.data,
			success : cfg.success,
		});
	},
	
	exibirEdicao : function(cfg) {
		MAISCONTROLE.ajax.put({
			url : MAISCONTROLE.representanteService.url + "/editarUsuario",
			data: cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},
}
