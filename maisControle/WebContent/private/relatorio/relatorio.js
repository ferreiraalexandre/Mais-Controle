//MAISCONTROLE= new Object();

MAISCONTROLE.relatorio = new Object();

$(document).ready(function() {

MAISCONTROLE.relatorio.buscar = function(tipoRelatorio){
	
	MAISCONTROLE.relatorioService.buscar({
			data: tipoRelatorio,
			success: function (dados){
						console.log(dados)
						
						if(tipoRelatorio == 1){
							MAISCONTROLE.relatorio.produto(dados);
						}
						if(tipoRelatorio == 2){
							MAISCONTROLE.relatorio.cliente(dados);
							}
						if(tipoRelatorio == 3){
							MAISCONTROLE.relatorio.representante(dados);
							}
						
			},
									
			error: function (err){
				alert(err.responseText);
			}
	});
};

MAISCONTROLE.relatorio.produto = function(dados){
	
	var ctx = document.getElementById("myChart");
	
	var data = {
		    labels: [],
		    datasets: [
		        {
		            label: "Produtos Mais Vendidos",
		            backgroundColor: [
		                              'rgba(255, 99, 132, 0.2)',
		                              'rgba(54, 162, 235, 0.2)',
		                              'rgba(255, 206, 86, 0.2)',
		                              'rgba(75, 192, 192, 0.2)',
		                              'rgba(153, 102, 255, 0.2)',
		                              'rgba(255, 159, 64, 0.2)'
		                          ],
		                          borderColor: [
		                              'rgba(255,99,132,1)',
		                              'rgba(54, 162, 235, 1)',
		                              'rgba(255, 206, 86, 1)',
		                              'rgba(75, 192, 192, 1)',
		                              'rgba(153, 102, 255, 1)',
		                              'rgba(255, 159, 64, 1)'
		                          ],
		                          borderWidth: 1,
		                          data: [65, 59, 80, 81, 56, 55, 40],
		            data: []
		        }
		    ]
		};
	
	for (var i = 0; i < dados.length; i++) {
		data.labels.push(dados[i].nome);
		data.datasets[0].data.push(dados[i].total);
	}
			
	var myBarChart = new Chart(ctx, {
	    type: 'bar',
	    data: data,
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    min: 0
	                 
	                }
	            }]
	        }
	    }
	    
	    
	});
}



MAISCONTROLE.relatorio.cliente = function(dados){
	
	var ctx = document.getElementById("myChart1");
	
	var data = {
		    labels: [],
		    datasets: [
		        {
		            label: "Cliente Que Mais Comprou ",
		            backgroundColor: [
		                              'rgba(255, 99, 132, 0.2)',
		                              'rgba(54, 162, 235, 0.2)',
		                              'rgba(255, 206, 86, 0.2)',
		                              'rgba(75, 192, 192, 0.2)',
		                              'rgba(153, 102, 255, 0.2)',
		                              'rgba(255, 159, 64, 0.2)'
		                          ],
		                          borderColor: [
		                              'rgba(255,99,132,1)',
		                              'rgba(54, 162, 235, 1)',
		                              'rgba(255, 206, 86, 1)',
		                              'rgba(75, 192, 192, 1)',
		                              'rgba(153, 102, 255, 1)',
		                              'rgba(255, 159, 64, 1)'
		                          ],
		                          borderWidth: 1,
		                          data: [65, 59, 80, 81, 56, 55, 40],	            
		            data: []
		        }
		    ]
		};
	
	for (var i = 0; i < dados.length; i++) {
		data.labels.push(dados[i].nome);
		data.datasets[0].data.push(dados[i].total);
	}
			
	var myBarChart = new Chart(ctx, {
	    type: 'bar',
	    data: data,
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    min: 0
	                 
	                }
	            }]
	        }
	    }
	    
	    
	});
}


MAISCONTROLE.relatorio.representante = function(dados){
	
	var ctx = document.getElementById("myChart2");
	
	var data = {
		    labels: [],
		    datasets: [
		        {
		            label: "Usuario Que Mais Vendeu ",
		            backgroundColor: [
		                              'rgba(255, 99, 132, 0.2)',
		                              'rgba(54, 162, 235, 0.2)',
		                              'rgba(255, 206, 86, 0.2)',
		                              'rgba(75, 192, 192, 0.2)',
		                              'rgba(153, 102, 255, 0.2)',
		                              'rgba(255, 159, 64, 0.2)'
		                          ],
		                          borderColor: [
		                              'rgba(255,99,132,1)',
		                              'rgba(54, 162, 235, 1)',
		                              'rgba(255, 206, 86, 1)',
		                              'rgba(75, 192, 192, 1)',
		                              'rgba(153, 102, 255, 1)',
		                              'rgba(255, 159, 64, 1)'
		                          ],
		                          borderWidth: 1,
		                          data: [65, 59, 80, 81, 56, 55, 40],	            
		            data: []
		        }
		    ]
		};
	
	for (var i = 0; i < dados.length; i++) {
		data.labels.push(dados[i].nome);
		data.datasets[0].data.push(dados[i].total);
	}
			
	var myBarChart = new Chart(ctx, {
	    type: 'bar',
	    data: data,
	    options: {
	        scales: {
	            yAxes: [{
	                ticks: {
	                    min: 0
	                 
	                }
	            }]
	        }
	    }
	    
	    
	});
}


});
MAISCONTROLE.relatorio.buscar(1) ;
MAISCONTROLE.relatorio.buscar(2) ;
MAISCONTROLE.relatorio.buscar(3) ;
	







