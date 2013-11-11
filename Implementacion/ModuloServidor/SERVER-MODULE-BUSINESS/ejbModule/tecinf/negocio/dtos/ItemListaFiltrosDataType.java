package tecinf.negocio.dtos;

import java.io.Serializable;

public class ItemListaFiltrosDataType implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String filtro;
	private String valor;
	
	public String getFiltro() {
		return filtro;
	}
	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	
}
