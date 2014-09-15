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
	private EdicionDao edicionDao;	
	
	@Transactional
	public List<Edicion> findAll() {
		return edicionDao.findAll();
	}

	@Transactional
	public Edicion findById(Integer id) {
		return edicionDao.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Edicion save(Edicion entidad) {
		Edicion modelo = edicionDao.save(entidad);		
		return modelo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Edicion entidad) {
		edicionDao.delete(entidad);
	}

	@Transactional
	public List<Edicion> findByExample(Edicion edicion) {
		return edicionDao.findByExample(edicion);
	}

	@Transactional
	public Boolean existe(String nombre) { 
		Edicion edicionEjemplo = new Edicion();
		List<Edicion> ediciones = null;
		edicionEjemplo.setNombre(nombre);
		ediciones = findByExample(edicionEjemplo);
		if (ediciones != null && !ediciones.isEmpty()) {
			return true;
		}
		return false;
	}
	
     
	public EdicionDao getEdicionDao() {
		return edicionDao;
	}

	public void setEdicionDao(EdicionDao edicionDao) {
		this.edicionDao = edicionDao;
	}

	
	
}
