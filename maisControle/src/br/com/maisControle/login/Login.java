package br.com.maisControle.login;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import br.com.maisControle.jdbc.JDBCEmpresaDAO;
import br.com.maisControle.objetos.Usuario;
import com.google.gson.Gson;
import br.com.maisControle.bd.conexao.Conexao;
import br.com.maisControle.exception.ProdutoException;
import br.com.maisControle.exemploCriptografia.ConvertMD5;

/**
 * Servlet implementation class CadastroContato
 */

public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	
	
	public Login() {
		super();

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException{
		try {
			process(req,resp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	private void process(HttpServletRequest request,
			HttpServletResponse response) throws   ProdutoException {
		
		/*
		 * Instanciar o objeto contato para a classe Contato pois e nesta que
		 * armazenaremos os valores dos campos do formulario contato que
		 * posteriormente serao gravados no banco de dados.
		 */
		String context = request.getServletContext().getContextPath();//Pesquisar
		Usuario usuario = new Usuario();
		usuario.setEmail(request.getParameter("email"));
		usuario.setSenha(request.getParameter("senha"));
		Conexao conec = new Conexao();

		try {

			Map<String, String> msg = new HashMap<String, String>();

			Connection conexao = conec.abrirConexao();
			JDBCEmpresaDAO jdbcEmpresa = new JDBCEmpresaDAO(conexao);
			String senha = convertPassword(usuario.getSenha());
			usuario.setSenha(senha);
			usuario = jdbcEmpresa.login(usuario);
			
			HttpSession sessao = request.getSession();
			if (usuario != null) {				
				sessao.setAttribute("login", usuario);
				System.out.println("login");
				response.sendRedirect(context + "/sistema.html");
			
			} else {
				msg.put("msg", "Erro ao logar");
				sessao.setAttribute("msg", "Usuário ou senha inválido!");
				response.sendRedirect(context + "/home.html?login=invalid");

			}

			String json = new Gson().toJson(msg);
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(json);

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conec.fecharConexao();
		}
	}

	
	private String convertPassword(String senha){

		System.out.println("Senha base64: " + senha);
		
		
		byte[] decoded = Base64.getDecoder().decode(senha);
		senha= new String(decoded, StandardCharsets.UTF_8);
		System.out.println("Senha base64 Decod: "+ senha);
		
		String senhaMD5 = "";
		try {
			senhaMD5 = ConvertMD5.convertMD5(senha);
			System.out.println("Senha MD5 : " + senhaMD5);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return senhaMD5;
	}
}

