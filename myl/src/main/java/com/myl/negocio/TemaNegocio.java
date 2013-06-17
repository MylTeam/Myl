package com.myl.negocio;

import java.util.List;

import javax.inject.Named;
import javax.inject.Singleton;

import org.springframework.transaction.annotation.Transactional;

import com.myl.dao.TemaDao;
import com.myl.modelo.Tema;
import com.myl.negocio.TemaNegocio;


@Singleton
@Named("temaNegocio")
public class TemaNegocio {
	private TemaDao temaDao;	
	
	@Transactional
	public List<Tema> findAll() {
		return temaDao.findAll();
	}

	@Transactional
	public Tema findById(Integer id) {
		return temaDao.findById(id);
	}

	@Transactional(rollbackFor = Exception.class)
	public Tema save(Tema entidad) {
		Tema modelo = temaDao.save(entidad);		
		return modelo;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Tema entidad) {
		temaDao.delete(entidad);
	}

	@Transactional
	public List<Tema> findByExample(Tema tema) {
		return temaDao.findByExample(tema);
	}

	@Transactional
	public Boolean existe(String nombre) { 
		Tema temaEjemplo = new Tema();
		List<Tema> temas = null;
		temaEjemplo.setNombre(nombre);
		temas = findByExample(temaEjemplo);
		if (temas != null && temas.size() > 0) {
			return true;
		}
		return false;
	}
	
     
	public TemaDao getTemaDao() {
		return temaDao;
	}

	public void setTemaDao(TemaDao temaDao) {
		this.temaDao = temaDao;
	}

	
	
}
