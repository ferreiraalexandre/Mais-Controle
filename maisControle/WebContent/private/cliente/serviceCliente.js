var MAISCONTROLE = MAISCONTROLE == undefined ? {} : MAISCONTROLE;
MAISCONTROLE.clienteService = MAISCONTROLE.clienteService == undefined ? {} : MAISCONTROLE.clienteService;

MAISCONTROLE.clienteService = {
	url : 'rest/clienteRest',
	
	cadastrar : function(cfg) {
		MAISCONTROLE.ajax.post({
			url : MAISCONTROLE.clienteService.url + "/addCliente",
			data : cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},

	buscarPorNome : function(cfg) {
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.clienteService.url + "/buscarClientesPorNome/" + cfg.data,
			success : cfg.success,
			
		});
	},
	
	deletarCliente : function(cfg) {
		MAISCONTROLE.ajax.del({
			url : MAISCONTROLE.clienteService.url + "/deletarCliente/" + cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},

	editarCliente : function(cfg) {
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.clienteService.url + "/buscarClientePeloCodigo/" + cfg.data,
			success : cfg.success,
		});
	},
	
	exibirEdicao : function(cfg) {
		MAISCONTROLE.ajax.put({
			url : MAISCONTROLE.clienteService.url + "/editarCliente",
			data: cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},
}
