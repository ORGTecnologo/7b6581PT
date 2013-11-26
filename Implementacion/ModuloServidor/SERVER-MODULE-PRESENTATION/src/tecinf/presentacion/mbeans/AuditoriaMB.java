package tecinf.presentacion.mbeans;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.naming.NamingException;

import tecinf.negocio.NegocioAuditoria;
import tecinf.negocio.dtos.AuditoriaDataType;
import tecinf.negocio.utiles.NegocioFactory;
import tecinf.negocio.utiles.ValidationUtil;

@ManagedBean
@ViewScoped
public class AuditoriaMB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private NegocioAuditoria negocioAuditoria = null;
	private List<AuditoriaDataType> listaAuditoria = null;
	
	private SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	@SuppressWarnings("rawtypes")
	private Map filtros = new HashMap<String, Object>();
	
	private String filtroUsuario;
	private String filtroDesde;
	private String filtroHasta;
	
	public  AuditoriaMB() throws NamingException { 
		
		negocioAuditoria = NegocioFactory.getNegocioAuditoria();
		listaAuditoria = negocioAuditoria.obtenerRegistroDeIngresos(filtros);
		
	}

	public List<AuditoriaDataType> getListaAuditoria() {
		return listaAuditoria;
	}

	public void setListaAuditoria(List<AuditoriaDataType> listaAuditoria) {
		this.listaAuditoria = listaAuditoria;
	}

	public String getFiltroUsuario() {
		return filtroUsuario;
	}

	public void setFiltroUsuario(String filtroUsuario) {
		this.filtroUsuario = filtroUsuario;
	}

	public String getFiltroDesde() {
		return filtroDesde;
	}

	public void setFiltroDesde(String filtroDesde) {
		this.filtroDesde = filtroDesde;
	}

	public String getFiltroHasta() {
		return filtroHasta;
	}

	public void setFiltroHasta(String filtroHasta) {
		this.filtroHasta = filtroHasta;
	}
	
	@SuppressWarnings("unchecked")
	public void filtrar() throws ParseException {
		filtros.clear();
		if (!ValidationUtil.isNullOrEmpty(filtroDesde))
			filtros.put("desde", sdf.parse(filtroDesde));
		if (!ValidationUtil.isNullOrEmpty(filtroHasta))
			filtros.put("hasta", sdf.parse(filtroHasta));
		if (!ValidationUtil.isNullOrEmpty(filtroUsuario))
			filtros.put("usuario", filtroUsuario);
		listaAuditoria = negocioAuditoria.obtenerRegistroDeIngresos(filtros);
	}
	
}
