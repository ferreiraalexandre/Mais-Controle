package br.com.maisControle.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Logout extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}
	
	public void process(ServletRequest request, ServletResponse response)throws IOException, ServletException {
		try{
			String context = request.getServletContext().getContextPath();
			((HttpServletRequest) request).getSession().invalidate();
			((HttpServletResponse)response).sendRedirect(context+"/home.html");
		} catch(Exception e){
			e.printStackTrace();
		}
	}
}
