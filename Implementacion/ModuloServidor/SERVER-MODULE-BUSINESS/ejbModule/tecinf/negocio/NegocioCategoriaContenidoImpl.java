package tecinf.negocio;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.naming.NamingException;

import org.jboss.logging.Logger;

import tecinf.negocio.dtos.CategoriaContenidoDataType;
import tecinf.negocio.dtos.SubCategoriaContenidoDataType;
import tecinf.negocio.utiles.DataTypesFactory;
import tecinf.negocio.utiles.ValidationUtil;
import tecinf.persistencia.daos.CategoriaContenidoDao;
import tecinf.persistencia.daos.SubCategoriaContenidoDao;
import tecinf.persistencia.entities.CategoriaContenidoEntity;
import tecinf.persistencia.entities.SubCategoriaContenidoEntity;
import tecinf.persistencia.utiles.PersistenciaFactory;

@Stateless
public class NegocioCategoriaContenidoImpl implements NegocioCategoriaContenido {
	
	private static Logger logger = Logger.getLogger(NegocioCategoriaContenidoImpl.class);
	
	private CategoriaContenidoDao categoriaContenidoDao = null;
	private SubCategoriaContenidoDao subCategoriaContenidoDao = null;
	
	public NegocioCategoriaContenidoImpl(){
		
		try {
			categoriaContenidoDao = PersistenciaFactory.getCategoriaContenidoDao();
			subCategoriaContenidoDao = PersistenciaFactory.getSubCategoriaContenidoDao();
		} catch (NamingException e) {
			logger.error(e.getMessage(), e) ;
		}	
		
	}
	
	public List<CategoriaContenidoDataType> obtenerTodasCategoriasYSubcategorias(){
		List<CategoriaContenidoDataType> listaCategorias = new ArrayList<CategoriaContenidoDataType>();		
		List<CategoriaContenidoEntity> listaCategoriasE = categoriaContenidoDao.findAll();
		for (CategoriaContenidoEntity cat : listaCategoriasE){
			CategoriaContenidoDataType dt = DataTypesFactory.getCategoriaContenidoDataType(cat);			
			List<SubCategoriaContenidoEntity> subCats = subCategoriaContenidoDao.getAllByCategoria(cat.getId());
			for (SubCategoriaContenidoEntity subCat : subCats)
				dt.getSubcategorias().add(DataTypesFactory.getSubCategoriaContenidoDataType(subCat));
			listaCategorias.add(dt);
		}		
		return listaCategorias;
	}
	
	public List<CategoriaContenidoDataType> obtenerCategoriasYSubcategorias(){
		List<CategoriaContenidoDataType> listaCategorias = new ArrayList<CategoriaContenidoDataType>();		
		List<CategoriaContenidoEntity> listaCategoriasE = categoriaContenidoDao.findAllByState(true);
		for (CategoriaContenidoEntity cat : listaCategoriasE){
			CategoriaContenidoDataType dt = DataTypesFactory.getCategoriaContenidoDataType(cat);			
			List<SubCategoriaContenidoEntity> subCats = subCategoriaContenidoDao.getAllByCategoria(cat.getId());
			for (SubCategoriaContenidoEntity subCat : subCats){
				if (subCat.getHabilitado())
					dt.getSubcategorias().add(DataTypesFactory.getSubCategoriaContenidoDataType(subCat));
			}
				
			listaCategorias.add(dt);
		}		
		return listaCategorias;
	}
	
