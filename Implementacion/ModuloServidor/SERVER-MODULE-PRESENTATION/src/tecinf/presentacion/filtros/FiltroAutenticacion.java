package tecinf.presentacion.filtros;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import tecinf.negocio.dtos.UserSession;
import tecinf.negocio.utiles.EnumTipoUsuario;
import tecinf.presentacion.utiles.ConstantesSession;


public class FiltroAutenticacion implements Filter {
	
	private List<String> publicURLs;
	private List<String> adminURLs;
	private List<String> supplierURLs;
	private List<String> clientURLs; 

	public FiltroAutenticacion() 
	{	 
	} 

	public void destroy() 
	{		
	} 
 
	public void doFilter(ServletRequest request, ServletResponse response,  FilterChain chain) throws IOException, ServletException 
	{ 
		HttpServletRequest req = (HttpServletRequest) request; 
		HttpServletResponse resp = (HttpServletResponse) response; 
		HttpSession session = req.getSession(true); 
		String url = req.getServletPath(); 
		
		UserSession userSession = (UserSession)session.getAttribute(ConstantesSession.keyUsuarioSession);
		
		if (!isPublic(url)){
			
			if (userSession == null){
				resp.sendRedirect("/SERVER-MODULE-PRESENTATION/index.html");
				return;			
			}			
			if (userSession.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_CLIENTE) && !isClientRestricted(url)){				
				resp.sendRedirect("/SERVER-MODULE-PRESENTATION/index.html");
				return;	
			} else if (userSession.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_PROVEEDOR) && !isSupplierRestricted(url)){
				resp.sendRedirect("/SERVER-MODULE-PRESENTATION/index.html");
				return;			
			} else if (userSession.getTipoUsuario().equals(EnumTipoUsuario.USUARIO_ADMINISTRADOR) && !isAdminRestricted(url)) {
				resp.sendRedirect("/SERVER-MODULE-PRESENTATION/admin/admin.xhtml");
				return;	
			}			
		}
		chain.doFilter(request, response); 
		
	} 


	public void init(FilterConfig fConfig) throws ServletException 
	{ 
		// URLs publicas
		publicURLs = new ArrayList<String>();	
		publicURLs.add("/index.html");		
		publicURLs.add("/css/");
		publicURLs.add("/js/");
		publicURLs.add("/cors/");
		publicURLs.add("/fonts/");
		publicURLs.add("/img/");
		publicURLs.add("/restws");
		publicURLs.add("/views/content.html");
		publicURLs.add("/Images");
		publicURLs.add("/ImageDispatcherServlet");	
		
		// URLs de administradores
		adminURLs = new ArrayList<String>(); 
		adminURLs.add("/admin/upload");
		adminURLs.add("/admin/FileUploadServlet");
		adminURLs.add("/admin/admin.xhtml");
		adminURLs.add("/admin/partialCategorias.xhtml");
		adminURLs.add("/admin/partialSubCategorias.xhtml");
		adminURLs.add("/admin/partialSubComentarios.xhtml");
		adminURLs.add("/admin/partialContenidos.xhtml");
		adminURLs.add("/admin/partialAuditoria.xhtml");
		adminURLs.add("/admin/partialParametros.xhtml");
		adminURLs.add("/admin/partialUsuarios.xhtml");
		adminURLs.add("/views/perfil.html");
		
		// URLs de proveedores
		supplierURLs = new ArrayList<String>(); 
		supplierURLs.add("/proveedor/AltaContenido.html");
		supplierURLs.add("/proveedor/upload.jsp");
		supplierURLs.add("/FileDispatcherServlet");
		supplierURLs.add("/proveedor/upload");
		supplierURLs.add("/proveedor/FileUploadServlet");
					
		// URLs de clientes
		clientURLs = new ArrayList<String>();
		clientURLs.add("/FileDispatcherServlet");
		clientURLs.add("/views/perfil.html");
		
		
	}
	
	private Boolean isPublic(String url){
		boolean isPublic = false; 
		for (int i = 0; i < publicURLs.size(); i++) { 
			if (url.contains(publicURLs.get(i))) { 
				isPublic = true; 
				break; 
			}
		}
		return isPublic;
	}
	
	private Boolean isAdminRestricted(String url){
		boolean isOk = false; 
		for (int i = 0; i < adminURLs.size(); i++) { 
			if (url.contains(adminURLs.get(i))) { 
				isOk = true; 
				break; 
			} 
		}
		return isOk;
	}
	
	private Boolean isSupplierRestricted(String url){
		boolean isOk = false; 
		for (int i = 0; i < supplierURLs.size(); i++) { 
			if (url.contains(supplierURLs.get(i))) { 
				isOk = true; 
				break; 
			} 
		}
		return isOk;
	}
	
	private Boolean isClientRestricted(String url){
		boolean isOk = false; 
		for (int i = 0; i < clientURLs.size(); i++) {
			if (url.contains(clientURLs.get(i))) {
				isOk = true; 
				break; 
			} 
		}
		return isOk;
	}
	
}