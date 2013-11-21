package tecinf.presentacion.mbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import tecinf.negocio.NegocioAuditoria;
import tecinf.negocio.dtos.AuditoriaDataType;
import tecinf.negocio.utiles.NegocioFactory;

@ManagedBean
@ViewScoped
public class AuditoriaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private NegocioAuditoria negocioAuditoria = null;
	private List<AuditoriaDataType> listaAuditoria = null;
	
	public  AuditoriaMB() throws NamingException { 
		
		negocioAuditoria = NegocioFactory.getNegocioAuditoria();
		listaAuditoria = negocioAuditoria.obtenerRegistroDeIngresos();
		
	}

	public List<AuditoriaDataType> getListaAuditoria() {
		return listaAuditoria;
	}

	public void setListaAuditoria(List<AuditoriaDataType> listaAuditoria) {
		this.listaAuditoria = listaAuditoria;
	}
	
}