	public Integer ingresarCategoria(CategoriaContenidoDataType dt) throws Exception {
		
		if (ValidationUtil.isNullOrEmpty(dt.getDescripcion()))
			throw new Exception("Descripción obligatoria");
		if (ValidationUtil.isNullOrEmpty(dt.getNombre()))		
			throw new Exception("Nombre obligatorio");
		//if (ValidationUtil.isNullOrEmpty(dt.getRutaImagen()))
		//	throw new Exception("Imagen obligatoria obligatorio");
		
		CategoriaContenidoEntity categoria = categoriaContenidoDao.findByName(dt.getNombre());
		if  (categoria != null)
			throw new Exception("Nombre ya utilizado");
		categoria = new CategoriaContenidoEntity();
		categoria.setDescripcion(dt.getDescripcion());
		categoria.setNombre(dt.getNombre());
		categoria.setHabilitado(dt.getHabilitada());
		categoria.setRutaImagen("");
		
		categoriaContenidoDao.persist(categoria);
		
		return categoria.getId();				
	}
	
	public Integer ingresarSubCategoria(SubCategoriaContenidoDataType dt) throws Exception {
		
		if (ValidationUtil.isNullOrEmpty(dt.getDescripcion()))
			throw new Exception("Descripción obligatoria");
		if (ValidationUtil.isNullOrEmpty(dt.getNombre()))
			throw new Exception("Nombre obligatorio");
		//if (ValidationUtil.isNullOrEmpty(dt.getRutaImagen()))
			//throw new Exception("Imagen obligatoria obligatorio");

		CategoriaContenidoEntity cat = categoriaContenidoDao.findByID(dt.getCategoria().getId());
		if (cat == null)
			throw new Exception("Categoria obligatoria");
		
		SubCategoriaContenidoEntity subCategoria = subCategoriaContenidoDao.findByName(dt.getNombre());
		if (subCategoria != null)
			throw new Exception("Nombre ya utilizado");
			
		subCategoria = new SubCategoriaContenidoEntity();
		
		subCategoria.setCategoria(cat);
		subCategoria.setHabilitado(true);
		subCategoria.setDescripcion(dt.getDescripcion());
		subCategoria.setNombre(dt.getNombre());
		
		subCategoriaContenidoDao.persist(subCategoria);
		
		return subCategoria.getId();
	}
	
	public Integer modificarCategoria(CategoriaContenidoDataType dt) throws Exception {
		
		if (ValidationUtil.isNullOrEmpty(dt.getDescripcion()))
			throw new Exception("Descripción obligatoria");
		if (ValidationUtil.isNullOrEmpty(dt.getNombre()))
			throw new Exception("Nombre obligatorio");
		//if (ValidationUtil.isNullOrEmpty(dt.getRutaImagen()))
		//	throw new Exception("Imagen obligatoria obligatorio");
		
		CategoriaContenidoEntity categoria = categoriaContenidoDao.findByName(dt.getNombre());
		if  (categoria != null && dt.getId() != categoria.getId())
			throw new Exception("Nombre ya utilizado");
		
		categoria = categoriaContenidoDao.findByID(dt.getId());
		categoria.setDescripcion(dt.getDescripcion());
		categoria.setNombre(dt.getNombre());
		
		categoriaContenidoDao.merge(categoria);
		
		return categoria.getId();
	}
	
	public Integer modificarSubCategoria(SubCategoriaContenidoDataType dt) throws Exception {
		
		if (ValidationUtil.isNullOrEmpty(dt.getDescripcion()))
			throw new Exception("Descripción obligatoria");
		if (ValidationUtil.isNullOrEmpty(dt.getNombre()))
			throw new Exception("Nombre obligatorio");
		//if (ValidationUtil.isNullOrEmpty(dt.getRutaImagen()))
			//throw new Exception("Imagen obligatoria obligatorio");

		CategoriaContenidoEntity cat = categoriaContenidoDao.findByID(dt.getCategoria().getId());
		if (cat == null)
			throw new Exception("Categoria obligatoria");
				
		SubCategoriaContenidoEntity subCategoria = subCategoriaContenidoDao.findByName(dt.getNombre());
		if (!dt.getNombre().equals(subCategoria.getNombre())){
			if (subCategoria != null)
				throw new Exception("Nombre ya utilizado");
		}
		subCategoria = subCategoriaContenidoDao.findByID(dt.getId());
		
		subCategoria.setCategoria(cat);
		subCategoria.setHabilitado(true);
		subCategoria.setDescripcion(dt.getDescripcion());
		subCategoria.setNombre(dt.getNombre());
		
		subCategoriaContenidoDao.merge(subCategoria);
		
		return subCategoria.getId();
	}
	
