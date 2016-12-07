package br.com.maisControle.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Produto;
import br.com.maisControle.service.ProdutoService;


@Path("produtoRest")
public class ProdutoRest extends UtilRest {
		
	public ProdutoRest() {
	}

	@POST
	@Path("/addProduto")
	@Consumes("application/*")
	public Response addProduto(String produtoParam) throws ProdutoException {
		try {
			Produto produto = new ObjectMapper().readValue(produtoParam,Produto.class);
            ProdutoService service= new ProdutoService(); 
            produto.setEmpresa(getEmpresa());
            service.addProduto(produto);
            return this.buildResponse("Produto cadastrado com sucesso");
		} catch (Exception e) {
			e.printStackTrace();	
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@GET
	@Path("/buscarProdutosPorNome")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarProdutosPorNome(@QueryParam("nome") String nome,@QueryParam("codigo") String codigo)
	{
		try {
			List<Produto> produtos = new ArrayList<Produto>();
			ProdutoService service= new ProdutoService();
			
			int id= getEmpresa().getId_empresa();
			String TipoUsuario= getUsuario().getTipo();
			
            produtos =service.buscarProdutosPorNome(nome,codigo,id,TipoUsuario);
            
			return this.buildResponse(produtos);
			
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	@DELETE
	@Path("/deletarProduto/{id_produto}")
	@Consumes("application/*")
	public Response deletarProduto(@PathParam("id_produto") int id_produto) {
		try {
			ProdutoService service= new ProdutoService(); 
			int idEmpresa= getEmpresa().getId_empresa();
            boolean returno = service.deletarProduto(id_produto,idEmpresa);
            
            if(returno==true){
			return this.buildResponse("Produto deletado com sucesso.");
            }
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
		 return this.buildResponse("Produto não pode ser deletado.<br>A pedido ligado a este produto");
	}
	
	@GET
	@Path("/buscarProdutoPeloId/{id_produto}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarProdutoPeloId(@PathParam("id_produto") int id_produto) {
		try {
			Produto produto= new Produto();
			ProdutoService service= new ProdutoService(); 
            produto =service.buscarProdutoPeloId(id_produto);			
			return this.buildResponse(produto);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());

		}
	}

	@PUT
	@Path("/editarProduto")
	@Consumes("application/*")
	public Response editarProduto(String produtoParam) {
		try {
				Produto produto= new ObjectMapper().readValue(produtoParam,Produto.class);
				ProdutoService service= new ProdutoService();
				service.editarProduto(produto);
				return this.buildResponse("Produto editado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

	
}