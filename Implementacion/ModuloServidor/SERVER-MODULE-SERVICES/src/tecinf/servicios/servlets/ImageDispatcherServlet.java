package tecinf.servicios.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.*;

import java.io.*;

import tecinf.negocio.NegocioArchivos;
import tecinf.negocio.NegocioUsuario;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.servicios.utiles.CripterDecripter;

public class ImageDispatcherServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void init() throws ServletException {
	}

	public void doPost(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {
	}

	public void doGet(javax.servlet.http.HttpServletRequest request,
			javax.servlet.http.HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException {

		// String myID = (String) request.getParameter("ID");
		String qString = request.getQueryString();

		// CripterDecripter cd = new CripterDecripter();
		String rutaDesencriptada = CripterDecripter.decrypt(qString);

		try {
			// leemos el fichero del ftp,usando el metodo que traduce el
			// path
			// http://localhost:8080/SERVER-MODULE-SERVICES/Images?/5lD4yJfz52ifDueFXAUMwUOBJBWFSj6Pkd2W49gj3MxMHgy1CqbAWRtIpKA8q8EDywz+iDwRd4=
			// /BaseDatosRecursos/pruebas/Images/incubus.jpg
			// "/5lD4yJfz52ifDueFXAUMwUOBJBWFSj6Pkd2W49gj3MxMHgy1CqbAWRtIpKA8q8EDywz+iDwRd4="
			
			NegocioArchivos negocioArchivo = NegocioFactory.getNegocioArchivos();			
			File f = negocioArchivo.responderImagen("/BaseDatosRecursos/pruebas/images/incubus.jpg");
			RandomAccessFile raf = new RandomAccessFile("/BaseDatosRecursos/pruebas/images/incubus.jpg", "r");
			FileInputStream fis = new FileInputStream(f);
			FileReader fr = new FileReader(f);
			byte b[] = new byte[(int) f.length()];
			raf.read(b);

			// cabecera
			response.setHeader("Content-Type", "img/jpeg");
			response.setIntHeader("Content-Length", (int) f.length());
			response.setHeader("Accept-Ranges", "bytes");

			// lo escribimos
			OutputStream out = response.getOutputStream();
			
			out.write(b);
			out.close();
			raf.close();
			fis.close();
			fr.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// salida a pagina de error y devolver foto por defecto

			// leemos el fichero del ftp por defecto
			File f = new File("/BaseDatosRecursos/pruebas/images/Sin_foto.png");
			RandomAccessFile raf = new RandomAccessFile("/BaseDatosRecursos/pruebas/images/Sin_foto.png", "r");
			FileInputStream fis = new FileInputStream(f);

			FileReader fr = new FileReader(f);
			byte b[] = new byte[(int) f.length()];
			raf.read(b);

			// cabecera
			response.setHeader("Content-Type", "img/jpeg");
			response.setIntHeader("Content-Length", (int) f.length());
			// response.setHeader("Accept-Ranges", "bytes");

			// lo escribimos
			OutputStream out = response.getOutputStream();
			out.write(b);
			out.close();
			raf.close();
			fis.close();
			fr.close();
		} // catch
	} // doGet
}