	public Integer cambiarEstadoSubCategoria(SubCategoriaContenidoDataType dt) throws Exception {
		
		SubCategoriaContenidoEntity scat = subCategoriaContenidoDao.findByID(dt.getId());
		
		if (dt.getHabilitada() == null || dt.getHabilitada() == false)
			scat.setHabilitado(true);
		else
			scat.setHabilitado(false);
		subCategoriaContenidoDao.merge(scat);
		
		return scat.getId();
	}
	
	public Integer cambiarEstadoCategoria(CategoriaContenidoDataType dt) throws Exception {
		
		CategoriaContenidoEntity cat = categoriaContenidoDao.findByID(dt.getId());
		
		if (dt.getHabilitada() == null || dt.getHabilitada() == false)
			cat.setHabilitado(true);
		else
			cat.setHabilitado(false);
		categoriaContenidoDao.merge(cat);
		
		List<SubCategoriaContenidoEntity> listaSubCats = subCategoriaContenidoDao.getAllByCategoria(dt.getId());
		for (SubCategoriaContenidoEntity s : listaSubCats){
			if (dt.getHabilitada() == null || dt.getHabilitada() == false)
				s.setHabilitado(true);
			else 
				s.setHabilitado(false);
			subCategoriaContenidoDao.merge(s);
		}
		
		return cat.getId();
	}
	
	public List<SubCategoriaContenidoDataType> obtenerSubCategorias(){
		List<SubCategoriaContenidoDataType> listaSubCat = new ArrayList<SubCategoriaContenidoDataType>(); 
		
		List<SubCategoriaContenidoEntity> listaSubCatE = subCategoriaContenidoDao.findAll();
		if (listaSubCatE != null){
			for (SubCategoriaContenidoEntity s : listaSubCatE)
				listaSubCat.add(DataTypesFactory.getSubCategoriaContenidoDataType(s));
		}
		
		return listaSubCat;
	}
	
	public List<SubCategoriaContenidoDataType> obtenerSubCategoriasPorFiltros(Map filtros){
		List<SubCategoriaContenidoDataType> listaSubCat = new ArrayList<SubCategoriaContenidoDataType>(); 
		
		List<SubCategoriaContenidoEntity> listaSubCatE = subCategoriaContenidoDao.findAllByFiltros(filtros);
		if (listaSubCatE != null){
			for (SubCategoriaContenidoEntity s : listaSubCatE)
				listaSubCat.add(DataTypesFactory.getSubCategoriaContenidoDataType(s));
		}
		
		return listaSubCat;
	}
	
	public List<CategoriaContenidoDataType> obtenerCategorias(){
		List<CategoriaContenidoDataType> listaCat = new ArrayList<CategoriaContenidoDataType>(); 
		
		List<CategoriaContenidoEntity> listaSubCatE = categoriaContenidoDao.findAll();
		if (listaSubCatE != null){
			for (CategoriaContenidoEntity s : listaSubCatE)
				listaCat.add(DataTypesFactory.getCategoriaContenidoDataType(s));
		}
		
		return listaCat;
	}
	
	public List<CategoriaContenidoDataType> obtenerCategoriasPorFiltros(Map filtros){
		List<CategoriaContenidoDataType> listaCat = new ArrayList<CategoriaContenidoDataType>(); 
		
		List<CategoriaContenidoEntity> listaSubCatE = categoriaContenidoDao.findAllByFiltros(filtros);
		if (listaSubCatE != null){
			for (CategoriaContenidoEntity s : listaSubCatE)
				listaCat.add(DataTypesFactory.getCategoriaContenidoDataType(s));
		}
		
		return listaCat;
	}
	
}
