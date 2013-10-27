package tecinf.negocio;

import java.util.List;

import javax.ejb.Local;

import tecinf.negocio.dtos.CategoriaContenidoDataType;

@Local
public interface NegocioCategoriaContenido {
	
	public List<CategoriaContenidoDataType> obtenerCategoriasYSubcategorias();	
	
}
