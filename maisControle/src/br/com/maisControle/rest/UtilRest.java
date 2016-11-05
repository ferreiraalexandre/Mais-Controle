package br.com.maisControle.rest;

import java.io.StringWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.codehaus.jackson.map.ObjectMapper;

import br.com.maisControle.objetos.Empresa;
import br.com.maisControle.objetos.Pedido;
import br.com.maisControle.objetos.Usuario;
public class UtilRest {
	
	@Context
	private HttpServletRequest req;

	public Object getSessionAttribute(String param) {

    	HttpSession session= req.getSession(false);
    	if(session == null){
    		return null;
    	}
    	return session.getAttribute(param);
	}
	
	public Empresa getEmpresa() {
		Empresa empresa = null;
		Usuario usuario =  (Usuario) getSessionAttribute("login");
		if(usuario != null){
			empresa = usuario.getEmpresa();
		}
		return empresa;
	}
	
	public Usuario getUsuario() {
		
		
		Usuario usuario =  (Usuario) getSessionAttribute("login");
		
		return usuario;
	}
	
	
	
	//	public Usuario getLoggedUser() {
//
//    	HttpSession session= req.getSession(false);
//    	if(session == null){
//    		return null;
//    	}
//    	Usuario usuario = (Usuario) session.getAttribute("login");
//
//    	return usuario;
//	}
	
	/*
	 * Abaixo o m�todo respons�vel por enviar a resposta ao cliente sobre
	a transa��o realizada, inclus�o, consulta, edi��o ou exclus�o,
	realizadas com sucesso.
	Repare que o m�todo em quest�o aguarda que seja repassado um
	conte�do que ser� referenciado por um objeto chamado result.
	 */
	public Response buildResponse(Object result){
		/*
		* Cria a inst�ncia da Classe StringWriter para o objeto fw.
		Isto que este objeto � quem estar� referenciando o conte�do
		repassado como resposta para o lado cliente.
		*/
		StringWriter fw = new StringWriter();
		try {
			/*
			* Cria a inst�ncia da classe ObjectMapper para o objeto
			mapper.
			*/
			ObjectMapper mapper = new ObjectMapper();
			/*
			* Acessa o m�todo writeValue, por meio do objeto mapper,
			* passando como par�metro o objeto fw e o conte�do do
			* objeto result, na realidade est� criando um mapeamento
			* de dados onde o objeto fw � a chave do valor de um
			* conte�do referenciado pelo objeto result.
			* result pode conter a mensagem, "Cadastro efetuado com
			* sucesso", ou "Exclus�o efetuada com sucesso" ou outra
			* qualquer dependendo da transa��o realizada.
			*/
			mapper.writeValue(fw, result);
			/*
			* Monta o objeto de resposta com status 200 (ok), junto
			* com o objeto result convertido para JSON pelo objeto fw 
			* para o cliente no formato String.
			*/
			return Response.ok(fw.toString()).build();
		}catch (Exception ex){
			return this.buildErrorResponse(ex.getMessage());
			
		}
	}
	/*
	* Abaixo o m�todo respons�vel por enviar a resposta ao cliente sobre
	* A transa��o realizada, inclus�o, consulta, edi��o ou exclus�o.ao
	* cliente, n�o realizadas com sucesso, ou seja, que contenha algum
	* erro.
	* Repare que o m�todo em quest�o aguarda que seja repassado um
	* conte�do que ser� referenciado pelo por um objeto chamado rb.
	*/
	public Response buildErrorResponse(String str){
		/*
		 * Abaixo o objeto rb recebe o status do erro.
		 */
				ResponseBuilder rb = 
			Response.status(Response.Status.INTERNAL_SERVER_ERROR);
				/*
				* Define a entidade (objeto), que nesse caso � uma
				* mensagem que ser� retornado para o cliente.
				*/
				rb = rb.entity(str);
				/*
				 * Define o tipo de retotno desta entidade(objeto), no
				 * caso � definido como texto simples.
				 */
				rb = rb.type("text/plain");
				/*
				* Retorna o objeto de resposta com status 500 (erro),
				* junto com a String contendo a mensagem de erro.
				*/
				return rb.build();
	}

}
