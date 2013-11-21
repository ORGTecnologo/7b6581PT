package tecinf.presentacion.servlets;
 
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Logger;

import tecinf.negocio.utiles.CripterDecripter;
 
public class FileDispatcherServlet extends javax.servlet.http.HttpServlet implements javax.servlet.Servlet {
	
	private static Logger logger = Logger.getLogger(FileDispatcherServlet.class);
	
    static final long serialVersionUID = 1L;
    private static final int BUFSIZE = 4096;
    //private String filePath;
    
    public void init() {
    	
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
    	String idContenido = request.getParameter("idContenido");
    	String qString = request.getQueryString();
    	String qStringDecripted = CripterDecripter.decrypt(qString);
    	
    	File file = new File(qStringDecripted);
        
        int length = 0;
        ServletOutputStream outStream = response.getOutputStream();
        ServletContext context  = getServletConfig().getServletContext();
        String mimetype = context.getMimeType(qStringDecripted);
        
        // sets response content type
        if (mimetype == null) {
            mimetype = "application/octet-stream";
        }
        response.setContentType(mimetype);
        response.setContentLength((int)file.length());
        String fileName = file.getName();
        
        // sets HTTP header
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        
        byte[] byteBuffer = new byte[BUFSIZE];
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        
        // reads the file's bytes and writes them to the response stream
        while ((in != null) && ((length = in.read(byteBuffer)) != -1))
        {
            outStream.write(byteBuffer,0,length);
        }
        
        in.close();
        outStream.close();
    }
}