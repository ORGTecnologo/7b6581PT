package tecinf.negocio;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.SubCategoriaContenidoDataType;

@Local
public interface NegocioCategoriaContenido {
	
	public List<CategoriaContenidoDataType> obtenerCategoriasYSubcategorias();
	
	public List<CategoriaContenidoDataType> obtenerTodasCategoriasYSubcategorias();
	
	public List<SubCategoriaContenidoDataType> obtenerSubCategorias();
	
	public List<SubCategoriaContenidoDataType> obtenerSubCategoriasPorFiltros(Map filtros);
	
	public List<CategoriaContenidoDataType> obtenerCategorias();
	
	public List<CategoriaContenidoDataType> obtenerCategoriasPorFiltros(Map filtros);
	
	public Integer ingresarCategoria(CategoriaContenidoDataType dt) throws Exception;
	
	public Integer ingresarSubCategoria(SubCategoriaContenidoDataType dt) throws Exception;
	
	public Integer modificarCategoria(CategoriaContenidoDataType dt) throws Exception;
	
	public Integer modificarSubCategoria(SubCategoriaContenidoDataType dt) throws Exception;
	
	public Integer cambiarEstadoSubCategoria(SubCategoriaContenidoDataType dt) throws Exception;
	
	public Integer cambiarEstadoCategoria(CategoriaContenidoDataType dt) throws Exception;
	
}
