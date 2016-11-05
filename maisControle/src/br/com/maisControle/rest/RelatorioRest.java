package br.com.maisControle.rest;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.codehaus.jackson.map.ObjectMapper;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Cliente;
import br.com.maisControle.objetos.Relatorio;
import br.com.maisControle.service.ClienteService;
import br.com.maisControle.service.RelatorioService;


@Path("relatorioRest")
public class RelatorioRest extends UtilRest {
		
	public RelatorioRest() {
	}
		
	@GET
	@Path("/buscar/{tipoRelatorio}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscar(@PathParam("tipoRelatorio") String tipoRelatorio) {
		try {
			List<Relatorio> relatorios = new ArrayList<Relatorio>();
			RelatorioService service= new RelatorioService(); 
			int id= getEmpresa().getId_empresa();
			relatorios =service.buscar(tipoRelatorio,id);
			return this.buildResponse(relatorios);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	

}
