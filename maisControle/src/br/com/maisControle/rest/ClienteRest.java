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
import br.com.maisControle.service.ClienteService;


@Path("clienteRest")
public class ClienteRest extends UtilRest {
		
	public ClienteRest() {
	}
	
	
	@POST
	@Path("/addCliente")
	@Consumes("application/*")
	public Response addCliente(String clienteParam) throws ProdutoException {
		
		try {
			Cliente cliente = new ObjectMapper().readValue(clienteParam,Cliente.class);
			ClienteService service= new ClienteService(); 
			cliente.setEmpresa(getEmpresa());
			String msg =service.addCliente(cliente);
            return this.buildResponse(msg);
		} catch (Exception e) {
			e.printStackTrace();	
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@GET
	@Path("/buscarClientesPorNome/{nome}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarclientesPorNome(@PathParam("nome") String nome) {
		try {
			List<Cliente> clientes = new ArrayList<Cliente>();
			ClienteService service= new ClienteService(); 
			int id= getEmpresa().getId_empresa();
			clientes =service.buscarclientesPorNome(nome,id);
			return this.buildResponse(clientes);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@DELETE
	@Path("/deletarCliente/{idCliente}")
	@Consumes("application/*")
	public Response deletarCliente(@PathParam("idCliente") int idCliente) {
		try {
			ClienteService service= new ClienteService(); 
			int idEmpresa= getEmpresa().getId_empresa();
			 boolean returno =  service.deletarCliente(idCliente,idEmpresa);	
			
			  if(returno==true){
			return this.buildResponse("Cliente deletado com sucesso.");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
		 return this.buildResponse("Cliente não pode ser deletado.<br>A pedido ligado a este cliente");
	}
	@GET
	@Path("/buscarClientePeloCodigo/{idCliente}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarClientePeloCodigo(@PathParam("idCliente") int idCliente) {
		try {
			Cliente cliente= new Cliente();
			ClienteService service= new ClienteService(); 
			cliente =service.buscarClientePeloCodigo(idCliente);			
			return this.buildResponse(cliente);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());

		}
	}

	
	@PUT
	@Path("/editarCliente")
	@Consumes("application/*")
	public Response editarCliente(String clienteParam) {
		try {
			Cliente cliente= new ObjectMapper().readValue(clienteParam,Cliente.class);
			ClienteService service= new ClienteService();
				service.editarCliente(cliente);
				return this.buildResponse("Cliente editado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

}
