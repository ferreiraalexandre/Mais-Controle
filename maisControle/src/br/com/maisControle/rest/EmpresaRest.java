package br.com.maisControle.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.Response;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.objetos.Empresa;
import br.com.maisControle.service.EmpresaService;


@Path("empresaRest")
public class EmpresaRest extends UtilRest {
		
	public EmpresaRest() {
	}

	@POST
	@Path("/addEmpresa")
	@Consumes("application/*")
	public Response addEmpresa(String empresaParam) throws ProdutoException {
		try {
			Empresa empresa = new ObjectMapper().readValue(empresaParam,Empresa.class);
            EmpresaService service= new EmpresaService(); 
            service.addEmpresa(empresa);
			return this.buildResponse(empresa.getId_empresa());
		} catch (Exception e) {
			e.printStackTrace();	
			return this.buildErrorResponse(e.getMessage());
		}
	}
}
	