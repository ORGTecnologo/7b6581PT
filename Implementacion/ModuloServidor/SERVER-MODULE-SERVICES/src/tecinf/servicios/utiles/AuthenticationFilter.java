package tecinf.servicios.utiles;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthenticationFilter implements Filter
{
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)	throws IOException, ServletException
	{
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		res.setContentType("application/json");
		//PrintWriter out = res.getWriter();
		
		//String uname= req.getParameter("username");
		//String psw= req.getParameter("password");
		
		/*if (0 == 0){
			res.sendRedirect("/SERVER-MODULE-SERVICES/restws/usuarios/listarUsuarios");
			return;
		}*/
		
		/*
		if(uname.equals("admin") && psw.equals("admin"))
			out.println("You have successfully logged in");
		else
			out.println("either user name or password is invalid");
		*/
		chain.doFilter(request, response);
	}
	
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub		
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub		
	}
}