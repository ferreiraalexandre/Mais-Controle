var MAISCONTROLE = MAISCONTROLE == undefined ? {} : MAISCONTROLE;
MAISCONTROLE.pedidoService = MAISCONTROLE.pedidoService == undefined ? {} : MAISCONTROLE.pedidoService;



MAISCONTROLE.pedidoService = {
	url : 'rest',
	
	
	cadastrar : function(cfg) {
		MAISCONTROLE.ajax.post({
			url : MAISCONTROLE.pedidoService.url + "/pedidoRest/addPedido",
			data : cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},
	
	buscarPorNomeProduto : function(cfg) {
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.pedidoService.url + "/produtoRest/buscarProdutosPorNome?nome=" + cfg.data.valor1 + "&codigo=" + cfg.data.valor2,
			success : cfg.success,
		});
	},
	
	buscarPorNomeCliente : function(cfg) {
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.pedidoService.url + "/clienteRest/buscarClientesPorNome/" + cfg.data,
			success : cfg.success,
		});
	},

	buscarPedido : function(cfg) {
		MAISCONTROLE.ajax.get({
			url : MAISCONTROLE.pedidoService.url + "/pedidoRest/buscarPedido?id=" + cfg.data.valor1 + "&cliente=" + cfg.data.valor2,
			success : cfg.success,
		});
	},
	
	deletarPedido : function(cfg) {
		MAISCONTROLE.ajax.del({
			url : MAISCONTROLE.pedidoService.url + "/pedidoRest/deletarPedido/" + cfg.data,
			success : cfg.success,
			error : cfg.error
		});
	},
	
	visualizarPedido : function(cfg) {
		MAISCONTROLE.ajax.get({		
			url : MAISCONTROLE.pedidoService.url + "/pedidoRest/visualizarPedido?idPedido=" + cfg.data.valor1 + "&produto=" + cfg.data.valor2,
			success : cfg.success,
		});
	},
	
}
