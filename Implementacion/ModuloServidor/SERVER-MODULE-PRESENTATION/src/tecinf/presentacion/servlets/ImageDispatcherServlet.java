package tecinf.presentacion.servlets;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import org.jboss.logging.Logger;

import java.awt.image.BufferedImage;
import java.io.*;

import tecinf.negocio.NegocioArchivos;
import tecinf.negocio.utiles.NegocioFactory;

public class ImageDispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getLogger(ImageDispatcherServlet.class);

	public void init() throws ServletException {
	}

	public void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
	}

	public void doGet(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		String qString = request.getQueryString();

		try {

			// Ejemplo de ruta en html 
			// /SERVER-MODULE-PRESENTATION/Images?LLGJcfgUSahG+lgxfmg6UigHGH3lrj7aREm66NIjPpYLaFQVNMH+DCsqA+8lX/Kivy+FMznbbMWoBrA4i+AKZmoEYhHtMArnmIZ2+ZRyDPrNR5g2FwsqFx5QIblFUz9y			
			// el query String debe ser = CripterDecripter.encrypt(ruta), donde ruta es la relativa de la imagen respecto al 
			//directorio base, ejemplo "/pruebas/images/incubus.jpg"			
			// CripterDecripter.encrypt("/pruebas/images/incubus.jpg") = qHg4l7Ai/VwdWRL4WB3TSa6+QwfDOBjr9xVl3Q0KrylbQdMSIV0+Cg==
			
			NegocioArchivos negocioArchivo = NegocioFactory.getNegocioArchivos();			
			File f = negocioArchivo.responderImagen(qString);
			
			BufferedImage bi = ImageIO.read(f);
			OutputStream out = response.getOutputStream();
			ImageIO.write(bi, "jpg", out);
			out.close();
			
		} catch (Exception e) {
			logger.error(e.getMessage() , e); 
			try {
				NegocioArchivos negocioArchivo = NegocioFactory.getNegocioArchivos();			
				File f = negocioArchivo.responderImagen("/BaseDatosRecursos/pruebas/images/Sin_foto.png");
				
				BufferedImage bi = ImageIO.read(f);
				OutputStream out = response.getOutputStream();
				ImageIO.write(bi, "jpg", out);
				out.close();
			} catch (Exception e2) {
				logger.error(e2.getMessage() , e2);
			}
		}
	} 
}
