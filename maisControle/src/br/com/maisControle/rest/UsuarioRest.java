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
import javax.ws.rs.QueryParam;

import org.codehaus.jackson.map.ObjectMapper;
import br.com.maisControle.exception.ProdutoException;

import br.com.maisControle.objetos.Usuario;
import br.com.maisControle.service.UsuarioService;


@Path("usuarioRest")
public class UsuarioRest extends UtilRest {
		
	public UsuarioRest() {
	}
	
	@POST
	@Path("/addUsuarioMaster")
	@Consumes("application/*")
	public Response addUsuarioMaster(String usuarioParam) throws ProdutoException {
		
		try {
			Usuario usuario = new ObjectMapper().readValue(usuarioParam,Usuario.class);
			UsuarioService service= new UsuarioService(); 
			//usuario.setEmpresa(usuario.getId_empresa());
			String msg =service.addUsuario(usuario);
            return this.buildResponse(msg);
		} catch (Exception e) {
			e.printStackTrace();	
			return this.buildErrorResponse(e.getMessage());
		}
	}

	
	
	@POST
	@Path("/addUsuario")
	@Consumes("application/*")
	public Response addUsuario(String usuarioParam) throws ProdutoException {
		
		try {
			Usuario usuario = new ObjectMapper().readValue(usuarioParam,Usuario.class);
			UsuarioService service= new UsuarioService(); 
			usuario.setEmpresa(getEmpresa());
			String msg =service.addUsuario(usuario);
            return this.buildResponse(msg);
		} catch (Exception e) {
			e.printStackTrace();	
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@GET
	@Path("/buscarUsuarioPorNome")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarUsuarioPorNome(@QueryParam("nome") String nome,@QueryParam("tipo") String tipo) {
		
		try {
			List<Usuario> usuario = new ArrayList<Usuario>();
			UsuarioService service= new UsuarioService(); 
			int id= getEmpresa().getId_empresa();
			usuario =service.buscarUsuarioPorNome(nome,tipo,id);
			return this.buildResponse(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}
	
	@DELETE
	@Path("/deletarUsuario/{id_usuario}")
	@Consumes("application/*")
	public Response deletarUsuario(@PathParam("id_usuario") int id_usuario) {
		try {
			UsuarioService service= new UsuarioService();
			int idEmpresa= getEmpresa().getId_empresa();
			boolean returno =  service.deletarUsuario(id_usuario,idEmpresa);			
			  if(returno==true){
			return this.buildResponse("Usuario deletado com sucesso.");
			  }
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
		 return this.buildResponse("Usuario não pode ser deletado.<br>A pedido  ligado a este usuario");
	}
	@GET
	@Path("/buscarUsuarioPeloCodigo/{id_usuario}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response buscarUsuarioPeloCodigo(@PathParam("id_usuario") int id_usuario) {
		try {
			Usuario usuario= new Usuario();
			UsuarioService service= new UsuarioService(); 
			usuario =service.buscarUsuarioPeloCodigo(id_usuario);			
			return this.buildResponse(usuario);
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());

		}
	}

	
	@PUT
	@Path("/editarUsuario")
	@Consumes("application/*")
	public Response editarUsuario(String usuarioParam) {
		try {
			Usuario usuario= new ObjectMapper().readValue(usuarioParam,Usuario.class);
			UsuarioService service= new UsuarioService();
				service.editarUsuario(usuario);
				return this.buildResponse("Usuario editado com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
			return this.buildErrorResponse(e.getMessage());
		}
	}

}
