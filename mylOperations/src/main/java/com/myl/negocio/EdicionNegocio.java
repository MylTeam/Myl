package com.myl.negocio;

import java.util.List;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.EdicionDao;
import com.myl.modelo.Edicion;


@Service("edicionNegocio")
@Scope(value = BeanDefinition.SCOPE_SINGLETON)
public class EdicionNegocio{
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
	public Boolean existeNombre(String nombre) { 
		Edicion edicionEjemplo = new Edicion();
		List<Edicion> ediciones = null;
		edicionEjemplo.setNombre(nombre);
		ediciones = findByExample(edicionEjemplo);
		if (ediciones != null && !ediciones.isEmpty()) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public Boolean existeSiglas(String siglas) { 
		Edicion edicionEjemplo = new Edicion();
		List<Edicion> ediciones = null;
		edicionEjemplo.setSiglas(siglas);
		ediciones = findByExample(edicionEjemplo);
		if (ediciones != null && !ediciones.isEmpty()) {
			return true;
		}
		return false;
	}
	
	@Transactional
	public String existe(Edicion edicion) { 
		Edicion edicionEjemplo = new Edicion();
		List<Edicion> ediciones = null;
		
		edicionEjemplo.setSiglas(edicion.getNombre());
		edicionEjemplo.setNombre(edicion.getNombre());
		ediciones = findByExample(edicionEjemplo);
		if (ediciones != null && !ediciones.isEmpty()) {
			return "La edición ya se encuentra registrada.";
		}
		
		ediciones = null;
		edicionEjemplo = new Edicion();
		edicionEjemplo.setNombre(edicion.getNombre());
		ediciones = findByExample(edicionEjemplo);
		if (ediciones != null && !ediciones.isEmpty()) {
			return "El nombre de la edición ya está registrado.";
		}
		
		ediciones = null;
		edicionEjemplo = new Edicion();
		edicionEjemplo.setSiglas(edicion.getSiglas());
		ediciones = findByExample(edicionEjemplo);
		if (ediciones != null && !ediciones.isEmpty()) {
			return "Las siglas ingresadas ya están registradas.";
		}
		
			
		return null;
	}
	
     
	public EdicionDao getEdicionDao() {
		return edicionDao;
	}

	public void setEdicionDao(EdicionDao edicionDao) {
		this.edicionDao = edicionDao;
	}

	
	
}
