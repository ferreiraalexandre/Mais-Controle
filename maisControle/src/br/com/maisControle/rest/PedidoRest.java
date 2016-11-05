package br.com.maisControle.rest;

import java.io.IOException;
import java.io.StringWriter;
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
import javax.ws.rs.QueryParam;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import com.sun.org.apache.xalan.internal.xsltc.compiler.Pattern;

import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Cliente;
import br.com.maisControle.objetos.Pedido;
import br.com.maisControle.objetos.Produto;
import br.com.maisControle.objetos.Usuario;
import br.com.maisControle.service.ClienteService;
import br.com.maisControle.service.PedidoService;
import br.com.maisControle.service.ProdutoService;
import br.com.maisControle.service.UsuarioService;


@Path("pedidoRest")
public class PedidoRest extends UtilRest {

	public PedidoRest() {
	}

	@POST
	@Path("/addPedido")
	@Consumes("application/*")
public Response addUsuario(String pedidoParam) throws ProdutoException, IOException {
		System.out.println(pedidoParam);
		
		try {
			List<Produto> pedidos = new ArrayList<Produto>();
			Pedido pedido = new ObjectMapper().readValue(pedidoParam,Pedido.class);
			PedidoService service= new PedidoService(); 
			pedido.setUsuario(getUsuario());
			pedidos=service.addPedido(pedido);
			
            return this.buildResponse(pedidos);
		} catch (Exception e) {
			e.printStackTrace();	
			return this.buildErrorResponse(e.getMessage());
		}
		
	}
	
	
	@GET
	@Path("/buscarPedido")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarProdutosPorNome(@QueryParam("id") String id,@QueryParam("cliente") String cliente){

	try {
			List<Pedido> pedidos = new ArrayList<Pedido>();
			PedidoService service= new PedidoService();
			int idEmpresa= getEmpresa().getId_empresa();
			pedidos =service.buscarPedido(id,cliente,idEmpresa);
			return this.buildResponse(pedidos);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@DELETE
	@Path("/deletarPedido/{idPedido}")
	@Consumes("application/*")
	public Response deletarPedido(@PathParam("idPedido") int idPedido) {
		try {
			PedidoService service= new PedidoService(); 
            service.deletarPedido(idPedido);			
			return this.buildResponse("Pedido deletado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	

	
	@GET
	@Path("/visualizarPedido")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response visualizarPedido(@QueryParam("idPedido") int idPedido,@QueryParam("produto") String produto){
			try {
			List<Pedido> pedido=  new ArrayList<Pedido>();
			PedidoService service= new PedidoService(); 
			pedido =service.visualizarPedido(idPedido,produto);			
			return this.buildResponse(pedido);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());

		}
	}
	
	
}

	


