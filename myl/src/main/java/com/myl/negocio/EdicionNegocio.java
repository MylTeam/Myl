package com.myl.negocio;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.EdicionDao;
import com.myl.modelo.Edicion;
import com.myl.negocio.EdicionNegocio;


@Singleton
@Named("edicionNegocio")
public class EdicionNegocio {
	private EdicionDao EdicionDao;	
	
	@Transactional
	public List<Edicion> findAll() {
		return EdicionDao.findAll();
	}

	@Transactional
	public Edicion findById(Integer id) {
		return EdicionDao.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Edicion save(Edicion entidad) {
		Edicion modelo = EdicionDao.save(entidad);		
		return modelo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Edicion entidad) {
		EdicionDao.delete(entidad);
	}

	@Transactional
	public List<Edicion> findByExample(Edicion Edicion) {
		return EdicionDao.findByExample(Edicion);
	}

	@Transactional
	public Boolean existe(String nombre) { 
		Edicion EdicionEjemplo = new Edicion();
		List<Edicion> Edicions = null;
		EdicionEjemplo.setNombre(nombre);
		Edicions = findByExample(EdicionEjemplo);
		if (Edicions != null && Edicions.size() > 0) {
			return true;
		}
		return false;
	}
	
     
	public EdicionDao getEdicionDao() {
		return EdicionDao;
	}

	public void setEdicionDao(EdicionDao EdicionDao) {
		this.EdicionDao = EdicionDao;
	}

	
	
}
