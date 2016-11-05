var MAISCONTROLE = MAISCONTROLE == undefined ? {} : MAISCONTROLE;
MAISCONTROLE.empresaService = MAISCONTROLE.empresaService == undefined ? {} : MAISCONTROLE.empresaService;
MAISCONTROLE.administradorService = MAISCONTROLE.administradorService == undefined ? {} : MAISCONTROLE.administradorService;

MAISCONTROLE.empresaService = {
	url : 'rest/empresaRest',
	cadastrar : function(cfg) {
		
		MAISCONTROLE.ajax.post({
			url : MAISCONTROLE.empresaService.url + "/addEmpresa",
			data : cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},
}

	MAISCONTROLE.administradorService = {
			url : 'rest/usuarioRest',
			cadastrar : function(cfg) {
				MAISCONTROLE.ajax.post({
					url : MAISCONTROLE.administradorService.url + "/addUsuarioMaster",
					data : cfg.data,
					success : cfg.success,
					error : cfg.error
				});
			},
}
