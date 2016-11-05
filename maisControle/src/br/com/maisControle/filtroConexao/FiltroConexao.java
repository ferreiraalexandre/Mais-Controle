package br.com.maisControle.filtroConexao;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import br.com.maisControle.objetos.Usuario;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class FiltroConexao implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		/*
		 * O metodo getContexPath é responsavel por retornar o contexto da URL que realizou a requisiçao.
		 */
		String context = request.getServletContext().getContextPath();
		
		try{
			/*
			 * O metodo getSession e responsavel por pegar a sessao ativa.
			 * Aqui foi necessario fazer um casting pois o objeto request e do tipo SevletRequest e nao 
			 * HttpServletRequest como no servlet onde voce utiliza o metodo em questao sem o uso do casting.
			 */
			HttpSession session = ((HttpServletRequest)request).getSession();
			Usuario usuario = null;
			if(session != null){
				usuario = (Usuario) session.getAttribute("login");
				
			}
			if(usuario == null){
				/*
				 * Aqui esta sendo setado um atributo na sessao para que depois possamos
				 * exibir uma mensagem ao usuario
				 */
				session.setAttribute("msg", "Você não está logado no sistema!");
				
				/*
				 * Utilizamos o metodo sendRedirect que altera a URL do navegador para posicionar
				 * o usuario na tela de login, que neste caso é a pagina index.html
				 * Note que nao precisamos utilizar o recurso "../../" para informar
				 * o caminho da pagina index.html, a variavel do contexto ja posiciona no inicio da URL.
				 */
				HttpServletResponse resp =((HttpServletResponse)response);
				
				resp.sendRedirect(context+"/home.html");
			}else{
				/*
				 * Caso exista um usuario valido (diferente de nulo) envia a requisicao para 
				 * a pagina que se deseja acessar, ou seja, permite o acesso, deixa passar.
				 */
				chain.doFilter(request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	public void destroy() {///Limpa o Servelt
		System.out.println("destroy");
	}
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init");
	}
}

