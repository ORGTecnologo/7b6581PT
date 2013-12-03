package tecinf.negocio.dtos;

import java.io.Serializable;

public class AprobarContenidoDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer idContenido;
	private Integer idVersion;
	private String nombreContenido;
	private String descripcionVersion;
	private String version;
	private String proveedor;

	
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public Integer getIdContenido() {
		return idContenido;
	}
	public void setIdContenido(Integer idContenido) {
		this.idContenido = idContenido;
	}
	public String getNombreContenido() {
		return nombreContenido;
	}
	public void setNombreContenido(String nombreContenido) {
		this.nombreContenido = nombreContenido;
	}
	public String getDescripcionVersion() {
		return descripcionVersion;
	}
	public void setDescripcionVersion(String descripcionVersion) {
		this.descripcionVersion = descripcionVersion;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Integer getIdVersion() {
		return idVersion;
	}
	public void setIdVersion(Integer idVersion) {
		this.idVersion = idVersion;
	}
	
}
