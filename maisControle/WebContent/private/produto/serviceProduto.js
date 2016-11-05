var MAISCONTROLE = MAISCONTROLE == undefined ? {} : MAISCONTROLE;
MAISCONTROLE.produtoService = MAISCONTROLE.produtoService == undefined ? {} : MAISCONTROLE.produtoService;

MAISCONTROLE.produtoService = {
	url : 'rest/produtoRest',
	
	cadastrar : function(cfg) {	
		MAISCONTROLE.ajax.post({
			url : MAISCONTROLE.produtoService.url + "/addProduto",
			data : cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},

	buscarPorNome : function(cfg) {
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.produtoService.url + "/buscarProdutosPorNome?nome=" + cfg.data.valor1 + "&codigo=" + cfg.data.valor2,
			success : cfg.success,
		});
	},
	
	deletarProduto : function(cfg) {
		MAISCONTROLE.ajax.del({
			url : MAISCONTROLE.produtoService.url + "/deletarProduto/" + cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},

	editarProduto : function(cfg) {
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.produtoService.url + "/buscarProdutoPeloId/" + cfg.data,
			success : cfg.success,
		});
	},
	
	exibirEdicao : function(cfg) {
		MAISCONTROLE.ajax.put({
			url : MAISCONTROLE.produtoService.url + "/editarProduto",
			data: cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},
}
