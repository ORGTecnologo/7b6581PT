package tecinf.negocio.dtos;

import java.io.Serializable;
import java.util.List;

public class ListaFiltrosDataType implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private List<ItemListaFiltrosDataType> listaFiltros;

	public List<ItemListaFiltrosDataType> getListaFiltros() {
		return listaFiltros;
	}

	public void setListaFiltros(List<ItemListaFiltrosDataType> listaFiltros) {
		this.listaFiltros = listaFiltros;
	}
	
}
