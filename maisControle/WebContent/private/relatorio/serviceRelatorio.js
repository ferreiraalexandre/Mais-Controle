var MAISCONTROLE = MAISCONTROLE == undefined ? {} : MAISCONTROLE;
MAISCONTROLE.relatorioService = MAISCONTROLE.relatorioService == undefined ? {} : MAISCONTROLE.relatorioService;

MAISCONTROLE.relatorioService = {
	url : 'rest/relatorioRest',
	
	buscar : function(cfg) {	
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.relatorioService.url + "/buscar/" + cfg.data,
			success : cfg.success,
		});
	},
}
